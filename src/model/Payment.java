package model;

import libraries.SystemModel;

public class Payment extends SystemModel<Payment> {
	
	private int id;
	private String name;
	private int status_id;
	
	public Payment() {
		super(Payment.class,"payment_methods");
	}
	
	public Payment(int id,String name,int status_id)
	{
		this();
		this.id = id;
		this.name = name;
		this.status_id = status_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	
	
	

}
