package Salesforelikeapp.Salesforelikeapp.service;

import Salesforelikeapp.Salesforelikeapp.model.Activity;
import Salesforelikeapp.Salesforelikeapp.model.Lead;
import Salesforelikeapp.Salesforelikeapp.repository.ActivityRepository;
import Salesforelikeapp.Salesforelikeapp.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private LeadRepository leadRepository;

    // Create a new activity for a lead
    public Activity createActivity(int leadId, Activity activity) {
        Optional<Lead> leadOptional = leadRepository.findById(leadId);
        if (leadOptional.isPresent()) {
            activity.setLead(leadOptional.get());
            return activityRepository.save(activity);
        } else {
            throw new IllegalArgumentException("Lead not found.");
        }
    }

    // Get all activities for a specific lead
    public List<Activity> getActivitiesByLeadId(int leadId) {
        return activityRepository.findByLeadId(leadId);
    }

    // Update an existing activity
    public Activity updateActivity(int activityId, Activity updatedActivity) {
        Optional<Activity> existingActivity = activityRepository.findById(activityId);
        if (existingActivity.isPresent()) {
            Activity activity = existingActivity.get();
            activity.setType(updatedActivity.getType());
            activity.setDescription(updatedActivity.getDescription());
            activity.setDate(updatedActivity.getDate());
            return activityRepository.save(activity);
        } else {
            throw new IllegalArgumentException("Activity not found.");
        }
    }

    // Delete an activity
    public void deleteActivity(int activityId) {
        if (activityRepository.existsById(activityId)) {
            activityRepository.deleteById(activityId);
        } else {
            throw new IllegalArgumentException("Activity not found.");
        }
    }
}
