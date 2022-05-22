
package br.edu.iff.projetoEvento.controller.view;

import br.edu.iff.projetoEvento.model.Evento;
import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.model.TipoStatusEventoEnum;
import br.edu.iff.projetoEvento.repository.PermissaoRepository;
import br.edu.iff.projetoEvento.service.FuncionarioService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioViewController {
    
    @Autowired
    private FuncionarioService service;
    @Autowired
    private PermissaoRepository repo;
    
    @GetMapping
    public String getAll (Model model){
        model.addAttribute("funcionarios", service.findAll());
        return "funcionarios";
    }
    @GetMapping(path = "/funcionario")
    public String cadastro (Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "formulariofuncionario";
    }
    @PostMapping(path = "/funcionario")
    public String save(@Valid @ModelAttribute Funcionario funcionario, BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formulariofuncionario";
        }
        if(funcionario.getSenha().equals(confirmarSenha)){
            model.addAttribute("msgErros", new ObjectError("funcionario", "Campos senha e confirmar senha devem ser iguais."));
            return "formulariofuncionario";
        }
        funcionario.setId(0);
        try {
            service.save(funcionario);
            model.addAttribute("msgSucesso", "Funcionário cadastrado com sucesso.");
            model.addAttribute("funcionario", new Funcionario());
            return "formulariofuncionario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            
            return "formulariofuncionario";
        }
        
    }
    @GetMapping(path = "/funcionario/{ID}")
    public String alterar (@PathVariable("ID") Long ID, Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "formulariofuncionario";
    }
    @PostMapping(path = "/funcionario/{ID}")
    public String atualizar (@Valid @ModelAttribute Funcionario funcionario, 
            BindingResult result,
            @PathVariable("ID") Long ID, Model model) {
        
        List<FieldError> error = new ArrayList<>();
        
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha")){
                error.add(fe);
            }
        }
        if (!error.isEmpty()) {
            model.addAttribute("msgErros", error);
            return "formulariofuncionario";
        }
        funcionario.setId(ID);
        try {
            service.update(funcionario, "", "", "");
            model.addAttribute("msgSucesso", "Funcionário atualizado com sucesso.");
            model.addAttribute("funcionario", funcionario);
            return "formulariofuncionario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            
            return "formulariofuncionario";
        }
        
    }
    @GetMapping(path = "/{ID}/deletar")
    public String deletar(@PathVariable("ID") Long ID){
        service.delete(ID);
        return "redirect:/funcionarios";
    }
}
