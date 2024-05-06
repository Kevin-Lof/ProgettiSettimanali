package it.epicode.library.entities;


import it.epicode.library.entities.constants.Tables;
import it.epicode.library.entities.enums.Frequency;
import jakarta.persistence.*;

@Entity
@Table(name = Tables.Names.MAGAZINES)
@DiscriminatorValue(Tables.Discriminators.MAGAZINES)
public class Magazine extends LibraryItem {

    @Enumerated
    private Frequency frequency;


    public Magazine() {}
    public Magazine(String isbn, String title, int publicationYear, int pages, Frequency frequency) {
        super(isbn, title, publicationYear, pages);
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }


    @Override
    public String toString() {
        String frequencyFormatted = getFrequency().name().toLowerCase();
        frequencyFormatted = Character.toUpperCase(frequencyFormatted.charAt(0)) + frequencyFormatted.substring(1);

        return "- RIVISTA - ISBN: " + getIsbn() + ", titolo: '" + getTitle() + "', periodicità: " + frequencyFormatted;
    }
}
