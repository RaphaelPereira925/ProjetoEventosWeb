
package br.edu.iff.projetoEvento.security;

import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.model.Permissao;
import br.edu.iff.projetoEvento.repository.FuncionarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class FuncionarioDetailsService implements UserDetailsService{
    
    @Autowired
    private FuncionarioRepository repo;
    
    
    @Override
    public UserDetails loadUserByUsername(String CPF) throws UsernameNotFoundException {
        
        Funcionario funcionario = (Funcionario) repo.findbyCPF(CPF);
        
        if(funcionario == null){
            throw new UsernameNotFoundException("O CPF inserido não foi encontrado para nenhum funcionário."+CPF);
        }
        
        
        return new User(funcionario.getCPF(), funcionario.getSenha(), getAuthorities(funcionario.getPermissoes()));
    }
    
    private List <GrantedAuthority> getAuthorities(List<Permissao> lista){
        
        List<GrantedAuthority> l = new ArrayList<>();
        
        for(Permissao p : lista){
            l.add(new SimpleGrantedAuthority("ROLE_"+p.getNome()));
        }
        
        return l;
    }
}
