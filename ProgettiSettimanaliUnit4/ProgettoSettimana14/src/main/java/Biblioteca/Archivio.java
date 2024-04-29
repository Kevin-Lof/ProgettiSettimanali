package Biblioteca;

import java.util.Optional;

public interface Archivio {
    // Metodo per aggiungere un elemento all'archivio
    void add(Comuni c);

    // Metodo per rimuovere un elemento dall'archivio dato il suo ISBN
    void deleteISBN(Integer ISBN);

    // Metodo per ottenere un elemento dall'archivio dato il suo ISBN
    Optional<Comuni> getByISBN(Integer ISBN);

    // Metodo per ottenere un elemento dall'archivio dato l'anno di pubblicazione
    Optional<Comuni> getAnno(Integer anno);

    // Metodo per ottenere gli elementi dell'archivio di un determinato autore
    void getAutore(String autore);
}