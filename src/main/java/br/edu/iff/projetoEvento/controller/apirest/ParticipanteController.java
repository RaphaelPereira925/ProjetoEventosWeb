
package br.edu.iff.projetoEvento.controller.apirest;

import br.edu.iff.projetoEvento.model.Participante;
import br.edu.iff.projetoEvento.service.ParticipanteService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(path = "/apirest/participantes")
public class ParticipanteController {
    @Autowired
    private ParticipanteService service;

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

        return ResponseEntity.ok(service.findAll(page, size));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Participante participante){
        participante.setId(null);
        service.save(null, participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(participante);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Participante participante){
        participante.setId(id);
        service.update(participante, null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping(path = "/{id}/uploadFile")
    public ResponseEntity uploadFile(@PathVariable("id") Long id, MultipartFile file){
        Participante participante = service.findById(id);
        service.update(participante, file);
        return ResponseEntity.ok().build();
    }
    
}
