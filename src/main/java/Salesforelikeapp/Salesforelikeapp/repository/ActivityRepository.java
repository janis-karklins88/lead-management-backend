package Salesforelikeapp.Salesforelikeapp.repository;

import Salesforelikeapp.Salesforelikeapp.model.Activity;
import Salesforelikeapp.Salesforelikeapp.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    // Find all activities associated with a specific lead
    List<Activity> findByLeadId(int leadID);
    
    void deleteByLead_Id(int leadId);
}