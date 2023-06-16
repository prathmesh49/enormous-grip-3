package com.amusement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.amusement.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer>, PagingAndSortingRepository<Activity, Integer>{
	
	public Optional<Activity> findByActivityName(String activityName);
	
	@Query("SELECT a FROM Activity a where a.isDeleted = false")
	public Page<Activity> findAllAvailableNonDeletedActivity(Pageable pageable);
	
	/**
	 * using this method just to get the count
	 * @return
	 */
	@Query("SELECT a FROM Activity a where a.isDeleted = false")
	public List<Activity> getAllAvailableNonDeletedActivity();
}
