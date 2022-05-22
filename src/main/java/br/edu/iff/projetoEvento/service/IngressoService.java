
package br.edu.iff.projetoEvento.service;

import br.edu.iff.projetoEvento.exception.NotFoundException;
import br.edu.iff.projetoEvento.model.Ingresso;
import br.edu.iff.projetoEvento.repository.IngressoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IngressoService {
    @Autowired
    private IngressoRepository repo;
    
    public List<Ingresso> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    public List<Ingresso> findAll(Long IDevento){
        
        return repo.findByIngressosByEvento(IDevento);
    }
    
    public List<Ingresso> findAll (int page, int size, Long participanteID, Long funcionarioID, Long eventoID){
        Pageable p = PageRequest.of(page, size);
        if(participanteID != 0 && funcionarioID != 0){
            return repo.findByParticipanteIdAndFuncionarioId(participanteID, funcionarioID, p);
        }
        else{
            if(participanteID != 0){
                return repo.findByParticipanteId(participanteID, p);
            }
            else{
                if(funcionarioID != 0){
                    return repo.findByFuncionarioId(funcionarioID, p);
                }
                else{
                    if(eventoID != 0){
                        return repo.findByIngressosByEvento(eventoID);
                    }
                }
            }
        }
        return repo.findAll(p).toList();
    }
    
    public Ingresso findByID(Long ID){
        
        Optional<Ingresso> resultado = repo.findById(ID);
        if (resultado.toString().isEmpty()){
            throw new NotFoundException("Ingresso não encontrado.");
        }
        return resultado.get();
    }
    
    public Ingresso save (Ingresso i){
       try{ 
        return repo.save(i);
       }catch(Exception e){
           throw new RuntimeException("Falha ao salvar o ingresso.");
       }
    }
    public Ingresso update (Ingresso i){
        Ingresso obj = findByID(i.getID());
        
        try {
            i.setID(obj.getID());
            return repo.save(i);
        } catch (Exception Ex) {
            throw new RuntimeException("Falha ao atualizar o Ingresso.");
        }
        
    }
    public void delete(Long ID){
       Ingresso obj = findByID(ID);
       
       try{
           repo.delete(obj);
       }catch(Exception e){
           throw new RuntimeException("Falha ao deletar o Ingresso.");
       }
   }
}
