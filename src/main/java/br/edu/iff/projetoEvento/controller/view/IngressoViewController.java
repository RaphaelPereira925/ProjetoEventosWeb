
package br.edu.iff.projetoEvento.controller.view;

import br.edu.iff.projetoEvento.model.Ingresso;
import br.edu.iff.projetoEvento.service.EventoService;
import br.edu.iff.projetoEvento.service.FuncionarioService;
import br.edu.iff.projetoEvento.service.IngressoService;
import br.edu.iff.projetoEvento.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("ingressos", serviIngresso.findAll(IDevento));
        model.addAttribute("IDevento", IDevento);
        return "ingressos";
    }
    @GetMapping(path = "/ingresso")
    public String cadastro(@PathVariable("IDevento") Long IDevento, Model model) {
        model.addAttribute("ingresso", new Ingresso());
        model.addAttribute("IDevento", IDevento);
        model.addAttribute("participantes", serviParticipante.findAll());
        model.addAttribute("funcionarios", serviFuncionario.findAll());
        
        return "formularioIngresso";
    }
    
}
