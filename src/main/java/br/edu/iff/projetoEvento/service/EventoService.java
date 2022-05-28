
package br.edu.iff.projetoEvento.service;

import br.edu.iff.projetoEvento.exception.NotFoundException;
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
    
    public Evento findById(Long id){
        
        Optional<Evento> resultado = repo.findById(id);
        if (resultado.toString().isEmpty()){
            throw new NotFoundException("Evento não encontrado.");
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
        Evento obj = findById(e.getId());
        
        List<Ingresso> ingressoAtuais = obj.getIngressos();
        ingressoAtuais.removeAll(e.getIngressos());
        
        try {
            e.setId(obj.getId());
            return repo.save(e);
        } catch (Exception Ex) {
            throw new RuntimeException("Falha ao atualizar o Evento.");
        }
        
    }
    public void delete(Long id){
       Evento obj = findById(id);
       
       verificaExclusaoEventosComIngressos(obj.getIngressos());
       try{
           repo.delete(obj);
       }catch(Exception e){
           throw new RuntimeException("Falha ao deletar o Evento.");
       }
   }
    

    private void verificaExclusaoEventosComIngressos(List<Ingresso> ingressos){
        
        if(!ingressos.isEmpty()){
            throw new RuntimeException("Não é possível excluir evento.");
        }
    }
    
    
}
