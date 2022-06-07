
package br.edu.iff.projetoEvento.controller.view;

import br.edu.iff.projetoEvento.model.Ingresso;
import br.edu.iff.projetoEvento.model.TipoIngressoEnum;
import br.edu.iff.projetoEvento.service.EventoService;
import br.edu.iff.projetoEvento.service.FuncionarioService;
import br.edu.iff.projetoEvento.service.IngressoService;
import br.edu.iff.projetoEvento.service.ParticipanteService;
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

@Controller
@RequestMapping(path = "/eventos/{IDevento}/ingressos")
public class IngressoViewController {
    @Autowired
    private IngressoService serviIngresso;
    @Autowired
    private EventoService serviEvento;
    @Autowired
    private ParticipanteService serviParticipante;
    @Autowired
    private FuncionarioService serviFuncionario;

    @GetMapping
    public String getAll(@PathVariable("IDevento") Long IDevento, Model model) {
        model.addAttribute("ingressos", serviIngresso.findById(IDevento));
        model.addAttribute("IDevento", IDevento);
        return "ingressos";
    }
    @GetMapping(path = "/ingresso")
    public String cadastro(@PathVariable("IDevento") Long IDevento, Model model) {
        model.addAttribute("ingresso", new Ingresso());
        model.addAttribute("IDevento", IDevento);
        model.addAttribute("tipoIngresso", TipoIngressoEnum.values());
        model.addAttribute("participantes", serviParticipante.findAll());
        model.addAttribute("funcionarios", serviFuncionario.findAll());
        model.addAttribute("eventos", serviEvento.findAll());
        
        return "formularioIngresso";
    }
    @PostMapping(path = "/ingresso")
    public String salvar(@PathVariable("IDevento") Long IDevento, @Valid @ModelAttribute Ingresso ingresso, 
            BindingResult result, Model model){
        
        
        //Valores a serem retornados no model
        model.addAttribute("IDevento", IDevento);
        model.addAttribute("tipoIngresso", TipoIngressoEnum.values());
        model.addAttribute("participantes", serviParticipante.findAll());
        model.addAttribute("funcionarios", serviFuncionario.findAll());
        model.addAttribute("eventos", serviEvento.findAll());
        
        //Elimina erros
        
        List <FieldError> list = new ArrayList<>();
        
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("dataHora")){
                list.add(fe);
            }
        }
        if(!list.isEmpty()){
            model.addAttribute("msgErros", list);
            return "formularioIngresso";
        }
        
        ingresso.setId(null);
        try {
            serviIngresso.save(ingresso);
            model.addAttribute("msgSucesso", "Ingresso cadastrado com sucesso.");
            model.addAttribute("ingresso", new Ingresso());
            return "formularioIngresso";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("ingresso", e.getMessage()));
            return "formularioIngresso";
        }
        
    }
    @GetMapping(path = "/ingresso/{id}")
    public String alterar(@PathVariable("IDevento") Long IDevento, @PathVariable("id") Long id,
            Model model) {
        
        
        
        model.addAttribute("ingresso", serviIngresso.findById(id));
        model.addAttribute("IDevento", IDevento);
        model.addAttribute("tipoIngresso", TipoIngressoEnum.values());
        model.addAttribute("participantes", serviParticipante.findAll());
        model.addAttribute("funcionarios", serviFuncionario.findAll());
        model.addAttribute("eventos", serviEvento.findAll());
        
        return "formularioIngresso";
    }
    
    @PostMapping(path = "/ingresso/{id}")
    public String update (@PathVariable("IDevento") Long IDevento, @PathVariable("id") Long id,
            @Valid @ModelAttribute Ingresso ingresso, 
            BindingResult result, Model model){
        
        
        //Valores a serem retornados no model
        model.addAttribute("IDevento", IDevento);
        model.addAttribute("tipoIngresso", TipoIngressoEnum.values());
        model.addAttribute("participantes", serviParticipante.findAll());
        model.addAttribute("funcionarios", serviFuncionario.findAll());
        model.addAttribute("eventos", serviEvento.findAll());
        
        //Elimina erros
        
        List <FieldError> list = new ArrayList<>();
        
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("dataHora")){
                list.add(fe);
            }
        }
        if(!list.isEmpty()){
            model.addAttribute("msgErros", list);
            return "formularioIngresso";
        }
        
        ingresso.setId(null);
        try {
            serviIngresso.update(ingresso);
            model.addAttribute("msgSucesso", "Ingresso cadastrado com sucesso.");
            model.addAttribute("ingresso", ingresso);
            return "formularioIngresso";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("ingresso", e.getMessage()));
            return "formularioIngresso";
        }
        
    }
    
    @GetMapping(path = "/{id}/deletar")
    public String delete (@PathVariable("IDevento") Long IDevento, @PathVariable("id") Long id) {
        
        serviIngresso.delete(id);
        return "redirect:/eventos/"+IDevento+"/ingressos";
    }
}
