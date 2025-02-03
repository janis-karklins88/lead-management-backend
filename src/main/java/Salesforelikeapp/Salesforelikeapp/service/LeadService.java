
package Salesforelikeapp.Salesforelikeapp.service;

import Salesforelikeapp.Salesforelikeapp.model.Lead;
import Salesforelikeapp.Salesforelikeapp.model.User;
import Salesforelikeapp.Salesforelikeapp.repository.LeadRepository;
import Salesforelikeapp.Salesforelikeapp.utils.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    // Create a new lead
    public boolean createLead(Lead lead, User user) {
        
        lead.setUser(user);
        
        //empty field validation
        if(!Validations.validateRequiredField(lead.getName(), "Name")){
            throw new IllegalArgumentException("Name is required!");
        }
        if(!Validations.validateRequiredField(lead.getStatus(), "Name")){
            throw new IllegalArgumentException("Status is required!");
        }
        //email number validation
        if(!Validations.validateEmail(lead.getEmail())){
            throw new IllegalArgumentException("Invalid email format!");
        }
        //phone nr validation
        if(!Validations.validatePhone(lead.getPhone())){
            throw new IllegalArgumentException("Phone number must contain only digits!");
        }
        
        //creat lead
        leadRepository.save(lead);
        return true;
    }




    // Update a lead
    public boolean updateLead(Lead lead, User user) {
    // Check if the lead ID exists
    if (!leadRepository.existsById(lead.getId())) {
        throw new IllegalArgumentException("Lead do not exist."); 
    }
    lead.setUser(user);
    //empty field validation
        if(!Validations.validateRequiredField(lead.getName(), "Name")){
            throw new IllegalArgumentException("Name is required!");
        }
        if(!Validations.validateRequiredField(lead.getStatus(), "Name")){
            throw new IllegalArgumentException("Status is required!");
        }
        //email number validation
        if(!Validations.validateEmail(lead.getEmail())){
            throw new IllegalArgumentException("Invalid email format!");
        }
        //phone nr validation
        if(!Validations.validatePhone(lead.getPhone())){
            throw new IllegalArgumentException("Phone number must contain only digits!");
        }

    // Update the lead
    leadRepository.save(lead);
    return true;
}


    // Delete a lead by ID
    public boolean deleteLead(int id) {
    // Check if the lead ID exists
    if (!leadRepository.existsById(id)) {
        throw new IllegalArgumentException("Lead do not exist."); 
    }

    // Delete the lead
    leadRepository.deleteById(id);
    return true;
}
    //Get leads filter&sort
    public List<Lead> getLeads(User user, String sortBy, String order, Map<String, String> filters) {
    // Validate sorting parameters
    if (!List.of("asc", "desc").contains(order.toLowerCase())) {
        throw new IllegalArgumentException("Invalid sort order: " + order);
    }

    // Build a custom query based on filters
    Specification<Lead> specification = (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new ArrayList<>();
        
        //filter by user
        predicates.add(criteriaBuilder.equal(root.get("user"), user));
        
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("status")) {
                predicates.add(criteriaBuilder.equal(root.get("status"), value));
            } else if(key.equalsIgnoreCase("priority")){
                predicates.add(criteriaBuilder.equal(root.get("priority"), value));
            }
              else if (key.equalsIgnoreCase("email")) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + value + "%"));
            } else if (key.equalsIgnoreCase("phone")) {
                predicates.add(criteriaBuilder.like(root.get("phone"), "%" + value + "%"));
            } else if (key.equalsIgnoreCase("name")) { 
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + value.toLowerCase() + "%"));
}
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };

    // Apply sorting
    Sort sort = Sort.by(order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

    // Execute query
    return leadRepository.findAll(specification, sort);
}


    
}
