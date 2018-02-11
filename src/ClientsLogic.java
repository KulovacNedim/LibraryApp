

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientsLogic {
	
	Validation validation = new Validation();
	private MethodsStock methodsStock = new MethodsStock();

	public void createCliant(Scanner sc) {

		Clients newClient = new Clients();

		System.out.print("Enter a client first name: ");
		String firstName = sc.next();

		sc.nextLine();

		System.out.print("Enter a client second name: ");
		String secondName = sc.next();

		sc.nextLine();

		int id = nextClientID();

		newClient.setFirstName(firstName);
		newClient.setLastName(secondName);
		newClient.setNumberOfBorrowedBooks(0);
		newClient.setClientID(id);

		saveClient(newClient);

		System.out.println("\nClient " + newClient.getFirstName() + " " + newClient.getLastName() + " added.");
	}
	

	public void saveClient(Clients newClient) {

		String file = "clients.txt";

		// write client info
		try (PrintWriter output = new PrintWriter(new FileWriter(file, true))) {

			output.println(newClient.getClientID() + " " + newClient.getFirstName() + " " + newClient.getLastName()
					+ " " + newClient.getNumberOfBorrowedBooks());

		} catch (Exception e) {

		}
	}
	

	public int nextClientID() {

		ArrayList<String> records = methodsStock.historyDotTxtElements("clients.txt");

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
	

	public void clientUsageOverview(Scanner sc) {

		methodsStock.listClients();
		
		boolean validClient = false;
		boolean validClientBookRenting = false;
		
		while (!validClient || !validClientBookRenting) {

			System.out.print("\nEnter user (ID) for more detailed informations:");
	
			int client = sc.nextInt();
			
			validClient = validation.validClient(client);
			validClientBookRenting = validation.validClientBookRenting(client);
	
			if (!validClient) {
				System.out.println("Wrong input. Try again.");
			} 
			else if (!validClientBookRenting) {
				System.out.println("\nSelected client did not borrowed any book yet. Try again.");
			}
			else {
				methodsStock.listUserUsage(client);
			}

		
		
		}

	}

}
