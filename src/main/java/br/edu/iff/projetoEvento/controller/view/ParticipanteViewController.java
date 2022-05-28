
package br.edu.iff.projetoEvento.controller.view;

import br.edu.iff.projetoEvento.model.Participante;
import br.edu.iff.projetoEvento.service.ParticipanteService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/participantes")
public class ParticipanteViewController {
    
    @Autowired
    private ParticipanteService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("participantes", service.findAll());
        return "participantes";
    }
    @GetMapping(path = "/participante")
    public String cadastro(Model model) {
        model.addAttribute("participante", new Participante());
        return "formularioParticipante";
    }
    @PostMapping(path = "/participante")
    public String salvar (@Valid @ModelAttribute Participante participante, BindingResult result,
            @RequestParam("file") MultipartFile file, Model model) {
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formularioParticipante";
        }
        participante.setId(null);
        try {
            service.save(file, participante);
            model.addAttribute("msgSucesso", "Participante cadastrado com sucesso.");
            model.addAttribute("participante", new Participante());
            return "formularioParticipante";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("participante", e.getMessage()));
            return "formularioParticipante";
        }
    }
    @GetMapping(path = "/participante/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("participante", service.findById(id));
        return "formularioParticipante";
    }
    @PostMapping(path = "/participante/{id}")
    public String atualizar (@Valid @ModelAttribute Participante participante, BindingResult result,
            @PathVariable("id") Long id, @RequestParam("file") MultipartFile file, Model model) {
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formularioParticipante";
        }
        participante.setId(null);
        try {
            service.update(participante, file);
            model.addAttribute("msgSucesso", "Participante cadastrado com sucesso.");
            model.addAttribute("participante", participante);
            return "formularioParticipante";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("participante", e.getMessage()));
            return "formularioParticipante";
        }
    }
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/participantes";
    }
    
}
