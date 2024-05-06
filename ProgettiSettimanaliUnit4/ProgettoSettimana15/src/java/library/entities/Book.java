package it.epicode.library.entities;

import it.epicode.library.entities.constants.Tables;
import jakarta.persistence.*;

@Entity
@Table(name = Tables.Names.BOOKS)
@DiscriminatorValue(Tables.Discriminators.BOOKS)
public class Book extends LibraryItem {

	@Column(length = 80, nullable = false)
	private String author;

	@Column(length = 20, nullable = false)
	private String genre;




	public Book(){}

	public Book(String isbn, String title, int publicationYear, int pages, String author, String genre) {
		super(isbn, title, publicationYear, pages);
		this.author = author;
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	@Override
	public String toString() {
		return "- LIBRO - ISBN: " + getIsbn() + ", titolo: '" + getTitle() + "', autore: '" + getAuthor() + "', genere: '" + getGenre() + "', anno di pubblicazione: " + getYear();
	}
}
