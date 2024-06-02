package it.epicode.controllers;

import it.epicode.entities.Eventi;
import it.epicode.entities.Utente;
import it.epicode.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventi")
public class EventiController {
    @Autowired
    private EventiService eventiService;

    @GetMapping
    public ResponseEntity<List<Eventi>> getAllEventi(){
        List<Eventi> eventi = eventiService.getEventi();
        return  ResponseEntity.ok(eventi);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Eventi> getEventoById(@PathVariable Long id) {
        Optional<Eventi> eventi = eventiService.getEventi(id);
        return eventi.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Eventi> createEvento(@RequestBody Eventi evento) {
        Eventi savedEventi = eventiService.save(evento);
        if (savedEventi != null) {
            return ResponseEntity.ok(savedEventi);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Eventi> updateEvento(@PathVariable Long id, @RequestBody Eventi evento) {
        Optional<Eventi> updatedEve = eventiService.update(id, evento);
        return updatedEve.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventi(@PathVariable Long id) {
        Optional<Eventi> deletedEve= eventiService.delete(id);
        if (deletedEve.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
