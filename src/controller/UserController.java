package controller;

import java.util.Date;
import java.util.List;

import model.User;
import libraries.PasswordHasher;

public class UserController {
	public static List<User> getAllUsers() {
	    List<User> users = User.getAll(User.class);
	    users.removeIf(user -> user.getId() == 1); // Remove default user
	    return users;
	}


    // add a new user
    public static boolean addUser(String username, String password, String role) {
        // Hash the password before storing
        String passwordHash = PasswordHasher.hashPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword_hash(passwordHash);
        user.setRole(role);
        user.setCreated_at(new Date());
        
        return user.add(user);
    }
    
    
    
    public static boolean deleteUser(int id) {
    	User user = User.findOrFail(User.class, id);
    	
    	return user.delete(user);
    }

    // User login (validate username and password)
    public static User login(String username, String password) {
    	
    	User user = User.findOrFail(User.class, "username", username);
        
        // Validate password
        if (PasswordHasher.verifyPassword(password, user.getPassword_hash())) {
            return user; // Login successful
        }
        
        return null; // Login failed
    }


    public static User isExist(int userId) {
        return User.findOrFail(User.class, userId);
    }
    
    public static List<String> getEnums(){
    	return User.getEnumValues("users", "role");
    }
    
    public static boolean resetPassword(int userId, String oldPassword, String newPassword) {
        User user = User.findOrFail(User.class, userId);

        // Validate old password
        if (!PasswordHasher.verifyPassword(oldPassword, user.getPassword_hash())) {
            return false; // Old password is incorrect
        }

        // Hash the new password
        String newPasswordHash = PasswordHasher.hashPassword(newPassword);

        // Update the user's password
        user.setPassword_hash(newPasswordHash);
        
        return user.update(user);
    }
}
