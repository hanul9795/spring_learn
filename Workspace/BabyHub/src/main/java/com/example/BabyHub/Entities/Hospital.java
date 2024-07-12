package com.example.BabyHub.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "cd", nullable=false)
	private int cd;
	
	@Column(name = "Organization", nullable=false)
	private String orgnm;
	
	@Column(name = "PhoneNum", nullable=false)
	private String orgTlno;
	
	@Column(name = "Address", nullable=false)
	private String orgAddr;
	
	@Column(name = "Date", nullable=false)
	private int expnYmd;
	
	 
	
	public Hospital(int Id, int cd, String orgnm, String orgTlno, String orgAddr, int expnYmd) {
		this.Id = Id;
		this.cd = cd;
		this.orgnm = orgnm;
		this.orgTlno = orgTlno;
		this.orgAddr = orgAddr;
		this.expnYmd = expnYmd;
	}
}
