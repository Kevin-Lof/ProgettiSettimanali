package it.epicode.entities;


import jakarta.persistence.Entity;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Eventi extends EntityBase{

    private String titolo;

    private String descrizione;

    private String luogo;

    private String data;

    private Integer postiDisponibili;

    private Integer postiPrenotati;

    private boolean disponibiliPrenotazioni = true;
}
