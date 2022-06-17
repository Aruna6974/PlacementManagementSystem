package com.placement.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.placement.Entity.CompanyEntity;
import com.placement.Entity.StudentEntity;
import com.placement.Entity.TrainingEntity;
import com.placement.Exception.ResourceNotFoundException;
import com.placement.Payloads.CompanyDto;
import com.placement.Payloads.TrainingDto;
import com.placement.Repository.CompanyRepository;
import com.placement.Repository.StudentRepository;
import com.placement.Repository.TrainingRepository;
import com.placement.Service.CompanyService;
@Service
public class CompanyServiceImplementation  implements CompanyService
{
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TrainingRepository trainingRepository;

	@Override
	public CompanyDto createCompany(CompanyDto companyDto, int studentId)
	{
		StudentEntity studentEntity = this.studentRepository.findById(studentId).
				  orElseThrow(
						  ()->new ResourceNotFoundException("Student","StudentId",studentId));
		companyDto.setStudent(studentEntity);
		
		List<TrainingEntity> trainings = this.trainingRepository.findAll();
		List<TrainingDto> trainingDtoList = trainings.stream().map(training->this.modelMapper.map(training, TrainingDto.class)).collect(Collectors.toList());
		companyDto.setTrainingDtoList(trainingDtoList);
		CompanyEntity companyEntity =this.companyRepository.save(this.companyDtoToCompanyEntity(companyDto));
		
		return this.companyEntityToCompanyDto(companyEntity);
		
	}

	@Override
	public CompanyDto getCompanyById(int companyId)
	{
		CompanyEntity companyEntity = this.companyRepository.findById(companyId).
				           orElseThrow(
				        		   ()-> new ResourceNotFoundException("Company","CompanyId",companyId));		
		return this.companyEntityToCompanyDto(companyEntity);
	}

	@Override
	public List<CompanyDto> getAllCompanies() 
	{
		List<CompanyDto> getAllCompanies = this.companyRepository.findAll().stream().map(company->this.companyEntityToCompanyDto(company)).collect(Collectors.toList());
		return getAllCompanies;
	}

	@Override
	public CompanyDto updateCompaniesById(CompanyDto companyDto, int companyId) 
	{
		CompanyEntity companyEntity = this.companyRepository.findById(companyId).
				orElseThrow(
						()->new ResourceNotFoundException("Company","CompanyId",companyId));
		CompanyEntity updatedCompanyEntity = this.companyRepository.save(this.companyDtoToCompanyEntity(companyDto));
		return this.companyEntityToCompanyDto(updatedCompanyEntity);
		
	}

	@Override
	public void deleteCompanyById(int companyId)
	{

		CompanyEntity companyEntity = this.companyRepository.findById(companyId).
				orElseThrow(
						()->new ResourceNotFoundException("Company","CompanyId",companyId));
		this.companyRepository.delete(companyEntity);		
	}

	@Override
	public List<CompanyDto> getAllCompaniesByStudent(int studentId) 
	{
		StudentEntity studentEntity = this.studentRepository.findById(studentId).
				orElseThrow(
				             ()->new ResourceNotFoundException("Student","StudentId",studentId));
		List<CompanyEntity> companies = this.companyRepository.findByStudent(studentEntity);
		List<CompanyDto> companyDtoList = companies.stream().map(company->this.modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
		return companyDtoList;
	}
	
	private CompanyDto companyEntityToCompanyDto(CompanyEntity companyEntity)
	{
		return this.modelMapper.map(companyEntity, CompanyDto.class);
	}

	private CompanyEntity companyDtoToCompanyEntity(CompanyDto companyDto)
	{
		return this.modelMapper.map(companyDto, CompanyEntity.class);
	}


}
