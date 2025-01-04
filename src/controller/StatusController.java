package controller;

import model.Status;

public class StatusController {
	
	public static Status get(int id) {
		return Status.findOrFail(Status.class, id); 
	}

}
