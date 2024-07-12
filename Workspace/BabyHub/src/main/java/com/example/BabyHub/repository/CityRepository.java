package com.example.BabyHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BabyHub.Entities.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	
}