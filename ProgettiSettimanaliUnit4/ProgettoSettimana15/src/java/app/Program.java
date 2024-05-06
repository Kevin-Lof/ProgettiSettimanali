package it.epicode.app;

import it.epicode.library.dao.*;
import it.epicode.library.entities.*;
import it.epicode.library.entities.enums.Frequency;
import it.epicode.library.services.IsbValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

	private static final Logger log = LoggerFactory.getLogger(Program.class);

	public static void main(String[] args) {
		IsbValidatorImpl isbnValidator = new IsbValidatorImpl();
		LibraryItemDaoImpl libraryItemDao = new LibraryItemDaoImpl();
		LoanDao loanDao = new LoanDaoImpl();
		UserDaoImpl userDao = new UserDaoImpl();



		// Menu
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Menu Principale:");
			System.out.println("1. Gestione Pubblicazioni");
			System.out.println("2. Gestione Utenti e Prestiti");
			System.out.println("0. Esci");
			System.out.print("Scelta: ");
			choice = scanner.nextInt();

			switch (choice) {
				case 1:
					manageItemsMenu(libraryItemDao, isbnValidator);
					break;
				case 2:
					manageLoansMenu(loanDao, userDao, libraryItemDao);
					break;
				case 0:
					System.out.println("Uscita...");
					break;
				default:
					System.out.println("Scelta non valida!");
			}
		} while (choice != 0);
		scanner.close();
	}

	private static void manageItemsMenu(LibraryItemDao libraryItemDao, IsbValidatorImpl isbnValidator) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\nMenu Gestione Articoli:");
			System.out.println("1. Aggiungi articolo al catalogo");
			System.out.println("2. Rimuovi articolo dal catalogo dato un codice ISBN");
			System.out.println("3. Ricerca per ISBN");
			System.out.println("4. Ricerca per anno di pubblicazione");
			System.out.println("5. Ricerca per autore");
			System.out.println("6. Ricerca per titolo");
			System.out.println("7. Visualizza tutti gli articoli nel catalogo");
			System.out.println("0. Torna al Menu Principale");
			System.out.print("Scelta: ");
			choice = scanner.nextInt();

			switch (choice) {
				case 1:
					addLibraryItem(libraryItemDao, isbnValidator);
					break;
				case 2:
					removeLibraryItem(libraryItemDao);
					break;
				case 3:
					searchByISBN(libraryItemDao);
					break;
				case 4:
					searchByPublicationYear(libraryItemDao);
					break;
				case 5:
					searchByAuthor(libraryItemDao);
					break;
				case 6:
					searchByTitle(libraryItemDao);
					break;
				case 7:
					displayAllItems(libraryItemDao);
					break;
				case 0:
					System.out.println("Tornando al Menu Principale...");
					break;
				default:
					System.out.println("Scelta non valida!");
			}
		} while (choice != 0);
	}

	private static void manageLoansMenu(LoanDao loanDao, UserDao userDao, LibraryItemDao libraryItemDao) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\nMenu Gestione Utenti e Prestiti:");
			System.out.println("1. Aggiungi un utente");
			System.out.println("2. Aggiungi un prestito");
			System.out.println("3. Ricerca degli articoli attualmente in prestito dato un numero di tessera utente");
			System.out.println("4. Ricerca di tutti i prestiti scaduti e non ancora restituiti");
			System.out.println("0. Torna al Menu Principale");
			System.out.print("Scelta: ");
			choice = scanner.nextInt();

			switch (choice) {
				case 1:
					addUser(userDao);
					break;
				case 2:
					addLoan(loanDao, libraryItemDao, userDao);
					break;
				case 3:
					searchLoansByTessera(loanDao);
					break;
				case 4:
					searchOverdueLoans(loanDao);
					break;
				case 0:
					System.out.println("Tornando al Menu Principale...");
					break;
				default:
					System.out.println("Scelta non valida!");
			}
		} while (choice != 0);
	}

	private static void addLibraryItem(LibraryItemDao libraryItemDao, IsbValidatorImpl isbnValidator) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Inserisci i dettagli del nuovo articolo:");
			System.out.print("ISBN: ");
			String isbn = scanner.nextLine();
			System.out.print("Titolo: ");
			String title = scanner.nextLine();
			System.out.print("Anno di pubblicazione: ");
			int publicationYear = scanner.nextInt();
			System.out.print("Numero di pagine: ");
			int pages = scanner.nextInt();
			scanner.nextLine();

			System.out.println("Seleziona il tipo di pubblicazione:");
			System.out.println("1. Libro");
			System.out.println("2. Rivista");
			System.out.print("Scelta: ");
			int elementType = scanner.nextInt();
			scanner.nextLine();

			LibraryItem newItem = null;
			if (elementType == 1) {
				System.out.print("Autore: ");
				String author = scanner.nextLine();
				System.out.print("Genere: ");
				String genre = scanner.nextLine();
				newItem = new Book(isbn, title, publicationYear, pages, author, genre);
			} else if (elementType == 2) {
				System.out.println("Seleziona la periodicità:");
				for (Frequency frequency : Frequency.values()) {
					System.out.println(frequency.ordinal() + 1 + ". " + frequency);
				}
				System.out.print("Scelta: ");
				int frequencyChoice = scanner.nextInt();
				Frequency frequency = Frequency.values()[frequencyChoice - 1];
				newItem = new Magazine(isbn, title, publicationYear, pages, frequency);
			} else {
				System.out.println("Tipo di pubblicazione non valido! Elemento non aggiunto.");
				return;
			}

			if (isbnValidator.isValidIsbn(isbn)) {
				libraryItemDao.addItem(newItem);
				System.out.println("Articolo aggiunto al catalogo: " + newItem);
			} else {
				System.out.println("ISBN non valido! Articolo non aggiunto.");
			}
		} catch (Exception e) {
			System.out.println("Errore durante l'aggiunta dell'articolo al catalogo");
		}
	}

	private static void removeLibraryItem(LibraryItemDao libraryItemDao) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Inserisci il codice ISBN dell'articolo da rimuovere: ");
		String isbnToRemove = scanner.nextLine();
		libraryItemDao.removeItemByISBN(isbnToRemove);
		System.out.println("Articolo con isbn '" + isbnToRemove + "' rimosso dal catalogo");
	}

	private static void searchByISBN(LibraryItemDao libraryItemDao) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Inserisci il codice ISBN da cercare: ");
		String isbnToSearch = scanner.nextLine();
		LibraryItem foundItem = libraryItemDao.getItemByISBN(isbnToSearch);
		if (foundItem != null) {
			System.out.println("Elemento trovato con ISBN '" + isbnToSearch + "': " + foundItem);
		} else {
			System.out.println("\u001B[31m" + "Nessun elemento trovato con ISBN '" + isbnToSearch + "'\n \u001B[0m");
		}
	}

	private static void searchByPublicationYear(LibraryItemDao libraryItemDao) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Inserisci l'anno di pubblicazione da cercare: ");
		int yearToSearch = scanner.nextInt();
		List<LibraryItem> itemsByYear = libraryItemDao.getItemsByPublicationYear(yearToSearch);
		if (!itemsByYear.isEmpty()) {
			System.out.println("Articoli trovati per anno di pubblicazione " + yearToSearch + ": ");
			for (LibraryItem item : itemsByYear) {
				System.out.println("- " + item);
			}
		} else {
			System.out.println("\u001B[31m" + "Nessun elemento trovato per l'anno di pubblicazione '" + yearToSearch + "'\n \u001B[0m");
		}
	}

	private static void searchByAuthor(LibraryItemDao libraryItemDao) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Inserisci l'autore da cercare: ");
		String authorToSearch = scanner.nextLine();
		List<LibraryItem> itemsByAuthor = libraryItemDao.getItemsByAuthor(authorToSearch);
		if (!itemsByAuthor.isEmpty()) {
			System.out.println("Articoli trovati per autore '" + authorToSearch + "': ");
			for (LibraryItem item : itemsByAuthor) {
				System.out.println("- " + item);
			}
		} else {
			System.out.println("\u001B[31m" + "Nessun elemento trovato per autori il cui nome contenga '" + authorToSearch + "'\n \u001B[0m");
		}
	}

	private static void searchByTitle(LibraryItemDao libraryItemDao) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Inserisci il titolo o parte di esso da cercare: ");
		String titleToSearch = scanner.nextLine();
		List<LibraryItem> itemsByTitle = libraryItemDao.getItemsByTitle(titleToSearch);
		if (!itemsByTitle.isEmpty()) {
			System.out.println("Articoli trovati con titolo contenente '" + titleToSearch + "': ");
			for (LibraryItem item : itemsByTitle) {
				System.out.println("- " + item);
			}
		} else {
			System.out.println("\u001B[31m" + "Nessun elemento trovato per il titolo '" + titleToSearch + "'\n \u001B[0m");
		}
	}



	private static void displayAllItems(LibraryItemDao libraryItemDao) {
		List<LibraryItem> allItems = libraryItemDao.getAllItems();
		if (allItems.isEmpty()) {
			System.out.println("Nessun articolo nel catalogo.");
		} else {
			System.out.println("Articolo nel catalogo:");
			for (LibraryItem item : allItems) {
				System.out.println(item);
			}
		}
	}

	private static void addUser(UserDao userDao) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Aggiungi un utente:");
			System.out.print("Nome: ");
			String nome = scanner.nextLine();
			System.out.print("Cognome: ");
			String cognome = scanner.nextLine();
			System.out.print("Data di nascita (formato yyyy-MM-dd): ");
			String dataDiNascitaStr = scanner.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataDiNascita = sdf.parse(dataDiNascitaStr);
			System.out.print("Numero tessera: ");
			int numeroTessera = scanner.nextInt();

			User newUser = new User(nome, cognome, dataDiNascita, numeroTessera);
			userDao.addUser(newUser);
			log.debug("Utente aggiunto: {}", newUser);
		} catch (Exception e) {
			log.error("Errore durante l'aggiunta dell'utente", e);
		}
	}
	private static void addLoan(LoanDao loanDao, LibraryItemDao libraryItemDao, UserDao userDao) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Aggiungi un prestito:");

		System.out.print("Numero di tessera utente: ");
		int tesseraNumber = scanner.nextInt();
		scanner.nextLine();

		User user = userDao.findUserByTessera(tesseraNumber);
		if (user == null) {
			System.out.println("Utente non trovato!");
			return;
		}

		System.out.print("ISBN dell'articolo: ");
		String isbn = scanner.nextLine();

		LibraryItem item = libraryItemDao.getItemByISBN(isbn);
		if (item == null) {
			System.out.println("Articolo non trovato!");
			return;
		}

		Date startDate = new Date();
		Loan newLoan = new Loan(user, item, startDate);

		try {
			loanDao.addLoan(newLoan);
			System.out.println("Prestito aggiunto con successo.");
		} catch (Exception e) {
			System.out.println("Errore durante l'aggiunta del prestito: " + e.getMessage());
			e.printStackTrace();
		}
	}





	private static void searchLoansByTessera(LoanDao loanDao) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Inserisci il numero di tessera utente: ");
		int tesseraNumber = scanner.nextInt();

		List<Loan> loansByTessera = loanDao.getLoansByUserTessera(tesseraNumber);
		if (loansByTessera.isEmpty()) {
			System.out.println("Nessun prestito trovato per il numero di tessera: " + tesseraNumber);
		} else {
			System.out.println("Prestiti trovati per il numero di tessera " + tesseraNumber + ":");
			displayLoans(loansByTessera);
		}
	}

	private static void displayLoans(List<Loan> loansByTessera) {
		if (loansByTessera.isEmpty()) {
			System.out.println("Nessun prestito trovato.");
			return;
		}

		User currentUser = null;

		for (Loan loan : loansByTessera) {

			if (currentUser == null || !currentUser.equals(loan.getUser())) {
				currentUser = loan.getUser();
				System.out.println("\nUtente: " + currentUser.getNome() + " " + currentUser.getCognome() +
						" (Numero tessera: " + currentUser.getNumeroTessera() + ")");
			}


			System.out.println("Dettagli del prestito:");
			System.out.println("Data di inizio: " + formatDate(loan.getStartDate()) +
					", Data di restituzione prevista: " + formatDate(loan.getExpectedReturnDate()));

			LibraryItem item = loan.getItem();
			printItemDetails(item);
			System.out.println();
		}
	}

	private static void searchOverdueLoans(LoanDao loanDao) {
		List<Loan> overdueLoans = loanDao.getOverdueLoans();
		if (overdueLoans.isEmpty()) {
			System.out.println("Non ci sono prestiti scaduti non ancora restituiti.");
		} else {
			System.out.println("Prestiti scaduti e non ancora restituiti:");
			for (Loan loan : overdueLoans) {
				User user = loan.getUser();
				LibraryItem item = loan.getItem();
				System.out.println("\nUtente: " + user.getNome() + " " + user.getCognome() +
						" (Numero tessera: " + user.getNumeroTessera() + ")");
				System.out.println("Data di inizio prestito: " + formatDate(loan.getStartDate()) +
						", Data di restituzione prevista: " + formatDate(loan.getExpectedReturnDate()));

				// Dettagli del prestito
				printItemDetails(item);
			}
		}
	}

	private static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}

	private static void printItemDetails(LibraryItem item) {
		if (item instanceof Book book) {
            System.out.println("- LIBRO - ISBN: " + book.getIsbn() +
					", titolo: '" + book.getTitle() +
					"', autore: '" + book.getAuthor() +
					"', genere: '" + book.getGenre() +
					"', anno di pubblicazione: " + book.getYear());
		} else if (item instanceof Magazine magazine) {
            System.out.println("- RIVISTA - ISBN: " + magazine.getIsbn() +
					", titolo: '" + magazine.getTitle() +
					"', periodicità: '" + magazine.getFrequency() +
					"', anno di pubblicazione: " + magazine.getYear());
		}
	}

}
