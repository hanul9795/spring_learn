package com.example.BabyHub.DBinit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
	private int id;
	private int cd;
	private String orgnm;
	private String orgAddr;
	private String orgTlnm;
}
