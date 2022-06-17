package com.placement.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placement.Entity.CompanyEntity;
import com.placement.Entity.PlacementEntity;
import com.placement.Entity.TrainingEntity;
import com.placement.Exception.ResourceNotFoundException;
import com.placement.Payloads.PlacementDto;
import com.placement.Payloads.TrainingDto;
import com.placement.Repository.CompanyRepository;
import com.placement.Repository.PlacementRepository;
import com.placement.Repository.TrainingRepository;
import com.placement.Service.TrainingService;
@Service
public class TrainingServiceImpl implements TrainingService
{
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CompanyRepository companyrepository;
	
	@Autowired
	private PlacementRepository placementRepository;
	
	@Override
	public TrainingDto createTraining(TrainingDto trainingDto,int companyId)
	{
		CompanyEntity companyEntity = this.companyrepository.findById(companyId).
				       orElseThrow(
				    		   ()->new ResourceNotFoundException("Company", "CompanyId", companyId));
		trainingDto.setCompany(companyEntity);
		List<PlacementEntity> placements = this.placementRepository.findAll();
		List<PlacementDto> placementDtoList = placements.stream().map(placement->this.modelMapper.map(placement, PlacementDto.class)).collect(Collectors.toList());
		trainingDto.setPlacementDtoList(placementDtoList);
		TrainingEntity trainingEntity = this.trainingRepository.save(this.trainingDtoToTrainingEntity(trainingDto));
		return this.trainingEntityToTrainingDto(trainingEntity);
	}

	

	@Override
	public List<TrainingDto> getAllTrainings()
	{
		List<TrainingDto> getAllTrainings = this.trainingRepository.findAll().stream().map(training->this.trainingEntityToTrainingDto(training)).collect(Collectors.toList());
		return getAllTrainings;
	}

	@Override
	public TrainingDto getTrainingById(int trainingId)
	{
		TrainingEntity trainingEntity = this.trainingRepository.findById(trainingId).
				     orElseThrow(
				    		 ()->new ResourceNotFoundException("Training", "TrainingId", trainingId));
		return this.trainingEntityToTrainingDto(trainingEntity);
	}

	@Override
	public TrainingDto updateTrainingById(TrainingDto trainingDto, int trainingId) 
	{
		TrainingEntity trainingEntity = this.trainingRepository.findById(trainingId).
				         orElseThrow(
				        		  ()->new ResourceNotFoundException("Training","TrainingId",trainingId));
		TrainingEntity updateTraining = this.trainingRepository.save(this.trainingDtoToTrainingEntity(trainingDto));
		return this.trainingEntityToTrainingDto(updateTraining);
	}

	@Override
	public void deleteTrainingById(int trainingId)
	{
		TrainingEntity trainingEntity = this.trainingRepository.findById(trainingId).
		         orElseThrow(
		        		  ()->new ResourceNotFoundException("Training","TrainingId",trainingId));
        this.trainingRepository.delete(trainingEntity);
		
	}

	@Override
	public List<TrainingDto> getAllTrainingsByCompany(int companyId) 
	{
		CompanyEntity companyEntity = this.companyrepository.findById(companyId).
				   orElseThrow(
						   ()->new ResourceNotFoundException("Company","CompanyId",companyId));
		List<TrainingEntity> trainings = this.trainingRepository.findByCompany(companyEntity);
		List<TrainingDto> trainingDtoList = trainings.stream().map(training->this.modelMapper.map(training, TrainingDto.class)).collect(Collectors.toList());
		
		return trainingDtoList;
	}

	private TrainingDto trainingEntityToTrainingDto(TrainingEntity trainingEntity)
	{
		return this.modelMapper.map(trainingEntity, TrainingDto.class);
	}
	private TrainingEntity trainingDtoToTrainingEntity(TrainingDto trainingDto)
	{
		return this.modelMapper.map(trainingDto, TrainingEntity.class);
		
	}



}
