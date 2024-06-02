package it.epicode.repositories;

import it.epicode.entities.Eventi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventiRepository extends JpaRepository<Eventi, Long> {
}
