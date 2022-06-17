package com.placement.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placement.Exception.ApiResponse;
import com.placement.Payloads.TrainingDto;
import com.placement.Service.TrainingService;

@RestController
@RequestMapping("/api")
public class TrainingController 
{

	@Autowired
	private TrainingService trainingService;
	
	@PostMapping("/company/{companyId}/training")
	public ResponseEntity<TrainingDto> createTraining( @Valid @PathVariable int companyId,@RequestBody TrainingDto trainingDto)
	{
		TrainingDto createTrainingDto =this.trainingService.createTraining(trainingDto, companyId); 
		return new ResponseEntity<TrainingDto>(createTrainingDto,HttpStatus.CREATED);
		
	}
	@GetMapping("/training/{trainingId}")
	public ResponseEntity<TrainingDto> getTrainingById(@PathVariable int trainingId)
	{
		TrainingDto getTraining = this.trainingService.getTrainingById(trainingId);
		return new ResponseEntity<TrainingDto>(getTraining,HttpStatus.OK);
	}
	@GetMapping("/training")
	public ResponseEntity<List<TrainingDto>> getAllTrainings()
	{
		List<TrainingDto> getTrainings = this.trainingService.getAllTrainings();
		return new ResponseEntity<List<TrainingDto>>(getTrainings,HttpStatus.OK);
		
	}
	@PutMapping("/training/{trainingId}")
	public ResponseEntity<TrainingDto> updateTrainingById(@RequestBody TrainingDto trainingDto,@PathVariable int trainingId)
	{
		TrainingDto updatedTraining = this.trainingService.updateTrainingById(trainingDto, trainingId);
		return new ResponseEntity<TrainingDto>(updatedTraining,HttpStatus.OK);
	}
	@DeleteMapping("/training/{trainingId}")
	public ResponseEntity<ApiResponse> deleteTrainingById(@PathVariable int trainingId)
	{
		this.trainingService.deleteTrainingById(trainingId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Training details will be deleted");
		response.setSuccess(true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/trainings/{companyId}")
	public ResponseEntity<List<TrainingDto>> getAllTrainingsByCompany(@PathVariable int companyId)
	{
		List<TrainingDto> getAllTrainings = this.trainingService.getAllTrainingsByCompany(companyId);
		return new ResponseEntity<List<TrainingDto>>(getAllTrainings,HttpStatus.OK);
	}

}
