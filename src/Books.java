

public class Books {
	
	private int bookID;
	private String bookName;
	private String authorFirstName;
	private String authorLastName;
	private boolean bookIn;
	
	Books() {
		
	}
	
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	
	public int getBookID() {
		return this.bookID;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookName() {
		return this.bookName;
	}
	
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	
	public String getAuthorFirstName() {
		return this.authorFirstName;
	}
	
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	
	public String getAuthorLastName() {
		return this.authorLastName;
	}
	
	public void setBookIn(boolean bookIn) {
		this.bookIn = bookIn;
	}
	public boolean getBookIn() {
		return bookIn;
	}

	@Override
	public String toString() {
		return "Books [bookID=" + bookID + ", bookName=" + bookName + ", authorFirstName=" + authorFirstName
				+ ", authorLastName=" + authorLastName + ", bookIn=" + bookIn + "]";
	}

}
