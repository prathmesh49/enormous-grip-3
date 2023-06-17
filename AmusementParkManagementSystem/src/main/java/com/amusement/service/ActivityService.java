package com.amusement.service;

import java.util.List;

import com.amusement.DTO.ActivityDTO;
import com.amusement.exception.ActivityException;
import com.amusement.model.Activity;

public interface ActivityService {
	
	public ActivityDTO createActivity(ActivityDTO activityDTO) throws ActivityException;

	public ActivityDTO getActivityById(Integer activityId) throws ActivityException;

	public ActivityDTO updateActivity(Integer activityId, ActivityDTO updatedActivityDTO);

	public Boolean deleteActivity(Integer activityId);
	
	public List<ActivityDTO> getAllAvailableActivities(Integer pageNumber, Integer itemsPerPage) throws ActivityException;
	
	public Integer getAvalilableActivityCount() throws ActivityException;
}
