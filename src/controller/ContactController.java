package controller;

import java.util.List;

import libraries.SystemModel;
import model.Contact;

public class ContactController {

    // Get all contacts
    public static List<Contact> getAllContacts() {
        return Contact.getAll(Contact.class); 
    }
    
    // Get Id 
    public static int getID(String email) {

		Contact contact = SystemModel.findOrFail(Contact.class,"email",email);
    	return contact.getId();
    }

    // Get specific contact by ID
    public static Contact get(int id) {
        return Contact.findOrFail(Contact.class, id); 
    }

    // Add a new contact
    public static boolean addContact(String email, String phone, String address, int created_by) {
        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);
        contact.setCreated_by(created_by);
        contact.setCreated_at(new java.util.Date());
        contact.setUpdated_at(new java.util.Date());
        
        return contact.add(contact);
    }

    // Update contact details
    public static boolean updateContact(int id, String email, String phone, String address) {
        Contact contact = Contact.findOrFail(Contact.class, id);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);
        contact.setUpdated_at(new java.util.Date());
        
        return contact.update(contact);
    }

    // Delete contact by ID
    public static boolean deleteContact(int id) {
        Contact contact = Contact.findOrFail(Contact.class, id);
        
        return contact.delete(contact);
    }
    
    // Check if contact exists
    public static boolean exist(int id) {
        return Contact.isExist("id", id, "contacts");
    }
}
