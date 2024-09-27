package model;

import java.util.Date;

public class People {
	
	private int id,status;
	private Contact contact;
	private String name,type,created_by;
	private Date created_at;
	
	public People(int id,String name,String type,Contact contact,int status,String created_by,Date created_at)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.contact = contact;
		this.status = status;
		this.setCreated_by(created_by);
		this.created_at = created_at;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	
	
	

}
