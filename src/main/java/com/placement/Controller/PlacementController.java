package com.placement.Controller;

import java.util.List;

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
import com.placement.Payloads.PlacementDto;
import com.placement.Service.PlacementService;


@RestController
@RequestMapping("/api")
public class PlacementController 
{
	@Autowired
	private PlacementService placementService;
	
	@PostMapping("/training/{trainingId}/placement")
	public ResponseEntity<PlacementDto> createPlacement(@PathVariable int trainingId,@RequestBody PlacementDto placementDto)
	{
		PlacementDto createdPlacement = this.placementService.createPlacement(placementDto, trainingId);
		return new ResponseEntity<PlacementDto>(createdPlacement,HttpStatus.OK);
		
	}

	@GetMapping("/placement/{placementId}")
	public ResponseEntity<PlacementDto> getPlacementById(@PathVariable int placementId)
	{
		PlacementDto getPlacement = this.placementService.getPlacementById(placementId);
		return new ResponseEntity<PlacementDto>(getPlacement,HttpStatus.OK);
	}
	@GetMapping("/placement")
	public ResponseEntity<List<PlacementDto>> getAllPlacements()
	{
		List<PlacementDto> getAllplacementsList = this.placementService.getAllPlacements();
		return new ResponseEntity<List<PlacementDto>>(getAllplacementsList,HttpStatus.OK);
	}
	@PutMapping("/placement/{placementId}")
	public ResponseEntity<PlacementDto> updateplacementById(@PathVariable int placementId,@RequestBody PlacementDto placementDto)
	{
		PlacementDto updatedplacement = this.placementService.updatePlacementById(placementDto, placementId);
		return new ResponseEntity<PlacementDto>(updatedplacement,HttpStatus.OK);
	}
	@DeleteMapping("/placement/{placementId}")
	public ResponseEntity<ApiResponse> deleteplacementById(@PathVariable int placementId)
	{
		this.placementService.deletePlacementById(placementId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Placements Will be Deleted");
		response.setSuccess(true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/placements/{trainingId}")
	public ResponseEntity<List<PlacementDto>> getAllPlacementsByTraining(@PathVariable int trainingId)
	{
		List<PlacementDto> getAllPlacements = this.placementService.getAllPlacementsByTraining(trainingId);
		return new ResponseEntity<List<PlacementDto>>(getAllPlacements,HttpStatus.OK);
	}
}
