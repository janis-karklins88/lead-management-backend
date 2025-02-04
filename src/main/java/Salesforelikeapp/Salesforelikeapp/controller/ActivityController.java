package Salesforelikeapp.Salesforelikeapp.controller;

import Salesforelikeapp.Salesforelikeapp.model.Activity;
import Salesforelikeapp.Salesforelikeapp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // Get all activities for a specific lead
    @GetMapping("/lead/{leadId}")
    public ResponseEntity<List<Activity>> getActivitiesByLeadId(@PathVariable int leadId) {
        List<Activity> activities = activityService.getActivitiesByLeadId(leadId);
        return ResponseEntity.ok(activities);
    }

    // Add a new activity for a specific lead
    @PostMapping("/lead/{leadId}")
    public ResponseEntity<Activity> addActivity(@PathVariable int leadId, @RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(leadId, activity);
        return ResponseEntity.ok(createdActivity);
    }

    // Delete an activity by its ID
    @DeleteMapping("/{activityId}")
    public ResponseEntity<String> deleteActivity(@PathVariable int activityId) {
        activityService.deleteActivity(activityId);
        return ResponseEntity.ok("Activity deleted successfully.");
    }
}
