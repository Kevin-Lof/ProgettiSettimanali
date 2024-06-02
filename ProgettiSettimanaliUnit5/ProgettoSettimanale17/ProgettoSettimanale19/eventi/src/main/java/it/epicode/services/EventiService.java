package it.epicode.services;

import it.epicode.entities.Eventi;
import it.epicode.entities.Utente;

import java.util.List;
import java.util.Optional;

public interface EventiService {
    List<Eventi> getEventi();

    Optional<Eventi> getEventi(Long id);

    Eventi save(Eventi eventi);

    Optional<Eventi> update(Long eventoId, Eventi eventi);

    Optional<Eventi> delete(Long eventoId);
}
