
package Salesforelikeapp.Salesforelikeapp.controller;

import Salesforelikeapp.Salesforelikeapp.model.Lead;
import Salesforelikeapp.Salesforelikeapp.model.User;
import Salesforelikeapp.Salesforelikeapp.service.LeadService;
import Salesforelikeapp.Salesforelikeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;




import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/leads")

public class LeadController {

    @Autowired
    private LeadService leadService;
    @Autowired
    private UserService userService;

    // Endpoint to create a new lead
    @PostMapping
    public ResponseEntity<String> createLead(@RequestBody Lead lead, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            // Retrieve the current authenticated user
            User user = userService.getUserByUsername(userDetails.getUsername());
            
            boolean result = leadService.createLead(lead, user);
            if (result) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Lead created successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create lead.");
            }
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    // Endpoint to update an existing lead
    @PutMapping
    public ResponseEntity<String> updateLead(@RequestBody Lead lead, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = userService.getUserByUsername(userDetails.getUsername());
            boolean result = leadService.updateLead(lead, user);
            if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Lead updated.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update lead.");
        }
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to delete a lead by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeadById(@PathVariable int id) {
        try {
            boolean result = leadService.deleteLead(id);
            if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Lead deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete lead.");
        }
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    //filter and sort
    @GetMapping("/leads")
    public ResponseEntity<List<Lead>> getLeads(
    @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
    @RequestParam(value = "order", defaultValue = "desc") String order,
    @RequestParam Map<String, String> filters,
    @AuthenticationPrincipal UserDetails userDetails
) {
    try {
        User user = userService.getUserByUsername(userDetails.getUsername());
        List<Lead> leads = leadService.getLeads(user, sortBy, order, filters);
        return ResponseEntity.ok(leads);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}

    
}
