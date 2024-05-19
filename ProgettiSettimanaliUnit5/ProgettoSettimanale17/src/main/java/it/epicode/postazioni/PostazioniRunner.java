package it.epicode.postazioni;

import it.epicode.postazioni.entities.Edificio;
import it.epicode.postazioni.entities.Postazione;
import it.epicode.postazioni.entities.Prenotazione;
import it.epicode.postazioni.entities.Utente;
import it.epicode.postazioni.enums.TipoPostazione;
import it.epicode.postazioni.services.EdificioService;
import it.epicode.postazioni.services.PostazioneService;
import it.epicode.postazioni.services.PrenotazioneService;
import it.epicode.postazioni.services.UtenteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class PostazioniRunner implements CommandLineRunner {

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private ApplicationContext ctx;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Utente utente1 = (Utente) ctx.getBean("utente1");
        Utente utente2 = (Utente) ctx.getBean("utente2");
        Utente utente3 = (Utente) ctx.getBean("utente3");

        Edificio edificio1 = (Edificio) ctx.getBean("edificio1");
        Edificio edificio2 = (Edificio) ctx.getBean("edificio2");

        Postazione postazione1 = (Postazione) ctx.getBean("postazione1");
        Postazione postazione2 = (Postazione) ctx.getBean("postazione2");
        Postazione postazione3 = (Postazione) ctx.getBean("postazione3");
        Postazione postazione4 = (Postazione) ctx.getBean("postazione4");

        // Salva gli utenti
        utenteService.save(utente1);
        utenteService.save(utente2);
        utenteService.save(utente3);

        // Salva gli edifici
        edificioService.save(edificio1);
        edificioService.save(edificio2);

        // Salva le postazioni e recupera le entità aggiornate con ID generato
        postazione1 = postazioneService.saveAndReturn(postazione1);
        postazione2 = postazioneService.saveAndReturn(postazione2);
        postazione3 = postazioneService.saveAndReturn(postazione3);
        postazione4 = postazioneService.saveAndReturn(postazione4);

        // Aggiungi prenotazioni di esempio
        Prenotazione prenotazione1 = Prenotazione.builder()
                .withUtente(utente1)
                .withPostazione(postazione1)
                .withDataPrenotazione(LocalDate.now().plusDays(1))
                .build();

        Prenotazione prenotazione2 = Prenotazione.builder()
                .withUtente(utente2)
                .withPostazione(postazione2)
                .withDataPrenotazione(LocalDate.now().plusDays(2))
                .build();

        Prenotazione prenotazione3 = Prenotazione.builder()
                .withUtente(utente3)
                .withPostazione(postazione3)
                .withDataPrenotazione(LocalDate.now().plusDays(3))
                .build();

        Prenotazione prenotazione4 = Prenotazione.builder()
                .withUtente(utente1)
                .withPostazione(postazione3)
                .withDataPrenotazione(LocalDate.now().plusDays(3))
                .build();

        try {
            prenotazioneService.creaPrenotazione(prenotazione1);
            prenotazioneService.creaPrenotazione(prenotazione2);
            prenotazioneService.creaPrenotazione(prenotazione3);
            prenotazioneService.creaPrenotazione(prenotazione4);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

        // Metodo per ricercare le postazioni indicando il tipo di postazione desiderato e la città di interesse
        List<Postazione> postazioniRomaPrivato = postazioneService.findByTipoAndCitta(TipoPostazione.PRIVATO, "Roma");
        log.info("Postazioni di tipo PRIVATO nella città di Roma:");
        postazioniRomaPrivato.forEach(postazione -> log.info(postazione.toString()));

        List<Postazione> postazioniRomaOpenspace = postazioneService.findByTipoAndCitta(TipoPostazione.OPENSPACE, "Roma");
        log.info("Postazioni di tipo OPENSPACE nella città di Roma:");
        postazioniRomaOpenspace.forEach(postazione -> log.info(postazione.toString()));
    }
}
