package it.epicode.library.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class User extends BaseEntity {

    @Column(length = 20, nullable = false)
    public String nome;

    @Column(length = 20, nullable = false)
    public String cognome;

    @Temporal(TemporalType.DATE)
    public Date dataDiNascita;

    @Column(nullable = false, unique = true)
    public int numeroTessera;

    public User(){}
    public User(String nome, String cognome, Date dataDiNascita, int numeroTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.numeroTessera = numeroTessera;
    }

    public String getNome() {
        return nome;
    }



    public String getCognome() {
        return cognome;
    }


    public int getNumeroTessera() {
        return numeroTessera;
    }



    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", numeroTessera=" + numeroTessera +
                '}';
    }
}
