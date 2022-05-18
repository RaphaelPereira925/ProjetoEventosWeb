
package br.edu.iff.projetoEvento.controller.view;

import br.edu.iff.projetoEvento.model.Evento;
import br.edu.iff.projetoEvento.model.TipoStatusEventoEnum;
import br.edu.iff.projetoEvento.service.EventoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/eventos")
public class EventoViewController {
    
    @Autowired
    private EventoService service;
    
    @GetMapping
    public String getAll(Model model){
       
        model.addAttribute("eventos", service.findAll());
        
        return "/eventos";
    }
    @GetMapping(path = "/evento")
    public String cadastro(Model model){
        model.addAttribute("evento", new Evento());
        model.addAttribute("tipoStatusEvento", TipoStatusEventoEnum.values());
 
        return "/evento";
    }
    @PostMapping(path = "/evento")
    public String save(@Valid @ModelAttribute Evento evento, BindingResult result, Model model) {
        //valores de retorno padrão
        model.addAttribute("tiposEvento", TipoStatusEventoEnum.values());
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formEvento";
        }
        evento.setID(null);
        try {
            service.save(evento);
            model.addAttribute("msgSucesso", "Evento cadastrado com sucesso.");
            model.addAttribute("evento", new Evento());
            return "formEvento";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Evento", e.getMessage()));
            return "formEvento";
        }
    }
    
    @GetMapping(path = "/evento/{ID}")
    public String alterar (@PathVariable("ID") Long ID,Model model) {
        model.addAttribute("evento", service.findByID(ID));
        model.addAttribute("tiposEvento", TipoStatusEventoEnum.values());
        return "formEvento";
    }
    @PostMapping(path = "/evento/{ID}")
    public String update(@Valid @ModelAttribute Evento evento, BindingResult result, @PathVariable("ID") Long ID, Model model) {
        //valores de retorno padrão
        model.addAttribute("tipoEvento", TipoStatusEventoEnum.values());
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formEvento";
        }
        evento.setID(ID);
        try {
            service.update(evento);
            model.addAttribute("msgSucesso", "Evento atualizado com sucesso.");
            model.addAttribute("evento", evento);
            return "formEvento";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Evento", e.getMessage()));
            return "formEvento";
        }
    }
    @GetMapping(path = "/{ID}/deletar")
    public String deletar(@PathVariable("ID") Long ID) {
        service.delete(ID);
        return "redirect:/eventos";
    }
}
