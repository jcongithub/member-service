package com.example.memberservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memberservice.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long>{
	List<Visit> findAllByPatientId(long patientId);
}
