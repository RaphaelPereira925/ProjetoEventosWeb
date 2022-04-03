
package br.edu.iff.projetoEvento.repository;

import br.edu.iff.projetoEvento.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
    
}
