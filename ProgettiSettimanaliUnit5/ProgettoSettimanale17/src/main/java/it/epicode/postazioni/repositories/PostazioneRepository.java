package it.epicode.postazioni.repositories;

import it.epicode.postazioni.entities.Postazione;
import it.epicode.postazioni.enums.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione,Long> {
    @Query("SELECT p FROM Postazione p JOIN FETCH p.edificio e WHERE p.tipo = :tipo AND e.citta = :citta")
    List<Postazione> findByTipoAndCitta(@Param("tipo") TipoPostazione tipo, @Param("citta") String citta);
}
