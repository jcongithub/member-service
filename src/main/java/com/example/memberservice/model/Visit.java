package com.example.memberservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Visit {
	@Id
	private Long id;
	private Long patientId;
	private Long doctorId;
	private Date visitTime;
	private Integer bloodPressure;
	private Integer heartRate;
	private Integer height;
	private Integer weight;
	private String diagnostic;
	private String treatment;
}
