
package br.edu.iff.projetoEvento.controller.apirest;

import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.service.FuncionarioService;
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

@RestController
@RequestMapping(path = "/apirest/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{ID}")
    public ResponseEntity getOne(@PathVariable("ID") Long ID){
        return ResponseEntity.ok(service.findByID(ID));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Funcionario funcionario){
        funcionario.setID(null);
        service.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }
    
    @PutMapping(path = "/{ID}")
    public ResponseEntity update(@PathVariable("ID") Long ID, @RequestBody Funcionario funcionario){
        funcionario.setID(ID);
        service.update(funcionario, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{ID}")
    public ResponseEntity delete(@PathVariable("ID") Long ID){
        service.delete(ID);
        return ResponseEntity.ok().build(); 
    }
    
    @PutMapping(path = "/{ID}/alterarSenha")
    public ResponseEntity alterarSenha(@PathVariable("ID") Long ID,
            @RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
            @RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
            @RequestParam(name = "confirmarNovaSenha", defaultValue = "", required = true) String confirmarNovaSenha){
        
        Funcionario f = service.findByID(ID);
        service.update(f, senhaAtual, novaSenha, confirmarNovaSenha);
        return ResponseEntity.ok().build();
    }
}
