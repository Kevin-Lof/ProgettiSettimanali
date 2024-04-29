package Biblioteca;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FileArchivio implements Archivio {
    private static final Logger logger = LoggerFactory.getLogger(FileArchivio.class);

    private ArrayList<Comuni> lista = new ArrayList<>(); // Lista degli elementi del catalogo
    private File f = new File("./catalogo.csv"); // File di archivio

    // Metodo per salvare la lista degli elementi su file
    public void save() {
        try {
            FileUtils.delete(f);
        } catch (IOException err) {
            IOException e = err;
            logger.error("Eccezione durante l'eliminazione", e);
        }
        lista.forEach(c -> {
            try {
                if (c instanceof Libro) {
                    // Se l'elemento è un libro, scrivi le informazioni del libro nel file
                    var lines = Arrays.asList(c.getCodiceISBN() + "," + c.getTitle() + "," + c.getAnno() + "," + c.getNumPagine() + "," + ((Libro) c).getAutore() + "," + ((Libro) c).getGenere());
                    FileUtils.writeLines(f, StandardCharsets.ISO_8859_1.name(), lines, true);

                } else if (c instanceof Rivista) {
                    // Se l'elemento è una rivista, scrivi le informazioni della rivista nel file
                    var lines = Arrays.asList(c.getCodiceISBN() + "," + c.getTitle() + "," + c.getAnno() + "," + c.getNumPagine() + "," + ((Rivista) c).getPeriodicita());
                    FileUtils.writeLines(f, StandardCharsets.ISO_8859_1.name(), lines, true);
                }

            } catch (IOException e) {
                logger.error("Eccezione:", e);
            }
        });
    }

    // Metodo per caricare la lista degli elementi dal file
    public void load() {
        leggiFile(f);
    }

    // Implementazione dei metodi definiti nell'interfaccia Archivio

    @Override
    public void add(Comuni c) {
        lista.add(c);
        save();
    }

    @Override
    public void deleteISBN(Integer ISBN) {
        lista.removeIf(el -> el.getCodiceISBN()==(ISBN));
        save();
    }

    @Override
    public Optional<Comuni> getByISBN(Integer ISBN) {
        return lista.stream().filter(el -> el.getCodiceISBN()==(ISBN)).findFirst();
    }

    @Override
    public Optional<Comuni> getAnno(Integer anno) {
        return lista.stream().filter(el -> el.getAnno()==(anno)).findFirst();
    }

    @Override
    public void getAutore(String autore) {
        lista.stream().filter(el -> el instanceof Libro && ((Libro) el).getAutore().equals(autore)).forEach(System.out::println);
    }

    // Altri metodi di utilità per ottenere elementi dal catalogo

    public List<Comuni> getByAutore(String autore) {
        return lista.stream().filter(el -> el instanceof Libro && ((Libro) el).getAutore().equals(autore)).toList();
    }

    public List<Comuni> getByAnno(Integer anno) {
        return lista.stream().filter(el -> el.getAnno()==(anno)).toList();
    }

    public ArrayList<Comuni> getLista() {
        return lista;
    }

    // Metodo per leggere le righe dal file e aggiungere gli elementi al catalogo
    public List<String> leggiFile(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] el = line.split(",");
                String titolo = el[1];
                String annoPubblicazioneStr = el[2];
                String numeroPagineStr = el[3];
                long codiceISBN = 0;
                int annoPubblicazione;
                int numeroPagine;

                try {
                    annoPubblicazione = Integer.parseInt(annoPubblicazioneStr);
                    numeroPagine = Integer.parseInt(numeroPagineStr);
                } catch (NumberFormatException e) {
                    System.err.println("Errore di conversione: " + e.getMessage());
                    continue;
                }
                if (el.length == 5) {
                    // Se la lunghezza è 5, l'elemento è una rivista
                    String periodicitaStr = el[4];
                    Periodicita periodicita = Periodicita.valueOf(periodicitaStr);
                    var rivista = new Rivista(codiceISBN, titolo, annoPubblicazione, numeroPagine, periodicita);
                    lista.add(rivista);
                } else if (el.length == 6) {
                    // Se la lunghezza è 6, l'elemento è un libro
                    String autore = el[4];
                    String genere = el[5];
                    var libro = new Libro(codiceISBN,titolo, annoPubblicazione, numeroPagine, autore, genere);
                    lista.add(libro);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
