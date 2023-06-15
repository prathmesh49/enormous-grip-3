package com.amusement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amusement.exception.ActivityException;
import com.amusement.model.Activity;
import com.amusement.service.ActivityService;

@RestController
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping("/activities")
	public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) throws ActivityException {
		Activity createdActivity = activityService.createActivity(activity);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
	}

	@GetMapping("/activities/{id}")
	public ResponseEntity<Activity> getActivity(@PathVariable("id") Integer activityId) throws ActivityException {
		Activity activity = activityService.getActivity(activityId);
		if (activity != null) {
			return ResponseEntity.ok(activity);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/activities/{id}")
	public ResponseEntity<Activity> updateActivity(@PathVariable("id") Integer activityId,
			@RequestBody Activity activity) {
		Activity updatedActivity = activityService.updateActivity(activityId, activity);
		if (updatedActivity != null) {
			return ResponseEntity.ok(updatedActivity);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/activities/{id}")
	public ResponseEntity<Void> deleteActivity(@PathVariable("id") Integer activityId) {
		activityService.deleteActivity(activityId);
		return ResponseEntity.noContent().build();
	}

}
