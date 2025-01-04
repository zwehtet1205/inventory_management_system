package model;

import libraries.SystemModel;

public class Status extends SystemModel<Status> {
	
	private int id;
	private String name;

	public Status() {
		super(Status.class, "statuses");
		
	}

	public Status(int id, String name) {
		this();
		this.id = id;
		this.name = name;
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
	
	
	
	
	
	

}
