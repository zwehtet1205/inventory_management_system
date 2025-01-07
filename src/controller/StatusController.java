package controller;

import java.util.*;

import libraries.SystemModel;
import model.Status;

public class StatusController {
	
	public static List<Status> getForInvoice(){
		List<Integer> values = Arrays.asList(3,4,5);
		SystemModel.whereIn("id", values);
		return Status.get(Status.class);
		
	}
	
	public static Status get(int id) {
		return Status.findOrFail(Status.class, id); 
	}

}
