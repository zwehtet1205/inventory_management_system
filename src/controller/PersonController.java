package controller;

import java.util.List;

import model.Person;
import model.User;

public class PersonController {

    // Get all persons
    public static List<Person> getAllPeople() {
        return Person.getAll(Person.class); 
    }

    // Get specific person by ID
    public static Person get(int id) {
        return Person.findOrFail(Person.class, id); 
    }
    
    // Get specific person by ID
    public static Person get(String column,String value) {
        return Person.findOrFail(Person.class, column, value); 
    }

    // Add a new person
    public static boolean addPerson(String name, String person_type, int contact_id, int status_id, int created_by) {
        Person person = new Person();
        person.setName(name);
        person.setPerson_type(person_type);
        person.setContact_id(contact_id);
        person.setStatus_id(status_id);
        person.setCreated_by(created_by);
        person.setCreated_at(new java.util.Date());
        person.setUpdated_at(new java.util.Date());
        
        return person.add(person);
    }

    // Update person details
    public static boolean updatePerson(int id, String name, String person_type, int contact_id, int status_id) {
        Person person = Person.findOrFail(Person.class, id);
        person.setName(name);
        person.setPerson_type(person_type);
        person.setContact_id(contact_id);
        person.setStatus_id(status_id);
        person.setUpdated_at(new java.util.Date());
        
        return person.update(person);
    }

    // Delete person by ID
    public static boolean deletePerson(int id) {
        Person person = Person.findOrFail(Person.class, id);
        
        return person.delete(person);
    }
    
    // check exist 
 	public static boolean exist(int id) {
 		return Person.isExist("id", id, "people");
 	}
 	
 	public static List<String> getEnums(){
    	return Person.getEnumValues("people", "person_type");
    }
}
