package it.epicode.services;

import io.jsonwebtoken.security.Password;
import it.epicode.Mapper;
import it.epicode.dto.LoginResponseDto;
import it.epicode.dto.RegisterUserDto;
import it.epicode.dto.RegisteredUserDto;
import it.epicode.entities.Ruolo;
import it.epicode.entities.Utente;
import it.epicode.exceptions.PersistEntityException;
import it.epicode.repositories.RuoloRepository;
import it.epicode.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class UtenteServiceImpl implements UtenteService {
    @Autowired
    UtenteRepository utenti;
    @Autowired
    RuoloRepository ruoloRep;
    @Autowired
    private AuthenticationManager auth;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    Mapper<RegisterUserDto, Utente> mapEntity;
    @Autowired
    Mapper<Utente, RegisteredUserDto> mapRegisteredUser;
    @Autowired
    Mapper<Utente, LoginResponseDto> mapLogin;

    @Override
    public List<Utente> getUtenti() {
        return utenti.findAll();
    }

    @Override
    public Optional<Utente> getUtente(Long id) {
        try {
            return utenti.findById(id);
        }catch (Exception ex){
            log.error(String.format("utente con id = non trovato",id),ex);
            return Optional.empty();
        }
    }

    @Override
    public Utente save(Utente utente) {
        try {
            return utenti.save(utente);
        }catch (Exception ex){
            log.error(String.format("salvataggio non riuscito di",utente),ex);
            return null;
        }
    }

    @Override
    public Optional<Utente> update(Long utenteId, Utente utente) {
        try{
            var ute = utenti.findById(utenteId).orElseThrow();
            ute.builder()
                    .withNome(ute.getNome())
                    .withEmail(ute.getEmail())
                    .withPassword(ute.getPassword())
                    .build();
            return Optional.of(utenti.save(ute));
        }
        catch (NoSuchElementException ex){
            log.error(String.format("utente con id = %s non trovato", utenteId), ex);
        }
        return Optional.empty();

    }

    @Override
    public Optional<Utente> delete(Long utenteId) {
         try{
            var ute = utenti.findById(utenteId).orElseThrow();
            utenti.delete(ute);
            return Optional.of(ute);
        }catch (NoSuchElementException ex){
            log.error(String.format("utente non trovato con id = %s",utenteId),ex);
            throw new RuntimeException("non trovo l'utente...");
        }catch (Exception ex){
            log.error(String.format("errore eliminazione utente con id = %s",utenteId),ex);
            throw new RuntimeException();
        }
    }

    @Override
    public RegisteredUserDto register(RegisterUserDto user) {
        try {
            var u = mapEntity.map(user);
            var p = encoder.encode(user.getPassword());
            log.info("Password encrypted: {}", p);
            u.setPassword(p);
            if (user.getRuolo() != null)
                Stream.of(user.getRuolo().split(",")).forEach(r -> u.getRoles().add(ruoloRep.findOneByName(r) //
                        .orElse(ruoloRep.save(Ruolo.builder().withNome(r).build()))));
            return mapRegisteredUser.map(utenti.save(u));
        } catch (Exception e) {
            log.error(String.format("Exception saving user %s", user), e);
            throw new PersistEntityException(user);
        }
    }

    @Override
    public Optional<LoginResponseDto> login(String username, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<RegisteredUserDto> get(long id) {
        return Optional.empty();
    }
}


