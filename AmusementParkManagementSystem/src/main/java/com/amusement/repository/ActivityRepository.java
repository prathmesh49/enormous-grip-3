package com.amusement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amusement.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer>{

}
