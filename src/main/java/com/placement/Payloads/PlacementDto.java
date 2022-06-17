package com.placement.Payloads;

import com.placement.Entity.TrainingEntity;

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
public class PlacementDto 
{
    private int placementId;
	
	private String placementType;
	
	private String placementDesc;
	
	private String placementCompanyName;
	
	private TrainingEntity training; 

}
