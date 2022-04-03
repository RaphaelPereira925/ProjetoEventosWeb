
package br.edu.iff.projetoEvento.repository;

import br.edu.iff.projetoEvento.model.Participante;
import br.edu.iff.projetoEvento.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long>{
    @Query("SELECT p FROM Participante p WHERE p.CPF = :CPF")
    public List<Usuario> findbyParticipanteCPF(@Param("CPF") String CPF);         
    
}
