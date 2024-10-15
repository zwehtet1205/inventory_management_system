package model;

import java.util.Date;

public class People {
	
	private int id,status,contact_id,created_by;
	private String name,type;
	private Date created_at;
	
	public People(int id,String name,String type,int contact_id,int status,int created_by,Date created_at)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.contact_id = contact_id;
		this.status = status;
		this.created_by = created_by;
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

	
	
	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	
	
	

}
