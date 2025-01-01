package model.entities;

import java.util.Date;

import model.dao.ContactDAO;

public class Supplier extends People{
	
	private String email,phone,address;
	
	public Supplier(int id, String name, int contact_id, int status, int created_by, Date created_at) {
		super(id, name,"Supplier", contact_id, status, created_by, created_at);
		
		email = ContactDAO.getContact(getContact_id()).getEmail();
		phone = ContactDAO.getContact(getContact_id()).getPhone();
		address = ContactDAO.getContact(getContact_id()).getAddress();
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
