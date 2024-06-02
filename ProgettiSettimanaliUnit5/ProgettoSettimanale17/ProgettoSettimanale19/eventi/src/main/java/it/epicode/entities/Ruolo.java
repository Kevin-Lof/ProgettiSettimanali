package it.epicode.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "roles")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Ruolo extends EntityBase {
    @Column(length = 15, unique = true)
    private String nome;
    @ManyToMany(mappedBy = "roles")
    private final List<Utente> users = new ArrayList<>();
}
