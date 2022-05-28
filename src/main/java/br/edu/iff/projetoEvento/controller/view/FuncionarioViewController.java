
package br.edu.iff.projetoEvento.controller.view;

import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.repository.PermissaoRepository;
import br.edu.iff.projetoEvento.service.FuncionarioService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
        funcionario.setId(null);
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
    @GetMapping(path = "/funcionario/{id}")
    public String alterar (@PathVariable("id") Long id, Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "formulariofuncionario";
    }
    @PostMapping(path = "/funcionario/{id}")
    public String atualizar (@Valid @ModelAttribute Funcionario funcionario, 
            BindingResult result,
            @PathVariable("id") Long id, Model model) {
        
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
        funcionario.setId(null);
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
    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/funcionarios";
    }
    
    
    //Controller para os meus Dados
    
    @GetMapping(path = "/meusdados/")
    public String getMeusDados (@AuthenticationPrincipal User user, Model model){
        
        Funcionario funcionario = service.findByCPF(user.getUsername());
        model.addAttribute("funcionario", funcionario);
        
        return "formularioMeusDados";
    }
    @PostMapping(path = "/meusdados")
    public String atualizarDados (@Valid @ModelAttribute Funcionario funcionario, 
            BindingResult result, @AuthenticationPrincipal User user, Model model,
            @RequestParam("senhaAtual") String senhaAtual,
            @RequestParam("novaSenha") String novaSenha,
            @RequestParam("confirmaSenha") String confirmaSenha) {
        
        List<FieldError> error = new ArrayList<>();
        
        for(FieldError fe : result.getFieldErrors()){
            if((!fe.getField().equals("senha"))&&(!fe.getField().equals("permissoes"))){
                error.add(fe);
            }
        }
        if (!error.isEmpty()) {
            model.addAttribute("msgErros", error);
            return "formularioMeusDados";
        }
        Funcionario funcionarioBD = service.findByCPF(user.getUsername());
        if(funcionarioBD.getId().equals(funcionario.getId())){
            throw new RuntimeException("Acesso negado.");
        }
        try {
            
            funcionario.setPermissoes(funcionario.getPermissoes());
            service.update(funcionario, senhaAtual, novaSenha, confirmaSenha);
            model.addAttribute("msgSucesso", "Funcionário atualizado com sucesso.");
            model.addAttribute("funcionario", funcionario);
            return "formularioMeusDados";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            
            return "formularioMeusDados";
        }
    }
}
