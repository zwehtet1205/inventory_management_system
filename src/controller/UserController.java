package controller;

import java.util.Date;
import model.User;
import libraries.PasswordHasher;

public class UserController {

    // add a new user
    public static boolean add(String username, String password, String role) {
        // Hash the password before storing
        String passwordHash = PasswordHasher.hashPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword_hash(passwordHash);
        user.setRole(role);
        user.setCreated_at(new Date());
        
        return user.save();
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
}
