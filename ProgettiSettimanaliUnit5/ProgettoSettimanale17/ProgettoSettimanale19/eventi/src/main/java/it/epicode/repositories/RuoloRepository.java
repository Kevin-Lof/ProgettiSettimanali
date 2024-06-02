package it.epicode.repositories;

import it.epicode.entities.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findOneByName(String roleName);
}
