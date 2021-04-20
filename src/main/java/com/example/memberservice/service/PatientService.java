package com.example.memberservice.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.memberservice.model.MedicalSummary;
import com.example.memberservice.model.Member;
import com.example.memberservice.model.Visit;

@Service
public class PatientService {
	private MedicalService medicalService;
	private MemberService memberService;

	public PatientService(MedicalService medicalService, MemberService memberService) {
		this.medicalService = medicalService;
		this.memberService = memberService;
	}

	public MedicalSummary getPatientMedicalSummary(long patientId) throws IllegalArgumentException{
		Member member = memberService.findByPatientId(patientId).orElseThrow(IllegalArgumentException::new);
		LocalDate now = LocalDate.now();
		LocalDate start = now.plusMonths(-12);
		List<Visit> visits = medicalService.getAllVisits(patientId, Date.valueOf(start), Date.valueOf(now));
		
		return MedicalSummary.from(member, visits);
	}
}
