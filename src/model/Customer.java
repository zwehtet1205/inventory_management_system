package model;

import java.util.Date;

public class Customer extends People{


	public Customer(int id, String name, Contact contact, int status, String created_by, Date created_at) {
		super(id, name,"Customer", contact, status, created_by, created_at);
		
	}


}
