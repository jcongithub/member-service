package com.example.memberservice.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicalSummary {
	private long patientId;
	private String name;
	private Date birthday;
	private String gender;
	private int height;
	private int weight;
	private int age;

	private int numVisists;

	private int minBP;
	private int maxBP;
	private int meanBP;
	private int medianBP;

	private int minHR;
	private int maxHR;
	private int meanHR;
	private int medianHR;

	@JsonIgnore
	private List<Integer> bloodPresuureHistory;
	@JsonIgnore
	private List<Integer> hartRateHistory;
	@JsonIgnore
	private List<String> doctors;

	public double getBmi() {
		return weight * 10000/ (height * height);
	}


	public List<String> getDoctors() {
		return doctors;
	}

	public static MedicalSummary from(Member member, List<Visit> visits) {
		MedicalSummaryBuilder builder = MedicalSummary.builder()
				.patientId(member.getId())
				.birthday(member.getBirthday())
				.name(member.getName())
				.age(member.getAge())
				.gender(member.getGender())
				.height((int) visits.stream().map(Visit::getHeight).filter(w -> w != null).mapToDouble(w -> w).average().orElse(-1))
				.weight((int) visits.stream().map(Visit::getWeight).filter(w -> w != null).mapToDouble(w -> w).average().orElse(-1))
				.minBP(-1).maxBP(-1).meanBP(-1).medianBP(-1)
				.minHR(-1).maxHR(-1).meanHR(-1).medianHR(-1)
				.numVisists(visits.size());
		
		
		
		if(visits.size() > 0) {
			List<Integer> bpList = visits.stream().map(Visit::getBloodPressure).filter(bp -> bp != null)
					.collect(Collectors.toList());
			List<Integer> hrList = visits.stream().map(Visit::getHeartRate).filter(hr -> hr != null)
					.collect(Collectors.toList());
			
			builder.minBP(bpList.stream().mapToInt(hr -> hr).min().orElse(-1))
					.maxBP(bpList.stream().mapToInt(hr -> hr).max().orElse(-1))
					.meanBP((int) bpList.stream().mapToInt(hr -> hr).average().orElse(-1))
					.medianBP((int) median(bpList));
			
			builder.minHR(hrList.stream().mapToInt(hr -> hr).min().orElse(-1))
					.maxHR(hrList.stream().mapToInt(hr -> hr).max().orElse(-1))
					.meanHR((int) hrList.stream().mapToInt(hr -> hr).average().orElse(-1))
					.medianHR((int) median(hrList));
		}
		
		return builder.build();
		
	}
	
	private static double median (List<Integer> numbers) {
		int[] sorted = numbers.stream().mapToInt(bp -> bp).sorted().toArray();
		double median;
		if (sorted.length % 2 == 0)
		    median = ((double)sorted[sorted.length/2] + (double)sorted[sorted.length/2 - 1])/2;
		else
		    median = (double) sorted[sorted.length/2];
		
		return median;
	}
	
	
	

}
