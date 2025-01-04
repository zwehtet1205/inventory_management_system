package controller;

import java.util.List;
import model.Contact;

public class ContactController {

    // Get all contacts
    public static List<Contact> getAllContacts() {
        return Contact.getAll(Contact.class); 
    }

    // Get specific contact by ID
    public static Contact get(int id) {
        return Contact.findOrFail(Contact.class, id); 
    }

    // Add a new contact
    public static boolean add(String email, String phone, String address, int created_by) {
        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);
        contact.setCreated_by(created_by);
        
        return contact.save();
    }

    // Update contact details
    public static boolean update(int id, String email, String phone, String address) {
        Contact contact = Contact.findOrFail(Contact.class, id);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);
        
        return contact.save();
    }

    // Delete contact by ID
    public static boolean delete(int id) {
        Contact contact = Contact.findOrFail(Contact.class, id);
        
        return contact.delete(contact);
    }
    
    // Check if contact exists
    public static boolean exist(int id) {
        return Contact.isExist("id", id, "contacts");
    }
}
