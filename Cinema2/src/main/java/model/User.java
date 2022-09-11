package model;

import model.Role.Level;

public class User {
	
	private long id;
	private String username;
	private String password;
	private Role role;
	
	//constructors
	public User(long id, String username, String password, Role role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(String username, String password, Role role) {
		this(0, username, password, role);
	}
	
	public User(String username, String password) {
		this(0, username, password, new Role(Level.USER));
	}
	
	//getters and setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

	
	
	
	
	
	
}
