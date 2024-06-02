package it.epicode.controllers;

import it.epicode.entities.Utente;
import it.epicode.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {
    @Autowired
    UtenteService utenteService;

    @GetMapping
    public ResponseEntity<List<Utente>> getAllUtenti(){
        List<Utente> utenti = utenteService.getUtenti();
        return  ResponseEntity.ok(utenti);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id) {
        Optional<Utente> utente = utenteService.getUtente(id);
        return utente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Utente> updateUtente(@PathVariable Long id, @RequestBody Utente utente) {
        Optional<Utente> updatedUte = utenteService.update(id, utente);
        return updatedUte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Long id) {
        Optional<Utente> deletedUte= utenteService.delete(id);
        if (deletedUte.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
