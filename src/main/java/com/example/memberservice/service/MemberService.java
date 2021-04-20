package com.example.memberservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.memberservice.dao.MemberRepository;
import com.example.memberservice.dao.VisitRepository;
import com.example.memberservice.model.Member;
import com.example.memberservice.model.Visit;

@Service
public class MemberService {
	private MemberRepository memberRepo;
	private VisitRepository visitRepo;
	
	@Autowired
	public MemberService(MemberRepository memberRepo, VisitRepository visitRepo) {
		this.memberRepo = memberRepo;
		this.visitRepo = visitRepo;
	}
	
	public List<Member> listAll() {
		return memberRepo.findAll(); 
	}
	
	public Optional<Member> findByPatientId(long patientId) {
		return memberRepo.findById(patientId);
	}
	
	public Member create(Member member) {
		return memberRepo.saveAndFlush(member);
	}
	
	public Member update(Member member) {
		return memberRepo.saveAndFlush(member);		
	}
	
	
	
	
	
	
	public List<Visit> listVisits(long patientId) {
		return visitRepo.findAllByPatientId(patientId); 
	}

}
