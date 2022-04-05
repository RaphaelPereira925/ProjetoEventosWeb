
package br.edu.iff.projetoEvento.repository;

import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    @Query("SELECT f FROM Funcionario f Where f.CPF = :CPF")
    public List<Usuario> findbyFuncionarioCPF (@Param("CPF") String CPF);
    
    //public Funcionario findByEmail(String email);//Professor não sei se faz sentido fazer uma verificação por email sendo que email no meu exemplo é atributo de contato e não de pessoa.
}
