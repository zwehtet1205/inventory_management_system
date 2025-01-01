package model;

import java.util.Date;

import libraries.SystemModel;

public class Warehouse extends SystemModel<Warehouse> {

	private int id;
	private String name;
	private int status_id;
	private String location;
	private int created_by;
	private Date created_at;
	private Date updated_at;
	public Warehouse() {
		super(Warehouse.class,"warehouses");
	}
	public Warehouse(int id, String name, int status_id, String location, int created_by, Date created_at,
			Date updated_at) {
		this();
		this.id = id;
		this.name = name;
		this.status_id = status_id;
		this.location = location;
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
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
