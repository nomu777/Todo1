package model;

import java.io.Serializable;

public class TodoUser implements Serializable {
	
	private String name;
	private String pass;
	
	public TodoUser() {}
	public TodoUser(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
}