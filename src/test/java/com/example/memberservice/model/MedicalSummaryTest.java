package com.example.memberservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicalSummaryTest {
	private Member patient;
	private Visit visit1, visit2;
	
	@BeforeEach
	public void setup() {
		LocalDate today = LocalDate.now();
		Date birthday = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());		
		patient = new Member();
		patient.setAddress("NYC");
		patient.setBirthday(birthday);
		patient.setGender("M");
		patient.setId(100L);
		patient.setLegalId("111111");
		patient.setName("ABC");
		patient.setPhone("111-222-4444");		
		
		visit1 = new Visit();
		visit1.setBloodPressure(100);
		visit1.setHeartRate(100);
		visit1.setHeight(100);
		visit1.setWeight(100);

		visit2 = new Visit();
		visit2.setBloodPressure(200);
		visit2.setHeartRate(200);
		visit2.setHeight(200);
		visit2.setWeight(200);

	}
	
	@Test
	public void testNoVisit() {
		List<Visit> visits = new ArrayList<>();		
		MedicalSummary actual = MedicalSummary.from(patient, visits);
		assertEquals(-1, actual.getMinBP());
		assertEquals(-1, actual.getMaxBP());
		assertEquals(-1, actual.getMeanBP());
		assertEquals(-1, actual.getMedianBP());
		
		assertEquals(-1, actual.getMinHR());
		assertEquals(-1, actual.getMaxHR());
		assertEquals(-1, actual.getMeanHR());
		assertEquals(-1, actual.getMedianHR());
		
		assertEquals(-1, actual.getHeight());
		assertEquals(-1, actual.getWeight());
	}
	
	@Test
	public void testOneVisit() {		
		MedicalSummary actual = MedicalSummary.from(patient, Arrays.asList(visit1));
		assertEquals(visit1.getBloodPressure(), actual.getMinBP());
		assertEquals(visit1.getBloodPressure(), actual.getMaxBP());
		assertEquals(visit1.getBloodPressure(), actual.getMeanBP());
		assertEquals(visit1.getBloodPressure(), actual.getMedianBP());
		
		assertEquals(visit1.getHeartRate(), actual.getMinHR());
		assertEquals(visit1.getHeartRate(), actual.getMaxHR());
		assertEquals(visit1.getHeartRate(), actual.getMeanHR());
		assertEquals(visit1.getHeartRate(), actual.getMedianHR());
		
		assertEquals((int) visit1.getHeartRate(), actual.getHeight());
		assertEquals((int) visit1.getHeartRate(), actual.getWeight());		
	}
	@Test
	public void testTwoVisit() {		
		MedicalSummary actual = MedicalSummary.from(patient, Arrays.asList(visit1, visit2));
		assertEquals(visit1.getBloodPressure(), actual.getMinBP());
		assertEquals(visit2.getBloodPressure(), actual.getMaxBP());
		assertEquals((int)(visit1.getBloodPressure() + visit2.getBloodPressure())/2, actual.getMeanBP());
		assertEquals((int)(visit1.getBloodPressure() + visit2.getBloodPressure())/2, actual.getMedianBP());
		
		assertEquals(visit1.getHeartRate(), actual.getMinHR());
		assertEquals(visit2.getHeartRate(), actual.getMaxHR());
		assertEquals((int)(visit1.getHeartRate() + visit2.getHeartRate())/2, actual.getMeanHR());
		assertEquals((int)(visit1.getHeartRate() + visit2.getHeartRate())/2, actual.getMedianHR());
		
		assertEquals((int) (visit1.getHeight() + visit2.getHeight())/2, (int) actual.getHeight());
		assertEquals((int) (visit1.getWeight() + visit2.getWeight())/2, (int) actual.getWeight());		
	}
}
