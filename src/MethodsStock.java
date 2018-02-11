

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MethodsStock {
	
	public MethodsStock() {
		
	}

	public ArrayList<String> historyDotTxtElements(String file) {

		String line = null;

		ArrayList<String> records = new ArrayList<String>();

		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));

			try {
				while ((line = bufferedReader.readLine()) != null) {
					records.add(line);
				}

				bufferedReader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return records;
	}

	
	private void deleteFileContent(String file) {

		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	public void changeClientBorrowedBook(int client, String addSub) {

		String file = "clients.txt";

		ArrayList<String> records = this.historyDotTxtElements("clients.txt");

		deleteFileContent("clients.txt");

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];
			String tempFirstName = niz[1];
			String tempLastName = niz[2];
			String tempBorrowed = niz[3];

			if (Integer.valueOf(tempID) == client) {
				String tempStr = "";

				if (addSub.equals("add")) {
					tempStr = "" + (Integer.valueOf(tempBorrowed) + 1);
				} else {
					tempStr = "" + (Integer.valueOf(tempBorrowed) - 1);
				}

				try (PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
					output.println(tempID + " " + tempFirstName + " " + tempLastName + " " + tempStr);

				} catch (Exception e) {

				}

			} else {
				try (PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
					output.println(tempID + " " + tempFirstName + " " + tempLastName + " " + tempBorrowed);

				} catch (Exception e) {

				}
			}

		}

	}

	
	public void setBookAvailibity(int book, String trueFalse) {

		ArrayList<String> records = this.historyDotTxtElements("books.txt");

		deleteFileContent("books.txt");

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];
			String tempIn = niz[1];
			String tempAuthorFirstName = niz[2];
			String tempAuthorLastName = niz[3];
			String tempBookName = "";
			String temp = "";

			for (int j = 4; j < niz.length; j++) {
				tempBookName = tempBookName + niz[j] + " ";
			}

			if (Integer.valueOf(tempID) == book) {
				temp = trueFalse;
			} else {
				temp = tempIn;
			}

			try (PrintWriter output = new PrintWriter(new FileWriter("books.txt", true))) {
				output.println(tempID + " " + temp + " " + tempAuthorFirstName + " " + tempAuthorLastName + " "
						+ tempBookName);

			} catch (Exception e) {

			}

		}

	}

	
	public void saveHistory(int client, int book, String borrowReturn) {
		String file = "history.txt";

		final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		try (PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
			output.println(book + " " + client + " " + sdf.format(date) + " " + borrowReturn + " "
					+ (borrowReturn.equals("borrow") ? "B" : "R"));

		} catch (Exception e) {

		}

	}

	
	public void listClients() {

		ArrayList<String> records = this.historyDotTxtElements("clients.txt");

		System.out.println("List of clients: \n");
		System.out.printf("%-3s\t%-17s\t%-2s\n", "ID", "Client Name", "Borrowed books");
		System.out.println("----------------------------------------------");

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];
			String tempFirstName = niz[1];
			String tempLastName = niz[2];
			String tempNuberOfBooksBrrewed = niz[3];

			System.out.printf("%-3s\t%-17s\t%-2s\n", tempID, (tempFirstName + " " + tempLastName),
					tempNuberOfBooksBrrewed);

		}

	}

	
	public void listBooks() {

		ArrayList<String> records = this.historyDotTxtElements("books.txt");

		System.out.println("List of books: \n");
		System.out.printf("%-3s\t%-25s\t%-20s\t%-8s\n", "ID", "Book Name", "Author", "in / out");
		System.out.println("-------------------------------------------------------------------------");

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];
			String tempInOut = niz[1];
			String tempAuthorFirstName = niz[2];
			String tempAuthorLastName = niz[3];
			String tempAuthor = tempAuthorFirstName + " " + tempAuthorLastName;
			String tempBookName = "";

			for (int j = 4; j < niz.length; j++) {
				tempBookName = tempBookName + niz[j] + " ";
			}

			System.out.printf("%-3s\t%-25s\t%-20s\t%-8s\n", tempID, tempBookName, tempAuthor,
					(tempInOut.equals("true") ? "available" : "borrowed"));

		}
	}

	
	public void listBorrowedBooksForClient(int client) {

		ArrayList<String> records = this.historyDotTxtElements("history.txt");

		System.out.println("\nSelected client (" + getClientName(client) + ") borrowed next books: \n");
		System.out.printf("%-11s\t%-12s\t%-20s\n", "Date", "Book ID", "Book Name");
		System.out.println("----------------------------------------------------------");
		
		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempBookID = niz[0];
			String tempClientID = niz[1];
			String tempDate = niz[2];
			String tempMark = niz[5];

			if (Integer.valueOf(tempClientID) == client && tempMark.equals("B")) {
				System.out.printf("%-11s\t%-12s\t%-20s\n", tempDate, tempBookID,
						getBookName(Integer.valueOf(tempBookID)));
			}

		}

	}

	
	public String getBookName(int bookID) {

		ArrayList<String> records = this.historyDotTxtElements("books.txt");

		String toReturn = "";

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];
			String tempBookName = "";

			for (int j = 4; j < niz.length; j++) {
				tempBookName = tempBookName + niz[j] + " ";
			}

			if (Integer.valueOf(tempID) == bookID) {

				toReturn = toReturn + tempBookName;
			}

		}
		return toReturn.substring(0, toReturn.length() - 1);
	}

	
	public void markBookAsReturnedInHistoryFile(int client, int book) {

		ArrayList<String> records = this.historyDotTxtElements("history.txt");

		deleteFileContent("history.txt");

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempBookID = niz[0];
			String tempClientID = niz[1];
			String tempDate = niz[2];
			String tempTime = niz[3];
			String tempBR = niz[4];
			String tempMark = niz[5];
			String tempMarkChanged = "";

			if (Integer.valueOf(tempClientID) == client && Integer.valueOf(tempBookID) == book
					&& tempMark.equals("B")) {
				tempMarkChanged = "R";
			} else {
				tempMarkChanged = tempMark;
			}

			try (PrintWriter output = new PrintWriter(new FileWriter("history.txt", true))) {
				output.println(tempBookID + " " + tempClientID + " " + tempDate + " " + tempTime + " " + tempBR + " "
						+ tempMarkChanged);

			} catch (Exception e) {

			}

		}
	}

	
	public void listClientsWithBorrowedBooks() {
		
		Validation validation = new Validation();

		ArrayList<String> records = this.historyDotTxtElements("history.txt");
		
		ArrayList<Integer> repetionControl = new ArrayList<Integer>();
		
		

		System.out.println("Clients with borrwed books at the moment: \n");
		System.out.printf("%-3s\t%-20s\n", "ID", "ClientName");
		System.out.println("--------------------------");
		
		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempClientID = niz[1];
			String tempMark = niz[5];

			boolean flag = validation.validatePrint(repetionControl, Integer.valueOf(tempClientID));
			
			if (tempMark.equals("B") && flag) {

				System.out.printf("%-3s\t%-20s\n", tempClientID, getClientName(Integer.valueOf(tempClientID)));
				
				repetionControl.add(Integer.valueOf(tempClientID));
			}

		}

	}


	public String getClientName(int client) {

		ArrayList<String> records = this.historyDotTxtElements("clients.txt");

		String tempToReturn = "";
		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];
			String tempFirstName = niz[1];
			String tempLastName = niz[2];

			if (Integer.valueOf(tempID) == client) {
				tempToReturn = tempFirstName + " " + tempLastName;
			}

		}
		return tempToReturn;
	}

	
	public void listUserUsage(int client) {

		ArrayList<String> records = this.historyDotTxtElements("history.txt");

		System.out.println("\nListing of books usage for client " + this.getClientName(client) + ": \n");

		System.out.printf("%-10s\t%-10s\t%-25s\n", "Borrowed", "Returned", "Book");
		System.out.println("--------------------------------------------------------------");

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempBookID = niz[0];
			String tempClientID = niz[1];
			String tempDate = niz[2];
			String tempBR = niz[4];

			if (Integer.valueOf(tempClientID) == client) {

				System.out.printf("%s\t%s\n",
						(tempBR.equals("borrow") ? (tempDate + "                ") : ("                " + tempDate)),
						getBookName(Integer.valueOf(tempBookID)));

			}

		}

	}

}
