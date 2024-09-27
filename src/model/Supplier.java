package model;

import java.util.Date;

public class Supplier extends People{
	
	private String email,phone,address;

	public Supplier(int id, String name, Contact contact, int status, String created_by, Date created_at) {
		super(id, name,"Supplier", contact, status, created_by, created_at);
		this.email = contact.getEmail();
		this.phone = contact.getPhone();
		this.address = contact.getAddress();
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
	
	

}
