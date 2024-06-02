package it.epicode.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class Utente extends EntityBase{
    @Column(length = 125, nullable = false)
    private String email;
    @Column(length = 125, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String nome;
    @Column(length = 125, nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private final List<Ruolo> roles = new ArrayList<>();
}
