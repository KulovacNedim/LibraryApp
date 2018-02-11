

import java.util.ArrayList;

public class Validation {
	
	private MethodsStock methodsStock = new MethodsStock();
	
	Validation() {
		
	}

	public boolean validClient(int client) {

		ArrayList<String> records = methodsStock.historyDotTxtElements("clients.txt");

		boolean flag = false;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];

			if (Integer.valueOf(tempID) == client) {

				flag = true;
				break;
			}

		}

		return flag;
	}
	
	public boolean validClientBookRenting(int client) {

		ArrayList<String> records = methodsStock.historyDotTxtElements("history.txt");

		boolean flag = false;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempClientID = niz[1];

			if (Integer.valueOf(tempClientID) == client) {

				flag = true;
				break;
			}

		}

		return flag;
	}

	
	public boolean validBook(int book) {	

		ArrayList<String> records = methodsStock.historyDotTxtElements("books.txt");

		boolean flag = false;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];

			if (Integer.valueOf(tempID) == book) {

				flag = true;
			}

		}
		return flag;
	}

	
	public int borrowedBookRestiction(int client) {

		ArrayList<String> records = methodsStock.historyDotTxtElements("clients.txt");

		int borrowed = 0;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];
			String tempBorrowed = niz[3];

			if (Integer.valueOf(tempID) == client) {

				borrowed = Integer.valueOf(tempBorrowed);
			}

		}

		return borrowed;
	}

	
	public boolean bookAvailable(int book) {

		ArrayList<String> records = methodsStock.historyDotTxtElements("books.txt");

		boolean flag = false;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempID = niz[0];
			String tempAvailable = niz[1];

			if (Integer.valueOf(tempID) == book) {

				if (tempAvailable.equals("true")) {
					flag = true;
				}

			}

		}

		return flag;
	}

	
	public boolean validateBorrowedBookByClient(int client, int book) {

		ArrayList<String> records = methodsStock.historyDotTxtElements("history.txt");

		boolean flag = false;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempBookID = niz[0];
			String tempClientID = niz[1];
			String tempBorr = niz[5];

			if (Integer.valueOf(tempClientID) == client) {

				if (Integer.valueOf(tempBookID) == book && tempBorr.equals("B")) {
					flag = true;
					break;
				}
			}

		}
		return flag;
	}

	
	public boolean isThereAnyBorrowedBooks() {

		ArrayList<String> records = methodsStock.historyDotTxtElements("history.txt");

		boolean flag = false;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempMark = niz[5];

			if (tempMark.equals("B")) {

				flag = true;
				break;
			}

		}

		return flag;

	}

	
	public boolean validateInputForReturningBook(int client) {

		ArrayList<String> records = methodsStock.historyDotTxtElements("history.txt");

		boolean flag = false;

		for (int i = 0; i < records.size(); i++) {

			String lineTemp = records.get(i);

			String[] niz = lineTemp.split(" ");

			String tempClientID = niz[1];
			String tempMark = niz[5];

			if (Integer.valueOf(tempClientID) == client && tempMark.equals("B")) {
				flag = true;
			}

		}
		return flag;
	}
	
	public boolean validatePrint(ArrayList<Integer> repetionControl, int clientID) {
		
		boolean flag = true;
		
		for (int i = 0; i < repetionControl.size(); i++) {
		
			if (repetionControl.get(i) == clientID) {
				flag = false;
				break;
			}
		}
		
		return flag;
	}
}
