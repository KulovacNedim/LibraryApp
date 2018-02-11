

import java.util.Scanner;

public class LibraryLogic {
	
	private MethodsStock methodsStock = new MethodsStock();
	private Validation validation = new Validation();
	
	public LibraryLogic() {
		
	}

	public void bookAbook(Scanner sc) {

		int client = 0;
		int book = 0;

		methodsStock.listClients();

		boolean validClient = false;

		while (!validClient) {

			System.out.print("\nEnter client (ID) to borrow him a book: ");

			client = sc.nextInt();

			validClient = validation.validClient(client);

			if (!validClient) {
				System.out.println("Wrong input. Try again.");
			}
		}

		System.out.println();

		methodsStock.listBooks();

		boolean validBook = false;

		while (!validBook) {

			System.out.print("\nEnter book (ID) to borrow: ");

			book = sc.nextInt();

			validBook = validation.validBook(book);

			if (!validBook) {
				System.out.println("Wrong input. Try again.");
			}
		}

		giveBookToClient(client, book);

	}

	
	public void giveBookToClient(int client, int book) {

		int borrowedBookRestiction = validation.borrowedBookRestiction(client);
		
		boolean bookAvailable = validation.bookAvailable(book);

		if (borrowedBookRestiction >= 3) {
			System.out.println("\nYou already borrowed 3 books. You can't borrow another book.");
			
		} else if (!bookAvailable) {
			System.out.println("\nSelected book is not available.");
			
		} else {
			
			methodsStock.changeClientBorrowedBook(client, "add");
			methodsStock.setBookAvailibity(book, "false");
			methodsStock.saveHistory(client, book, "borrow");

			System.out.println("\nClient " + methodsStock.getClientName(client) + " borrowed a book \""
					+ methodsStock.getBookName(book) + "\".");
		}

	}

	
	public void returnAbook(Scanner sc) {
		
		boolean isThereAnyBorrowedBooks = validation.isThereAnyBorrowedBooks();

		if (isThereAnyBorrowedBooks) {

			methodsStock.listClientsWithBorrowedBooks();
			
			int client;

			boolean validInp = false;

			do {
				System.out.println("\nSelect client (ID) who want return a book: ");

				client = sc.nextInt();

				validInp = validation.validateInputForReturningBook(client);

				if (!validInp) {
					System.out.println("Wrong input. Try again.");
					
				} else {
					methodsStock.listBorrowedBooksForClient(client); 
					
				}
			} while (!validInp);

			boolean valid = false;
			
			int book = 0;

			do {
				System.out.print("\nEnter book ID client want to return: ");

				book = sc.nextInt();

				valid = validation.validateBorrowedBookByClient(client, book);

				if (!valid) {
					System.out.println("Wrong input. Try again.");
				}

			} while (!valid);

			methodsStock.changeClientBorrowedBook(client, "sub");
			methodsStock.setBookAvailibity(book, "true");
			methodsStock.saveHistory(client, book, "return");
			methodsStock.markBookAsReturnedInHistoryFile(client, book);

			System.out.println("\nClient " + methodsStock.getClientName(client) + " returned a book \""
					+ methodsStock.getBookName(book) + "\".");

		} else {
			System.out.println("There is not any borrowed book.");
			
		}

	}

}
