
package br.edu.iff.projetoEvento.service;

import br.edu.iff.projetoEvento.exception.NotFoundException;
import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.model.Usuario;
import br.edu.iff.projetoEvento.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repo;
    
    public List<Funcionario> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    public List<Funcionario> findAll(){
        return repo.findAll();
    }
    
    public Funcionario findByID(Long ID){
        
        Optional<Funcionario> resultado = repo.findById(ID);
        if (resultado.toString().isEmpty()){
            throw new NotFoundException("Funcionário não encontrado.");
        }
        return resultado.get();
    }
    public Funcionario save (Funcionario f){
       //Verifica se o CPF já está cadastrados
       verificaCPFCadastrado(f.getCPF());
       
       try{ 
        return repo.save(f);
       }catch(Exception Ex){
           Throwable t = Ex;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
           throw new RuntimeException("Falha ao salvar o funcionário.");
       }
    }
    public Funcionario update (Funcionario f, String senhaAtual, String novaSenha, String ConfirmarNovaSenha){
        //Verifica se funcionário já existe
        Funcionario obj = findByID(f.getId());
        //Verifica alteração da senha
        alterarSenha(obj, senhaAtual, novaSenha, ConfirmarNovaSenha);
        try {
            f.setCPF(obj.getCPF());
            f.setSenha(obj.getSenha());
            return repo.save(f);
        } catch (Exception Ex) {
            Throwable t = Ex;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar o Funcionário.");
        }
        
    }
    public void delete(Long ID){
       Funcionario obj = findByID(ID);
        verificaExclusaoFuncinarioComIngressos(obj);
       
       try{
           repo.delete(obj);
       }catch(Exception e){
           throw new RuntimeException("Falha ao deletar o Funcionário.");
       }
   }
    private void verificaCPFCadastrado (String CPF){
        List<Usuario> resultado = (List<Usuario>) repo.findbyCPF(CPF);
        if(!resultado.isEmpty()){
            throw new RuntimeException("CPF já cadastrado.");
        }
    }
    private void alterarSenha (Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        
        if(!senhaAtual.trim().isEmpty() && !novaSenha.trim().isEmpty() && !confirmarNovaSenha.trim().isEmpty()){
            if(!senhaAtual.equals(obj.getSenha())){
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("A senha nova não é igual a senha atual.");
            }
            obj.setSenha(novaSenha);
        }
        
    }
    private void verificaExclusaoFuncinarioComIngressos(Funcionario f) {
        if (!f.getIngressos().isEmpty()) {
            throw new RuntimeException("Funcionário possui ingresso. Não pode ser excluído.");
        }
    }
}
