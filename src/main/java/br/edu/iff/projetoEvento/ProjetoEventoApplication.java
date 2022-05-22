package br.edu.iff.projetoEvento;

import br.edu.iff.projetoEvento.controller.view.ArquivosController;
import br.edu.iff.projetoEvento.model.Contato;
import br.edu.iff.projetoEvento.model.Endereco;
import br.edu.iff.projetoEvento.model.Evento;
import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.model.Ingresso;
import br.edu.iff.projetoEvento.model.Participante;
import br.edu.iff.projetoEvento.model.Permissao;
import br.edu.iff.projetoEvento.model.TipoIngressoEnum;
import br.edu.iff.projetoEvento.model.TipoStatusEventoEnum;
import br.edu.iff.projetoEvento.repository.EventoRepository;
import br.edu.iff.projetoEvento.repository.FuncionarioRepository;
import br.edu.iff.projetoEvento.repository.IngressoRepository;
import br.edu.iff.projetoEvento.repository.ParticipanteRepository;
import br.edu.iff.projetoEvento.repository.PermissaoRepository;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetoEventoApplication implements CommandLineRunner{
    
    @Autowired
    private ParticipanteRepository pariticipanteRepo;
    @Autowired
    private FuncionarioRepository funcionarioRepo;
    @Autowired
    private EventoRepository eventoRepo;
    @Autowired
    private IngressoRepository ingressoRepo;
    @Autowired
    private PermissaoRepository permissaoRepo;
    
    public static void main(String[] args) {
        SpringApplication.run(ProjetoEventoApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception {
        //Permissão
        Permissao P1 = new Permissao();
        P1.setNome("ADMIN");
        
        Permissao P2 = new Permissao();
        P2.setNome("FUNC");
        
        ArrayList<Permissao> a = new ArrayList();
        a.add(P2);
        a.add(P1);
        
        permissaoRepo.saveAll(a);
        
        //Participante
        Participante p = new Participante();
        p.setNome("Larissa");
        p.setCPF("433.504.690-19");
        p.setRG("12.203.952-6");
        
        Contato c = new Contato();
        c.setCel("(21)99833-2620");
        c.setTel("(21)2735-0602");
        c.setEmail("larissa@instituicao.com.br");
        
        Endereco e = new Endereco();
        e.setRua("Alameda das Pedras");
        e.setNumero(36);
        e.setBairro("Jardim Esperança");
        e.setCidade("Indianópolis");
        e.setCEP("28090-000");
        
       p.setContato(c);
       p.setEndereco(e);
       //Não sei necessariamente se setar documentos seria nesse sentido
       p.setDocumentos(MediaType.MULTIPART_FORM_DATA_VALUE);
       pariticipanteRepo.save(p);
          
        //Funcionário
        
        Funcionario f = new Funcionario();
        f.setNome("Kátia Damaceno");
        f.setCPF("462.340.455-29");
        f.setRG("49.454.542-2");
        f.setSetor("Cadastramento");
        f.setSenha(new BCryptPasswordEncoder().encode("12345678"));
        f.setPermissoes(a);
        
        Contato cf = new Contato();
        cf.setCel("(22)99323-9489");
        cf.setTel("(22)3853-4456");
        cf.setEmail("kdamaceno@instituicao.com.br");

        Endereco ef = new Endereco();
        ef.setRua("Rua Quatro");
        ef.setNumero(531);
        ef.setBairro("Industrial");
        ef.setCidade("Aracaju");
        ef.setCEP("49066-329");
        
        f.setContato(cf);
        f.setEndereco(ef);
        funcionarioRepo.save(f);
       
        //Evento
        
        Evento eE = new Evento();
        eE.setNome("Rock in Rio");
        eE.setOrganizacao("Eventos S.A.");
        eE.setQtdeIngresso(20000);
        eE.setStatus(TipoStatusEventoEnum.DISPONIVEL);
        eE.setDataHora(LocalDateTime.parse("2022-08-13T20:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                
        Contato ce = new Contato();
        ce.setCel("(21)99502-8558");
        ce.setTel("(21)3757-3574");
        ce.setEmail("rir@rir.com.br");
        
        Endereco ee = new Endereco();
        ee.setRua("Rua Eduardo da Costa");
        ee.setNumero(791);
        ee.setBairro("Parque Ipanema");
        ee.setCidade("Queimados");
        ee.setCEP("26321-360");
        
        eE.setContato(ce);
        eE.setEndereco(ee);
        eventoRepo.save(eE);
        
        //Ingresso
        
        Ingresso i = new Ingresso();
        i.setTipoIngresso(TipoIngressoEnum.PIPOCA);
        i.setValor(Float.parseFloat("356.00"));
       
        i.setEvento(eE);
        i.setFuncionario(f);
        i.setParticipante(p);
        ingressoRepo.save(i);
    }
    
}
