package it.epicode.library.dao;

import it.epicode.library.entities.LibraryItem;
import it.epicode.library.services.IsbValidatorImpl;

import java.util.List;


public interface LibraryItemDao {


    boolean isValidIsbn(String code);

    void addItem(LibraryItem item);


    List<LibraryItem> getAllItems();

    void removeItemByISBN(String isbn);

    LibraryItem getItemByISBN(String isbn);

    List<LibraryItem> getItemsByPublicationYear(int publicationYear);

    List<LibraryItem> getItemsByAuthor(String author);

    List<LibraryItem> getItemsByTitle(String title);

}
