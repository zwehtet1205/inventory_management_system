package controller;

import java.util.List;

import model.Person;

public class PersonController {

    // Get all persons
    public static List<Person> getAllPersons() {
        return Person.getAll(Person.class); 
    }

    // Get specific person by ID
    public static Person get(int id) {
        return Person.findOrFail(Person.class, id); 
    }

    // Add a new person
    public static boolean add(String name, String person_type, int contact_id, int status_id, int created_by) {
        Person person = new Person();
        person.setName(name);
        person.setPerson_type(person_type);
        person.setContact_id(contact_id);
        person.setStatus_id(status_id);
        person.setCreated_by(created_by);
        
        return person.save();
    }

    // Update person details
    public static boolean update(int id, String name, String person_type, int contact_id, int status_id) {
        Person person = Person.findOrFail(Person.class, id);
        person.setName(name);
        person.setPerson_type(person_type);
        person.setContact_id(contact_id);
        person.setStatus_id(status_id);
        
        return person.save();
    }

    // Delete person by ID
    public static boolean delete(int id) {
        Person person = Person.findOrFail(Person.class, id);
        
        return person.delete(person);
    }
    
    // check exist 
 	public static boolean exist(int id) {
 		return Person.isExist("id", id, "people");
 	}
}
