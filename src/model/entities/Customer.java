package model.entities;

import java.util.Date;

import model.dao.ContactDAO;

public class Customer extends People{


	public Customer(int id, String name, int contact_id, int status, int created_by, Date created_at) {
		super(id, name,"Customer", contact_id, status, created_by, created_at);
		
	}
	
	public String getEmail() {
		return ContactDAO.getContact(getContact_id()).getEmail();
	}


	public String getPhone() {
		return ContactDAO.getContact(getContact_id()).getPhone();
	}

	public String getAddress() {
		return ContactDAO.getContact(getContact_id()).getAddress();
	}


}
