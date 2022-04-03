
package br.edu.iff.projetoEvento.repository;

import br.edu.iff.projetoEvento.model.Ingresso;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long>{
    //SELECT DISTINCT(r) FROM Hotel h JOIN h.quartos q JOIN q.reservas r WHERE h.id = :hotelId
    @Query("SELECT e FROM Evento e Where e.ID = :ID")
    public List<Ingresso> findByIngressosByEvento(@Param("ID") Long ID);
    
    public List<Ingresso> findByFuncionarioId(Long funcionarioId, Pageable page);
    
    public List<Ingresso> findByParticianteId(Long participanteId, Pageable page);
    
    public List<Ingresso> findByParticipanteIdAndFuncionarioId(Long participanteId, Long funcionarioId, Pageable page);

}
