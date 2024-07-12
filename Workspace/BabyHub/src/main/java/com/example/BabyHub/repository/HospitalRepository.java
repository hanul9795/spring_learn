package com.example.BabyHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BabyHub.Entities.Hospital;
import com.example.BabyHub.DBinit.HospitalDTO;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
	List<Hospital> findByCd(String cd); 
}