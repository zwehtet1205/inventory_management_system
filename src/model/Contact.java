package model;

import java.util.Date;

import libraries.SystemModel;

public class Contact extends SystemModel<Contact> {
	
	private int id;
	private String email;
	private String phone;
	private String address;
	private int created_by;
	private Date created_at;
	private Date updated_at;
	
	public Contact() {
		super(Contact.class,"contacts");
	}
	
	public Contact(int id, String email, String phone, String address, int created_by, Date created_at,
			Date updated_at) {
		this();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.created_by = created_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	

	
}
