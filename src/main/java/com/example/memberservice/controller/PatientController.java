package com.example.memberservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.memberservice.model.MedicalSummary;
import com.example.memberservice.service.PatientService;

/**
 * PatientController is an application orchestration service and is used by a patent UI application.  
 * @author jianfeicheng
 *
 */

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
	private PatientService patientService;
	
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping("/{id}")
	public MedicalSummary getPatientMedicalSummary(@PathVariable long id) {
		MedicalSummary summary = patientService.getPatientMedicalSummary(id);
		return summary;
	}
}
