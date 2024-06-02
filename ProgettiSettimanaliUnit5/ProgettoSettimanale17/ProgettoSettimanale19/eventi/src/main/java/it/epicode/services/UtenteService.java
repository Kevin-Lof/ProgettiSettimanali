package it.epicode.services;

import it.epicode.dto.LoginResponseDto;
import it.epicode.dto.RegisterUserDto;
import it.epicode.dto.RegisteredUserDto;
import it.epicode.entities.Utente;

import java.util.List;
import java.util.Optional;

public interface UtenteService {

    List<Utente> getUtenti();

    Optional<Utente> getUtente(Long id);

    Utente save(Utente utente);

    Optional<Utente> update(Long utenteId, Utente utente);

    Optional<Utente> delete(Long utenteId);

    RegisteredUserDto register(RegisterUserDto user);

    Optional<LoginResponseDto> login(String username, String password);

    Optional<RegisteredUserDto> get(long id);
}
