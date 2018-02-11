

public class Library {

	private String name;

	Library() {

	}

	Library(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Library [name=" + name + "]";
	}
}
