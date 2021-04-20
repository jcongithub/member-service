package com.example.memberservice.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
	private String address;
	private String legalId;
	private Date birthday;
	private String gender;

	@ElementCollection
	@CollectionTable(name = "MEMBER_RULE", joinColumns = @JoinColumn(name = "MEMBER_ID"))
	@Column(name = "RULE_ID")
	private List<String> rules;

	public int getAge() {
		LocalDate today = LocalDate.now();
		LocalDate bday = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period p = Period.between(bday, today);
		return p.getYears();
	}

//	@ManyToMany(cascade={CascadeType.ALL})
//	@JoinTable(name="doctor_patient", joinColumns= {@JoinColumn(name="doctor_id")}, inverseJoinColumns= {@JoinColumn(name="patient_id")})
//	private List<Member> patients;

//	@ManyToMany(cascade={CascadeType.ALL})
//	@JoinTable(name="doctor_patient", joinColumns= {@JoinColumn(name="patient_id")}, inverseJoinColumns= {@JoinColumn(name="doctor_id")})
//	private List<Member> doctors;

}
