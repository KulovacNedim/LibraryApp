

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BooksLogic {
	
	private MethodsStock methodsStock = new MethodsStock();
	
	
	public BooksLogic() {
		
	}

	public void addNewBook(Scanner sc) {

		Books newBook = new Books();

		System.out.print("Enter a book name: ");
		String bookName = sc.nextLine();

		System.out.print("Enter a author first name: ");
		String authorFirstName = sc.next();

		sc.nextLine();

		System.out.print("Enter a author last name: ");
		String authorLastName = sc.next();

		sc.nextLine();

		int id = nextBookID();

		newBook.setBookID(id);
		newBook.setBookName(bookName);
		newBook.setAuthorFirstName(authorFirstName);
		newBook.setAuthorLastName(authorLastName);
		newBook.setBookIn(true);

		saveBook(newBook);

		System.out.println("\nBook \"" + newBook.getBookName() + "\" registred.");
	}
	

	public void saveBook(Books newBook) {

		String file = "books.txt";

		try (PrintWriter output = new PrintWriter(new FileWriter(file, true))) {

			output.println(newBook.getBookID() + " " + newBook.getBookIn() + " " + newBook.getAuthorFirstName() + " "
					+ newBook.getAuthorLastName() + " " + newBook.getBookName());

		} catch (Exception e) {

		}
	}
	

	public int nextBookID() {

		ArrayList<String> records = methodsStock.historyDotTxtElements("books.txt");

		int maxID = 0;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];

			if (Integer.valueOf(tempID) > maxID) {

				maxID = Integer.valueOf(tempID);
			}

		}

		return maxID + 1;
	}
	

	public void bookStockOverview() {

		ArrayList<String> records = methodsStock.historyDotTxtElements("books.txt");

		int counter = 1;

		System.out.println("List of registred books:");

		System.out.printf("\n%2s  %-25s\t%-20s\t%-8s\n", "No", "Bok Name", "Author", "Status");
		System.out.println("-----------------------------------------------------------------");

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempIn = niz[1];
			String tempAuthorFirstName = niz[2];
			String tempAuthorLastName = niz[3];
			String tempBookName = "";

			for (int j = 4; j < niz.length; j++) {
				tempBookName = tempBookName + niz[j] + " ";
			}

			System.out.printf("\n%2s  %-25s\t%-20s\t%-8s", counter, tempBookName,
					(tempAuthorFirstName + " " + tempAuthorLastName),
					(tempIn.equals("true") ? "available" : "borrowed"));
			counter++;

		}
		
		System.out.println();
	}

}
