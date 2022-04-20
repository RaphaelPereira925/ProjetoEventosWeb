
package br.edu.iff.projetoEvento.service;

import br.edu.iff.projetoEvento.model.Evento;
import br.edu.iff.projetoEvento.model.Ingresso;
import br.edu.iff.projetoEvento.repository.EventoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository repo;
    
    public List<Evento> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    public List<Evento> findAll(){
        return repo.findAll();
    }
    
    public Evento findByID(Long ID){
        
        Optional<Evento> resultado = repo.findById(ID);
        if (resultado.toString().isEmpty()){
            throw new RuntimeException("Evento não encontrado.");
        }
        return resultado.get();
    }
    
    public Evento save (Evento e){
       try{ 
        return repo.save(e);
       }catch(Exception ex){
           throw new RuntimeException("Falha ao salvar o evento.");
       }
    }
    
    public Evento update (Evento e){
        Evento obj = findByID(e.getID());
        
        List<Ingresso> ingressoAtuais = obj.getIngressos();
        ingressoAtuais.removeAll(e.getIngressos());
        verificaExclusaoEventosComIngressos(ingressoAtuais);
        
        try {
            e.setID(obj.getID());
            return repo.save(e);
        } catch (Exception Ex) {
            throw new RuntimeException("Falha ao atualizar o Evento.");
        }
        
    }
    public void delete(Long ID){
       Evento obj = findByID(ID);
       
       verificaExclusaoEventosComIngressos(obj.getIngressos());
       try{
           repo.delete(obj);
       }catch(Exception e){
           throw new RuntimeException("Falha ao deletar o Evento.");
       }
   }
    private void verificaExclusaoEventosComIngressos(List<Ingresso> ingressos){
        
        for(Ingresso eE : ingressos){
            if(!eE.toString().isEmpty()){
                throw new RuntimeException("Não é possível excluir evento.");
            }
        }
    }
    
    
}
