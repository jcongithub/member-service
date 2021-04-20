package com.example.memberservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.memberservice.dao.VisitRepository;
import com.example.memberservice.model.Visit;

@Service
public class MedicalService {
	private VisitRepository visitRepo;
	
	@Autowired
	public MedicalService(VisitRepository visitRepo) {
		this.visitRepo = visitRepo;
	}
	
	public List<Visit> getAllVisits(long patientId, Date start, Date end) {
		//TODO
		return visitRepo.findAllByPatientId(patientId);		
	}
}
