package it.epicode.postazioni.entities;

import it.epicode.postazioni.enums.TipoPostazione;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Postazione extends BaseEntity{

    private String descrizione;
    private TipoPostazione tipo;
    private int maxOccupanti;

    @ManyToOne
    private Edificio edificio;
    private boolean libera;

    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioni;

    public Postazione(String descrizione, TipoPostazione tipo, int maxOccupanti, Edificio edificio){
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
        this.libera = true;
    }

}
