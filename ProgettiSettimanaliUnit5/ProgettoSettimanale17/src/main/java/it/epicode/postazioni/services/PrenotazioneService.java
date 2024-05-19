package it.epicode.postazioni.services;

import it.epicode.postazioni.entities.Prenotazione;
import it.epicode.postazioni.exceptions.NotFoundException;
import it.epicode.postazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    public List<Prenotazione> getAll() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione findById(long id) {
        return prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void save(Prenotazione prenotazione) {
        prenotazioneRepository.save(prenotazione);
    }

    public void findByIdAndDelete(Long id) {
        Prenotazione prenotazione = this.findById(id);
        prenotazioneRepository.delete(prenotazione);
    }

    public boolean isPostazioneLibera(long postazioneId, LocalDate data) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByPostazioneIdAndDataPrenotazione(postazioneId, data);
        return prenotazioni.isEmpty();
    }

    public boolean utenteHaPrenotazionePerData(long utenteId, LocalDate data) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByUtenteIdAndDataPrenotazione(utenteId, data);
        return !prenotazioni.isEmpty();
    }

    public Prenotazione creaPrenotazione(Prenotazione prenotazione) {
        if (!isPostazioneLibera(prenotazione.getPostazione().getId(), prenotazione.getDataPrenotazione())) {
            throw new RuntimeException("La postazione non è disponibile per questa data.");
        }

        if (utenteHaPrenotazionePerData(prenotazione.getUtente().getId(), prenotazione.getDataPrenotazione())) {
            throw new RuntimeException("L'utente ha già una prenotazione per questa data.");
        }

        return prenotazioneRepository.save(prenotazione);
    }
}
