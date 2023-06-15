package com.amusement.service;

import com.amusement.exception.ActivityException;
import com.amusement.model.Activity;

public interface ActivityService {
	
	public Activity createActivity(Activity activity) throws ActivityException;

	public Activity getActivity(Integer activityId) throws ActivityException;

	public Activity updateActivity(Integer activityId, Activity updatedActivity);

	public void deleteActivity(Integer activityId);
	

}
