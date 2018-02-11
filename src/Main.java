

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Library library = new Library("Books of the world!");

		System.out.println("\n\n\tWelcome to \"" + library.getName() + "\" aplication.\n");

		System.out.println("\n****************************************************************");

		aplicationMenu(sc, library);

	}

	private static void aplicationMenu(Scanner sc, Library library) {
		
		ClientsLogic clientsLogic = new ClientsLogic();
		BooksLogic booksLogic = new BooksLogic();
		LibraryLogic libraryLogic = new LibraryLogic();

		System.out.println("\n\tWhat do you want to do next: \n");

		System.out.println("	1) Add new client");
		System.out.println("	2) Register new book");
		System.out.println("	3) Book (borrow) a book");
		System.out.println("	4) Return a book");
		System.out.println("	5) Client usage overview");
		System.out.println("	6) Book stock overview");

		int option = sc.nextInt();

		switch (option) {

		case 1:
			sc.nextLine();

			System.out.println("\n****************************************************************\n");

			clientsLogic.createCliant(sc);

			System.out.println("\n****************************************************************");

			aplicationMenu(sc, library);

			break;

		case 2:
			sc.nextLine();

			System.out.println("\n****************************************************************\n");

			booksLogic.addNewBook(sc);

			System.out.println("\n****************************************************************");

			aplicationMenu(sc, library);

			break;
		case 3:

			System.out.println("\n****************************************************************\n");

			libraryLogic.bookAbook(sc);

			System.out.println("\n****************************************************************");

			aplicationMenu(sc, library);

			break;
		case 4:

			System.out.println("\n****************************************************************\n");

			libraryLogic.returnAbook(sc);

			System.out.println("\n****************************************************************");

			aplicationMenu(sc, library);

			break;
		case 5:

			System.out.println("\n****************************************************************\n");

			clientsLogic.clientUsageOverview(sc);

			System.out.println("\n****************************************************************");

			aplicationMenu(sc, library);

			break;
		case 6:

			System.out.println("\n****************************************************************\n");

			booksLogic.bookStockOverview();

			System.out.println("\n****************************************************************");

			aplicationMenu(sc, library);

			break;
		default:

			System.out.println("Wrong input! \nTry again. Enter option 1 - 6.");

			aplicationMenu(sc, library);

			break;
		}
	}

}
