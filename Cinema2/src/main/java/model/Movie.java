package model;

public class Movie {
	
	private long id;
	private String title;
	private Genre genre;
	private int year;
	
	//constructors
	
	public Movie(long id, String title, int year, Genre genre) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.genre = genre;
	}
	
	public Movie(String title, int year, Genre genre) {
		this(0, title, year, genre);
	}
	
	
	//getters & setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
