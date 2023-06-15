package com.amusement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amusement.model.Activity;
import com.amusement.model.Customer;
import com.amusement.repository.ActivityRepository;
import com.amusement.repository.CustomerRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity createActivity(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity getActivity(Integer activityId) {
		return activityRepository.findById(activityId).orElse(null);
	}

	@Override
	public Activity updateActivity(Integer activityId, Activity updatedActivity) {
		Activity activity = getActivity(activityId);
		if (activity != null) {

			activity.setActivityName(updatedActivity.getActivityName());
			activity.setPrice(updatedActivity.getPrice());
			activity.setCapacity(updatedActivity.getCapacity());
			activity.setDuration(updatedActivity.getDuration());

			return activityRepository.save(activity);
		} else {
			return null;
		}

	}

	@Override
	public void deleteActivity(Integer activityId) {
		activityRepository.deleteById(activityId);

	}

}
