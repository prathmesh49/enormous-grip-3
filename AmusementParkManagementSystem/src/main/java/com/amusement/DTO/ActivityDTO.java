package com.amusement.DTO;

import java.time.LocalDateTime;

import com.amusement.model.Activity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer activityId;
	
	@Size(min = 4, max = 20, message = "{invalid.activityName}")
	@NotNull(message = "{invalid.activityName}")
	@NotBlank(message = "{invalid.activityName}")
	@NotEmpty(message = "{invalid.activityName}")
	private String activityName;
	
	@DecimalMin(value = "10",  message = "{invalid.activityPrice}")
	@DecimalMax(value = "2000",  message = "{invalid.activityPrice}")
	private Double price;
	
	@Min(value = 1, message = "{invalid.activityPersonCapacity}")
	@Max(value = 150, message = "{invalid.activityPersonCapacity}")
	private Integer personCapacity;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdOn;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime lastUpdatedOn;
	
	
	public static Activity convertToActivity(ActivityDTO activityDTO) {
		
		Activity activity = new Activity();
		
		activity.setActivityName(activityDTO.getActivityName());
		activity.setPersonCapacity(activityDTO.getPersonCapacity());
		activity.setPrice(activityDTO.getPrice());
		
		return activity;
	}
	
	public static ActivityDTO convertToActivityDTO(Activity activity) {
		
		ActivityDTO activityDTO = new ActivityDTO();
		
		activityDTO.setActivityId(activity.getActivityId());
		activityDTO.setActivityName(activity.getActivityName());
		activityDTO.setPrice(activity.getPrice());
		activityDTO.setPersonCapacity(activity.getPersonCapacity());
		activityDTO.setCreatedOn(activity.getCreatedOn());
		activityDTO.setLastUpdatedOn(activity.getLastUpdatedOn());
		
		return activityDTO;
	}
}
