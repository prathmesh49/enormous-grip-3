package com.amusement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amusement.DTO.ActivityDTO;
import com.amusement.service.ActivityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping("/")
	public ResponseEntity<ActivityDTO> addActivityHandler(@Valid @RequestBody ActivityDTO activity){
		
		return new ResponseEntity<>(activityService.createActivity(activity), HttpStatus.CREATED);
	}
	
	@GetMapping("/{activityId}")
	public ResponseEntity<ActivityDTO> getActivityByIdHandler(@PathVariable Integer activityId){
		
		return new ResponseEntity<>(activityService.getActivityById(activityId), HttpStatus.OK);
	}
	
	@PutMapping("/{activityId}")
	public ResponseEntity<ActivityDTO> updateActivityHandler(@PathVariable Integer activityId, 
			@Valid @RequestBody ActivityDTO activity){
		
		return new ResponseEntity<>(activityService.updateActivity(activityId, activity), HttpStatus.OK);
	}
	
	@DeleteMapping("/{activityId}")
	public ResponseEntity<String> deleteActivityHandler(@PathVariable Integer activityId){
		
		Boolean deleteStatus = activityService.deleteActivity(activityId);
		
		HttpStatus status;
		String message;
		
		if(deleteStatus) {
			status = HttpStatus.OK;
			message = "Activity deleted successfully";
		}else {
			status = HttpStatus.BAD_REQUEST;
			message = "Not able to delete activity";
		}
		
		return new ResponseEntity<>(message, status);
	}
	
	@GetMapping
	public ResponseEntity<List<ActivityDTO>> getAllNonDeletedActivitiesHandler(
			@RequestParam("pageNumber") Integer pageNumber, 
			@RequestParam("recordsPerPage") Integer recordsPerPage){
		
		return new ResponseEntity<>(activityService.getAllAvailableActivities(pageNumber, recordsPerPage), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Integer> getCountOfAllAvailableNonDeletedActivitiesHandler(){
		
		return new ResponseEntity<>(activityService.getAvalilableActivityCount(), HttpStatus.OK);
	}

}
