package model.entities;

public class Payment {
	
	private int id,status;
	private String type;
	
	public Payment(int id, String type, int status) {
		super();
		this.id = id;
		this.status = status;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
