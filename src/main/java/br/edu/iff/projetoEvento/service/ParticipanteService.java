
package br.edu.iff.projetoEvento.service;

import br.edu.iff.projetoEvento.exception.NotFoundException;
import br.edu.iff.projetoEvento.model.Participante;
import br.edu.iff.projetoEvento.model.Usuario;
import br.edu.iff.projetoEvento.repository.ParticipanteRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParticipanteService {
    @Autowired
    private ParticipanteRepository repo;
    
    public List<Participante> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    public List<Participante> findAll(){
        return repo.findAll();
    }
    
    public Participante findByID(Long ID){
        
        Optional<Participante> resultado = repo.findById(ID);
        if (resultado.toString().isEmpty()){
            throw new NotFoundException("Participante não encontrado.");
        }
        return resultado.get();
    }
    
    public Participante save (MultipartFile file, Participante p){
       //Arquivo
       if(file != null){
           if(!file.isEmpty()){
               salvarArquivo(file, p.getCPF()+".pdf");
               p.setDocumentos(p.getCPF()+".pdf");
           }
           else{
               p.setDocumentos(null);
           }
       }
       //Verifica se CPF já está cadastrado.
        verificaCPFCadastrado(p.getCPF());
       try{ 
        return repo.save(p);
       }catch(Exception e){
           throw new RuntimeException("Falha ao salvar o participante.");
       }
    }
    public Participante update (Participante p, MultipartFile file){
        //Participante já existe
        Participante obj = findByID(p.getId());
        
        //Arquivo
        p.setDocumentos(obj.getDocumentos());
        
        if(!file.isEmpty()){
            salvarArquivo(file, p.getCPF()+".pdf");
            p.setDocumentos(p.getCPF()+".pdf");
        }
        
        try{
            p.setCPF(obj.getCPF());
            return repo.save(p);
        } catch(Exception ex){
            throw new RuntimeException("Falha ao atualizar o Evento.");
        }
        
    }
    public void delete(Long ID){
       Participante obj = findByID(ID);
       
        verificaExclusaoParticipanteComIngressos(obj);
       try{
           repo.delete(obj);
           if(obj.getDocumentos() != null){
               Path caminho = Paths.get("Uploads", obj.getDocumentos());
               Files.deleteIfExists(caminho);
           }
       }catch(IOException e){
           throw new RuntimeException("Falha ao deletar o Participante.");
       }
   }
    private void salvarArquivo (MultipartFile file, String novoNome){
        
        if(file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)){
            Path dest = Paths.get("uploads", novoNome);
            try {
                file.transferTo(dest);   
            }catch (IOException e) {
                throw new RuntimeException("Falha ao salvar o arquivo.");
            }
        }
        else{
            throw new RuntimeException("Arquivo deve estar no formato PDF.");
        }
    }
    private void verificaCPFCadastrado (String CPF){
        List<Usuario> resultado = repo.findbyParticipanteCPF(CPF);
        if(!resultado.isEmpty()){
            throw new RuntimeException("CPF já cadastrado.");
        }
    }
    private void verificaExclusaoParticipanteComIngressos(Participante p) {
        if (!p.getIngressos().isEmpty()) {
            throw new RuntimeException("Participante possui ingresso. Não pode ser excluído.");
        }
    }
}
