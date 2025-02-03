
package Salesforelikeapp.Salesforelikeapp;

import Salesforelikeapp.Salesforelikeapp.utils.Validations;
import Salesforelikeapp.Salesforelikeapp.model.User;
import Salesforelikeapp.Salesforelikeapp.service.UserService;
import Salesforelikeapp.Salesforelikeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class TestMain {
    public static void main(String[] args){
        UserService userService = new UserService();
        User user = new User();
        user.setUsername("lol");
        user.setPassword("sd");
        
        boolean isReg = userService.registerUser(user);
        
        if(isReg){
            System.out.println("positive");
        } else {
            System.out.println("Negative");
        }
        
    }
}
