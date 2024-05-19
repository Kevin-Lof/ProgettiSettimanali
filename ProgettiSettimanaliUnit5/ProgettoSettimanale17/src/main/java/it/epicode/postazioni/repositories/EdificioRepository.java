package it.epicode.postazioni.repositories;

import it.epicode.postazioni.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio,Long> {
}
