package com.placement.Payloads;

import java.util.List;

import com.placement.Entity.CompanyEntity;
import com.placement.Entity.PlacementEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TrainingDto
{
     private int trainingId;
	
	private String trainingName;
	
	private String trainingBatch;
	
	private String trainingYear;

	private CompanyEntity company;
	
	private List<PlacementDto> placementDtoList;
}
