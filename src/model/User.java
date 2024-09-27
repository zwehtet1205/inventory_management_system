package model;

import java.util.*;

public class User {
	private int user_id,status;
	private String username,password,role;
	private Date created_at;
	
	public User(int user_id, String username, String password, String role, int status,Date created_at) 
	{
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
		this.created_at = created_at;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
