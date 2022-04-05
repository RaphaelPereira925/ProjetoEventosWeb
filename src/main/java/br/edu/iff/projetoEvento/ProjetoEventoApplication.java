package br.edu.iff.projetoEvento;

import br.edu.iff.projetoEvento.model.Contato;
import br.edu.iff.projetoEvento.model.Endereco;
import br.edu.iff.projetoEvento.model.Evento;
import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.model.Ingresso;
import br.edu.iff.projetoEvento.model.Participante;
import br.edu.iff.projetoEvento.model.TipoIngressoEnum;
import br.edu.iff.projetoEvento.model.TipoStatusEventoEnum;
import br.edu.iff.projetoEvento.repository.EventoRepository;
import br.edu.iff.projetoEvento.repository.FuncionarioRepository;
import br.edu.iff.projetoEvento.repository.IngressoRepository;
import br.edu.iff.projetoEvento.repository.ParticipanteRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    //private PermissaoRepository permissaoRepo;
    
    public static void main(String[] args) {
        SpringApplication.run(ProjetoEventoApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception {
        
        //Participante
        Participante p = new Participante();
        p.setNome("Larissa");
        p.setCPF("433.504.690-19");
        p.setRG("12.203.952-6");
        
        Contato c = new Contato();
        c.setCel("(22)99999-9999");
        c.setTel("(22)9999-9999");
        c.setEmail("larissa@instituicao.com.br");
        
        Endereco e = new Endereco();
        e.setRua("Alameda das Pedras");
        e.setNumero(36);
        e.setBairro("Jardim Esperança");
        e.setCidade("Indianópolis");
        e.setCEP("28090-000");
        
       pariticipanteRepo.save(e);
        
        //Funcionário
        
        Funcionario f = new Funcionario();
        f.setNome("Kátia Damaceno");
        f.setCPF("462.340.455-29");
        f.setRG("49.454.542-2");
        f.setSetor("Cadastramento");
        f.setSenha("12345678");
        
        Contato cf = new Contato();
        cf.setCel("(79)99323-9489");
        cf.setTel("(79)3853-4456");
        cf.setEmail("kdamaceno@instituicao.com.br");

        Endereco ef = new Endereco();
        ef.setRua("Rua Quatro");
        ef.setNumero(531);
        ef.setBairro("Industrial");
        ef.setCidade("Aracaju");
        ef.setCEP("49066-329");
        
        funcionarioRepo.save(f);
        
        //Evento
        
        Evento eE = new Evento();
        eE.setNome("Rock in Rio");
        eE.setOrganizacao("Eventos S.A.");
        eE.setQtdeIngresso(20000);
        eE.setStatus(TipoStatusEventoEnum.DISPONIVEL);
        eE.setDataHora(LocalDateTime.parse("2022-08-13 20:00:00", DateTimeFormatter.ISO_DATE));
                
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
        
        eventoRepo.save(eE);
        
        //Ingresso
        
        Ingresso i = new Ingresso();
        i.setTipoIngresso(TipoIngressoEnum.PIPOCA);
        i.setValor("356.00");
       
        ingressoRepo.save(i);
    }

    
    /*public class ProjetoHotelApplication implements CommandLineRunner{
    
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private FuncionarioRepository funcionarioRepo;
    @Autowired
    private HotelRepository hotelRepo;
    @Autowired
    private ReservaRepository reservaRepo;
    @Autowired
    private PermissaoRepository permissaoRepo;
    
    public static void main(String[] args) {
        SpringApplication.run(ProjetoHotelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Permissão
        Permissao p1 = new Permissao();
        p1.setNome("ADMIN");
        Permissao p2 = new Permissao();
        p2.setNome("FUNC");
        permissaoRepo.saveAll(List.of(p1, p2));
        
        //Cliente
        Cliente c1 = new Cliente();
        c1.setNome("Matheus");
        c1.setCpf("765.620.220-02");
        c1.setEmail("matheus@gmail.com");
        
        Telefone t1 = new Telefone();
        t1.setNumero("(22)99999-9999");
        Telefone t2 = new Telefone();
        t2.setNumero("(22)88888-8888");
        
        c1.setTelefones(List.of(t1,t2));
        
        Endereco end = new Endereco();
        end.setRua("Rua das flores");
        end.setNumero(123);
        end.setBairro("Parque das flores");
        end.setCidade("Campos");
        end.setCep("28000-000");
        
        c1.setEndereco(end);
        clienteRepo.save(c1);
        
        //Funcionario
        Funcionario f1 = new Funcionario();
        f1.setPermissoes(List.of(p1));
        f1.setNome("João");
        f1.setEmail("joao@gmail.com");
        f1.setCpf("325.291.890-05");
        f1.setEndereco(end);
        f1.setTelefones(List.of(t1,t2));
        f1.setSetor("Financeiro");
        f1.setSenha(new BCryptPasswordEncoder().encode("12345678"));
        
        funcionarioRepo.save(f1);
        
        //Hotel
        Hotel h1 = new Hotel();
        h1.setNome("Hotel Miraflores");
        h1.setCnpj("07.589.505/0001-82");
        h1.setEndereco(end);
        h1.setTelefones(List.of(t1,t2));
        
        Quarto q1 = new Quarto();
        q1.setNumero(101);
        q1.setTipo(TipoQuartoEnum.LUXO);
        q1.setQtdCamaCasal(1);
        q1.setQtdCamaSolteiro(1);
        Quarto q2 = new Quarto();
        q2.setNumero(102);
        q2.setTipo(TipoQuartoEnum.STANDARD);
        q2.setQtdCamaCasal(0);
        q2.setQtdCamaSolteiro(3);
        
        h1.setQuartos(List.of(q1,q2));
        
        hotelRepo.save(h1);
        
        // Reserva
        Reserva r1 = new Reserva();
        r1.setCliente(c1);
        r1.setFuncionario(f1);
        r1.setQuartos(List.of(q1, q2));
        
        r1.setDataHora(Calendar.getInstance());
        
        Calendar inicio = Calendar.getInstance();
        Calendar termino = Calendar.getInstance();
        inicio.set(2020, 10, 10);
        termino.set(2020, 10, 20);
        
        r1.setInicio(inicio);
        r1.setTermino(termino);
        
        reservaRepo.save(r1);
    }
*/
}
