package com.placement.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placement.Entity.PlacementEntity;
import com.placement.Entity.TrainingEntity;
import com.placement.Exception.ResourceNotFoundException;
import com.placement.Payloads.PlacementDto;
import com.placement.Repository.PlacementRepository;
import com.placement.Repository.TrainingRepository;
import com.placement.Service.PlacementService;
@Service
public class PlacementServiceImpl  implements PlacementService
{
	
	@Autowired
	private PlacementRepository placementRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TrainingRepository trainingRepository;

	@Override
	public PlacementDto createPlacement(PlacementDto placementDto, int trainingId)
	{
		TrainingEntity trainingEntity = this.trainingRepository.findById(trainingId).
				    orElseThrow(
				    		()->new ResourceNotFoundException("Training","TrainingId",trainingId));
		placementDto.setTraining(trainingEntity);
		PlacementEntity placementEntity = this.placementRepository.save(this.placementDtoToPlacementEntity(placementDto));
		
		return this.placementEntityToPlacementDto(placementEntity);
	}

	

	@Override
	public PlacementDto getPlacementById(int placementId)
	{
		PlacementEntity placementEntity = this.placementRepository.findById(placementId).
				     orElseThrow(
				    		 ()->new ResourceNotFoundException("Placement","PlacementId",placementId));
		return this.placementEntityToPlacementDto(placementEntity);
	}

	@Override
	public List<PlacementDto> getAllPlacements() 
	{
		List<PlacementDto> getAllPlacements = this.placementRepository.findAll().stream().map(placement->this.placementEntityToPlacementDto(placement)).collect(Collectors.toList());
		return getAllPlacements;
	}

	@Override
	public PlacementDto updatePlacementById(PlacementDto placementDto, int placementId)
	{
		PlacementEntity placementEntity = this.placementRepository.findById(placementId).
				    orElseThrow(
				    		()->new ResourceNotFoundException("Placement","PlacementId",placementId));
		PlacementEntity updatePlacement = this.placementRepository.save(this.placementDtoToPlacementEntity(placementDto));
		return this.placementEntityToPlacementDto(updatePlacement);
	}

	@Override
	public void deletePlacementById(int placementId)
	{
		PlacementEntity placementEntity = this.placementRepository.findById(placementId).
			    orElseThrow(
			    		()->new ResourceNotFoundException("Placement","PlacementId",placementId));
		this.placementRepository.delete(placementEntity);
		
	}
	private PlacementDto placementEntityToPlacementDto(PlacementEntity placementEntity)
	{
		return this.modelMapper.map(placementEntity, PlacementDto.class);
	}
	private PlacementEntity placementDtoToPlacementEntity(PlacementDto placementDto)
	{
		return this.modelMapper.map(placementDto, PlacementEntity.class);
	}

	@Override
	public List<PlacementDto> getAllPlacementsByTraining(int trainingId) 
	{
		TrainingEntity trainingEntity = this.trainingRepository.findById(trainingId).
			    orElseThrow(
			    		()->new ResourceNotFoundException("Training","TrainingId",trainingId));
		List<PlacementEntity> placements = this.placementRepository.findByTraining(trainingEntity);
		List<PlacementDto> placementDtoList = placements.stream().map(placement->this.modelMapper.map(placement, PlacementDto.class)).collect(Collectors.toList()); 
		
		return placementDtoList;
	}

}
