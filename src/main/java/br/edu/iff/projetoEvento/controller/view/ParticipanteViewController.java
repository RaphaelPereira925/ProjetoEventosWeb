
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
        return "formParticipante";
    }
}
