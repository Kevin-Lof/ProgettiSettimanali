package it.epicode.services;

import it.epicode.entities.Eventi;
import it.epicode.entities.Utente;
import it.epicode.repositories.EventiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Slf4j
@Service
public class EventiServiceImpl implements EventiService{

    @Autowired
    EventiRepository eventirep;
    @Override
    public List<Eventi> getEventi() {
        return eventirep.findAll();
    }

    @Override
    public Optional<Eventi> getEventi(Long id) {
        try {
            return eventirep.findById(id);
        }catch (Exception ex){
            log.error(String.format("evento con id = non trovato",id),ex);
            return Optional.empty();
        }
    }

    @Override
    public Eventi save(Eventi eventi) {
        try {
            return eventirep.save(eventi);
        }catch (Exception ex){
            log.error(String.format("salvataggio non riuscito di",eventi),ex);
            return null;
        }
    }

    @Override
    public Optional<Eventi> update(Long eventoId, Eventi eventi) {
        try{
            var eve = eventirep.findById(eventoId).orElseThrow();
            eve.builder()
                    .withData(eve.getData())
                    .withDescrizione(eve.getDescrizione())
                    .withPostiDisponibili(eve.getPostiDisponibili())
                    .withLuogo(eve.getLuogo())
                    .withPostiPrenotati(eve.getPostiPrenotati())
                    .withTitolo(eve.getTitolo())
                    .build();
            return Optional.of(eventirep.save(eve));
        }
        catch (NoSuchElementException ex){
            log.error(String.format("evento con id = %s non trovato", eventoId), ex);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Eventi> delete(Long eventoId) {
        try{
            var dip = eventirep.findById(eventoId).orElseThrow();
            eventirep.delete(dip);
            return Optional.of(dip);
        }catch (NoSuchElementException ex){
            log.error(String.format("evento non trovato con id = %s",eventoId),ex);
            throw new RuntimeException("non trovo l'evento...");
        }catch (Exception ex){
            log.error(String.format("errore eliminazione evento con id = %s",eventoId),ex);
            throw new RuntimeException();
        }
    }
}
