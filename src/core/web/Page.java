package core.web;

public class Page {
	private static final String DIAGONAL = "/";
	private String name;

	public Page() {
	}

	public Page(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return new StringBuilder(DIAGONAL).append(name).toString();
	}

	public void setName(String name) {
		this.name = name;
	}
}