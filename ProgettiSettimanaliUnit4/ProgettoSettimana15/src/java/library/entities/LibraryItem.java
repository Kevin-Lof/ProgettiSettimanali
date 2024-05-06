package it.epicode.library.entities;

import jakarta.persistence.*;

/**
 * Un elemento che può essere gestito in libreria.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public abstract class LibraryItem extends BaseEntity {
	@Column(length = 13, nullable = false)
	private String isbn;
	@Column(length = 125, nullable = false)
	private String title;
	private int publicationYear;
	private int pages;

	public LibraryItem(String isbn, String title, int publicationYear, int pages) {
		this.isbn = isbn;
		this.title = title;
		this.publicationYear = publicationYear;
		this.pages = pages;
	}

	public LibraryItem() {
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public int getYear(){ return publicationYear; }

	@Override
	public String toString() {
		return String.format("LibraryItem [isbn=%s, title=%s, publicationYear=%s, pages=%s, insertedAt=%s, id=%s]",
				isbn, title, publicationYear, pages, getInsertedAt(), getId());
	}

}
