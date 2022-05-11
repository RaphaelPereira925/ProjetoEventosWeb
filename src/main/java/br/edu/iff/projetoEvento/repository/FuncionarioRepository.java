
package br.edu.iff.projetoEvento.repository;

import br.edu.iff.projetoEvento.model.Funcionario;
import br.edu.iff.projetoEvento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    //@Query("SELECT f FROM Funcionario f Where f.CPF = :CPF")
    @Query("SELECT f FROM Funcionario f Where f.CPF = :CPF")
    public Usuario findbyCPF (@Param("CPF") String CPF);
}
