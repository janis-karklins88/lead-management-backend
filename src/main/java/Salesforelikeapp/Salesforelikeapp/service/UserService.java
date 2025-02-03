package Salesforelikeapp.Salesforelikeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Salesforelikeapp.Salesforelikeapp.repository.UserRepository;
import Salesforelikeapp.Salesforelikeapp.model.User; 
import at.favre.lib.crypto.bcrypt.BCrypt;
import Salesforelikeapp.Salesforelikeapp.utils.Validations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager; //Inject AuthenticationManager
    
    //adding user
    public boolean registerUser(User user) {
        //Check for empty fields
        if (!Validations.validateRequiredField(user.getUsername(), "Username") ||
        !Validations.validateRequiredField(user.getPassword(), "Password")){
            throw new IllegalArgumentException("Username or Password can not be empty.");
        }
        //check for password strenght
        if (!Validations.validatePasswordStrength(user.getPassword())){
            throw new IllegalArgumentException("Password must be at least 8 characters and contain at least 1 digit.!");
        }
        // Check if the username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken."); // Username already exists
        }
        
        //Password hashing
        String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(hashedPassword);

        // Save the user
        userRepository.save(user);
        return true;
    }
    
    //user login
    public boolean authenticateUser(User user) {
        
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            return authentication.isAuthenticated(); // Authentication success
        } catch (Exception e) {
            return false; // Authentication failed
        }
    }
    
    //get user by username
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
        
    }

}
