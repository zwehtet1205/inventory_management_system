package model;

import java.util.*;

import libraries.SystemModel;

public class User extends SystemModel<User> {
	private int id;
	private String username;
	private String password_hash;
	private String role;
	private Date created_at;
	public User() {
		super(User.class,"users");
	}
	public User(int id, String username, String password_hash, String role, Date created_at) {
		this();
		this.id = id;
		this.username = username;
		this.password_hash = password_hash;
		this.role = role;
		this.created_at = created_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
	
	
	
	
	
}
