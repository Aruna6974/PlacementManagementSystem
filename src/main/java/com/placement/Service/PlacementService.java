package com.placement.Service;

import java.util.List;

import com.placement.Payloads.PlacementDto;

public interface PlacementService 
{
	 public PlacementDto createPlacement(PlacementDto placementDto,int trainingId);
	
     public PlacementDto getPlacementById(int placementId);
     
     public List<PlacementDto> getAllPlacements();
     
     public PlacementDto updatePlacementById(PlacementDto placementDto,int placementId);
     
     public void deletePlacementById(int placementId);
     
     public List<PlacementDto> getAllPlacementsByTraining(int trainingId);
}

