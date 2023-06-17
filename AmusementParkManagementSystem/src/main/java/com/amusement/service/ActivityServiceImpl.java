package com.amusement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amusement.DTO.ActivityDTO;
import com.amusement.exception.ActivityException;
import com.amusement.model.Activity;
import com.amusement.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepo;

	@Override
	public ActivityDTO createActivity(ActivityDTO activityDTO) throws ActivityException {
		// TODO Auto-generated method stub
		Optional<Activity> opt = activityRepo.findByActivityName(activityDTO.getActivityName());
		
		if(opt.isPresent()) throw new ActivityException("Activity with Name: " + activityDTO.getActivityName()+" already exists");
		if(opt.isPresent() && opt.get().getIsDeleted() == true) throw new ActivityException("Activity is deleted");
		
		Activity activity = ActivityDTO.convertToActivity(activityDTO);
		activity.setIsDeleted(false);
		
		return ActivityDTO.convertToActivityDTO(activityRepo.save(activity));
	}

	@Override
	public ActivityDTO getActivityById(Integer activityId) throws ActivityException {
		// TODO Auto-generated method stub
		Optional<Activity> opt = activityRepo.findById(activityId);
		
		if(opt.isEmpty()) throw new ActivityException("Activity with activityId: " + activityId + " not found");
		if(opt.isPresent() && opt.get().getIsDeleted() == true) throw new ActivityException("Activity is deleted");
		
		return ActivityDTO.convertToActivityDTO(activityRepo.save(opt.get()));
	}

	
	public ActivityDTO updateActivity(Integer activityId, ActivityDTO updatedActivityDTO) {
		// TODO Auto-generated method stub
		Optional<Activity> opt = activityRepo.findById(activityId);
		
		if(opt.isEmpty()) throw new ActivityException("Activity with activityId: " + activityId + " not found");
		if(opt.isPresent() && opt.get().getIsDeleted() == true) throw new ActivityException("Activity is deleted");
		
		Optional<Activity> activityByName = activityRepo.findByActivityName(updatedActivityDTO.getActivityName());
		
		if(activityByName.isPresent()) throw new ActivityException("Activity with name: "+ updatedActivityDTO.getActivityName()+ " already present");
		
		Activity activity = opt.get();
		activity.setActivityName(updatedActivityDTO.getActivityName());
		activity.setPersonCapacity(updatedActivityDTO.getPersonCapacity());
		activity.setPrice(updatedActivityDTO.getPrice());
		
		return ActivityDTO.convertToActivityDTO(activityRepo.save(activity));
	}

	@Override
	public Boolean deleteActivity(Integer activityId) {
		// TODO Auto-generated method stub
		Optional<Activity> opt = activityRepo.findById(activityId);
		
		if(opt.isEmpty()) throw new ActivityException("Activity with activityId: " + activityId + " not found");
		if(opt.isPresent() && opt.get().getIsDeleted() == true) throw new ActivityException("Activity has already been deleted");
		
		opt.get().setIsDeleted(true);
		Activity activity = activityRepo.save(opt.get());
		
		if(activity != null) return true;
		else return false;
	}

	@Override
	public List<ActivityDTO> getAllAvailableActivities(Integer pageNumber, Integer itemsPerPage) throws ActivityException {
		// TODO Auto-generated method stub
		
		List<Activity> activities = activityRepo.findAllAvailableNonDeletedActivity(PageRequest.of(pageNumber-1, itemsPerPage)).getContent();
		
		if(activities.isEmpty()) throw new ActivityException("No activity available");
		
		/**
		 * converting Activity objects to ActivityDTO objects
		 */
		List<ActivityDTO> activityDTOs = new ArrayList<>();
		activities.forEach(i -> activityDTOs.add(ActivityDTO.convertToActivityDTO(i)));
		
		return activityDTOs;
	}

	@Override
	public Integer getAvalilableActivityCount() throws ActivityException {
		// TODO Auto-generated method stub
		
		return activityRepo.getAllAvailableNonDeletedActivity().size();
	}

	

}
