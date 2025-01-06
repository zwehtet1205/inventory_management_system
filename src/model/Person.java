package model;

import java.util.Date;

import libraries.SystemModel;

public class Person extends SystemModel<Person> {
	
	private int id;
	private String name;
	private String person_type;
	private int contact_id;
	private int status_id;
	private int created_by;
	private Date created_at;
	private Date updated_at;
	
	public Person() {
		super(Person.class,"people");
	}
	public Person(int id, String name, String person_type, int contact_id, int status_id, int created_by,
			Date created_at, Date updated_at) {
		this();
		this.id = id;
		this.name = name;
		this.person_type = person_type;
		this.contact_id = contact_id;
		this.status_id = status_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPerson_type() {
		return person_type;
	}
	public void setPerson_type(String person_type) {
		this.person_type = person_type;
	}
	public int getContact_id() {
		return contact_id;
	}
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
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
	
	public Contact getContact()
	{
		return SystemModel.findOrFail(Contact.class, contact_id);
	}
	
	public String getEmail() {
		return getContact().getEmail();
	}
	
	public String getPhone() {
		return getContact().getPhone();
	}
	
	public String getAddress() {
		return getContact().getAddress();
	}
	
	public Status getStatus() {
		return SystemModel.findOrFail(Status.class,status_id);
	}

	
	
	

}
