package com.example.BabyHub.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class City {
			

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int Id;
			
			@Column(name = "cdNm")
			private String cdNm;
			
			@Column(name="cd", nullable = false)
			private int cd;
			
			
	public City(int id, String cdNm, int cd) {
		this.Id = id;
		this.cd = cd;
		this.cdNm = cdNm;
	}
}