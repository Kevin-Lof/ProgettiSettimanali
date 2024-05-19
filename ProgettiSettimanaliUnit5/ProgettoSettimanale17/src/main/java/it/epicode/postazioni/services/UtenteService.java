package it.epicode.postazioni.services;

import it.epicode.postazioni.entities.Utente;
import it.epicode.postazioni.exceptions.NotFoundException;
import it.epicode.postazioni.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    public List<Utente> getAll(){ return utenteRepository.findAll(); }

    public Utente findById(long id){ return utenteRepository.findById(id).orElseThrow(() ->new NotFoundException(id));}

    public void save(Utente utente) { utenteRepository.save(utente); }

    public void findByIdAndDelete(long id) {
        Utente utente = this.findById(id);
        utenteRepository.delete(utente);
    }
}
