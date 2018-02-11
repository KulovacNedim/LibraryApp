

public class Clients {
	
	private int clientID;
	private String firstName;
	private String lastName;
	private int numberOfBorrowedBooks;
	
	Clients() {
		
	}
	
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public int getClientID() {
		return this.clientID;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setNumberOfBorrowedBooks(int number) {
		this.numberOfBorrowedBooks = number;
	}
	
	public int getNumberOfBorrowedBooks() {
		return this.numberOfBorrowedBooks;
	}

	@Override
	public String toString() {
		return "Clients [clientID=" + clientID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", numberOfBorrowedBooks=" + numberOfBorrowedBooks + "]";
	}

}
