package model;

public class Genre {
	
	private long id;
	private String name;
	
	
	//costruttori
	
	public Genre(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Genre(String name) {
		this(0, name);
	}
	
	//getters setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	//getters & setters
	

}
