package Salesforelikeapp.Salesforelikeapp.controller;


import Salesforelikeapp.Salesforelikeapp.service.UserService;
import Salesforelikeapp.Salesforelikeapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Salesforelikeapp.Salesforelikeapp.utils.JwtUtil;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    
    //adding user to db
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        
        //messages
        try {
            boolean result = userService.registerUser(user);
            if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration sucessful.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration unsuccessful.");
        }
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    //user login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
    
    //message
    try {
            boolean isAuthenticated = userService.authenticateUser(user);
            if (isAuthenticated) {
                String token = JwtUtil.generateToken(user.getUsername()); //Generate JWT Token
                System.out.println("Generated Toke: " + token);
                return ResponseEntity.ok(token);
            } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login unsuccessful.");
        }
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
            
}

    
    
    

    
}
