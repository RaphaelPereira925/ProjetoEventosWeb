
package br.edu.iff.projetoEvento.controller.apirest;

import br.edu.iff.projetoEvento.model.Ingresso;
import br.edu.iff.projetoEvento.service.IngressoService;
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

@RestController
@RequestMapping(path = "/apirest/ingressos")
public class IngressoController {
    @Autowired
    private IngressoService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "clienteID", defaultValue = "0", required = false) Long clienteID,
            @RequestParam(name = "funcionarioID", defaultValue = "0", required = false) Long funcionarioID,
            @RequestParam(name = "eventoID", defaultValue = "0", required = false) Long eventoID){
        
        return ResponseEntity.ok(service.findAll(page, size, clienteID, funcionarioID, eventoID));        
    }
    @GetMapping(path = "/{ID}")
    public ResponseEntity getOne(@PathVariable("ID") Long ID){
        return ResponseEntity.ok(service.findByID(ID));
    }
    @PostMapping
    public ResponseEntity save(@RequestBody Ingresso ingresso){
        ingresso.setID(null);
        service.save(ingresso);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingresso);
    }
    
    @PutMapping(path = "/{ID}")
    public ResponseEntity update(@PathVariable("ID") Long ID, @RequestBody Ingresso ingresso){
        ingresso.setID(ID);
        service.update(ingresso);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{ID}")
    public ResponseEntity delete(@PathVariable("ID") Long ID){
        service.delete(ID);
        return ResponseEntity.ok().build();
    }
}
