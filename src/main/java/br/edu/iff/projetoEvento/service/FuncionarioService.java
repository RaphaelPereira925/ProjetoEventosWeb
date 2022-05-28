
package br.edu.iff.projetoEvento.service;

import br.edu.iff.projetoEvento.exception.NotFoundException;
import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.model.Permissao;
import br.edu.iff.projetoEvento.model.Usuario;
import br.edu.iff.projetoEvento.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Funcionario findByCPF(String CPF){
        return (Funcionario) repo.findbyCPF(CPF);
    }
    
    public Funcionario findById(Long id){
        
        Optional<Funcionario> resultado = repo.findById(id);
        if (resultado.toString().isEmpty()){
            throw new NotFoundException("Funcionário não encontrado.");
        }
        return resultado.get();
    }
    public Funcionario save (Funcionario f){
       //Verifica se o CPF já está cadastrado
       verificaCPFCadastrado(f.getCPF());
       
       try{ 
        f.setSenha(new BCryptPasswordEncoder().encode(f.getSenha()));
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
        Funcionario obj = findById(f.getId());
        //Verifica permissões nulas
        removePermissoesNulas(f);
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
    public void delete(Long id){
       Funcionario obj = findById(id);
        //verificaExclusaoFuncinarioComIngressos(obj);
       
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
        
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        
        
        if(!senhaAtual.trim().isEmpty() && !novaSenha.trim().isEmpty() && !confirmarNovaSenha.trim().isEmpty()){
            if(!encrypt.matches(senhaAtual, obj.getSenha())){
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("A senha nova não é igual a senha atual.");
            }
            obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        }
        
    }
    
/*
    private void verificaExclusaoFuncinarioComIngressos(Funcionario f) {
        if (!f.getIngressos().isEmpty()) {
            throw new RuntimeException("Funcionário possui ingresso. Não pode ser excluído.");
        }
    }
*/
    public void removePermissoesNulas(Funcionario f){
        f.getPermissoes().removeIf( (Permissao p) -> {
            return p.getId()==null;
        });
        if(f.getPermissoes().isEmpty()){
            throw new RuntimeException("Funcionario deve conter no mínimo 1 permissão.");
        }
    }
}
