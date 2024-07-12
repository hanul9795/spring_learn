package com.example.BabyHub.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BabyHub.repository.CityRepository;
import com.example.BabyHub.repository.HospitalRepository;

@Service
public class LocationProvidingAPI {
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@GetMapping("/api/LocationProvide")
	public String provideHospital(@RequestParam String location) {
		hospitalRepository.findByCd(location);
		
		
	}
}
