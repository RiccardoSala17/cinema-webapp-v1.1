package model;

public class Role {
	
	public enum Level {
		
		USER,
		STAFF,
		ADMIN
	}
	
	long id;
	Level level;
	
	// constructors
	
	public Role(long id, Level level) {
		this.id = id;
		this.level = level;
	}
	
	public Role(Level level) {
		this(0, level);
	}
	
	//getters setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	
	
	
	
	
	
	

}
