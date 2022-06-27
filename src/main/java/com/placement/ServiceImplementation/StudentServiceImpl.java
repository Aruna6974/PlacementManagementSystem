package com.placement.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placement.Entity.AdminEntity;
import com.placement.Entity.CompanyEntity;
import com.placement.Entity.StudentEntity;
import com.placement.Entity.TrainingEntity;
import com.placement.Exception.ResourceNotFoundException;
import com.placement.Repository.AdminRepository;
import com.placement.Repository.CompanyRepository;
import com.placement.Repository.StudentRepository;
import com.placement.Service.StudentService;
import com.placement.Payloads.AdminDto;
import com.placement.Payloads.CompanyDto;
import com.placement.Payloads.StudentDto;
import com.placement.Payloads.TrainingDto;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public StudentDto createStudent(StudentDto student,int adminId) 
	{
		AdminEntity adminEntity=this.adminRepository.findById(adminId).
				                orElseThrow(
				                		()->new ResourceNotFoundException("Admin","AdminId",adminId));
		student.setAdmin(this.modelMapper.map(adminEntity, AdminDto.class));
		//StudentEntity studentEntity=this.modelMapper.map(student, StudentEntity.class);
		//studentEntity.setAdmin(adminEntity);
		List<CompanyEntity> company = this.companyRepository.findAll();
		List<CompanyDto> companyDtoList = company.stream().map(companies->this.modelMapper.map(companies, CompanyDto.class)).collect(Collectors.toList());
		student.setCompanyEntity(companyDtoList);
		StudentEntity savedStudent=this.studentRepository.save(this.modelMapper.map(student, StudentEntity.class));
		return this.modelMapper.map(savedStudent, StudentDto.class);
	}

	@Override
	public List<StudentDto> getAllStudents() 
	{
		List<StudentEntity> studentList = this.studentRepository.findAll();
		List<StudentDto> studentDtoList = studentList.stream().map(student->this.modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
		return studentDtoList;
	}

	@Override
	public StudentDto getStudentById(int studentId)
	{
		StudentEntity studentEntity = this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student", "StudentId",studentId));
		return this.modelMapper.map(studentEntity, StudentDto.class);
	}

	@Override
	public StudentDto updateStudentById(StudentDto studentDto, int studentId) 
	{
		StudentEntity studentEntity = this.studentRepository.
				findById(studentId).orElseThrow(
						()->new ResourceNotFoundException("Student", "StudentId",studentId));
		studentEntity.setStudentId(studentDto.getStudentId());
		studentEntity.setStudentName(studentDto.getStudentName());
		studentEntity.setStudentEmail(studentDto.getStudentEmail());
		studentEntity.setStudentContact(studentDto.getStudentContact());
		studentEntity.setStudentQualification(studentDto.getStudentQualification());
		studentEntity.setPassword(studentDto.getPassword());
		return this.studentEntityToStudentDto(studentEntity);
	}

	@Override
	public void deleteStudentById(int studentId) 
	{
		StudentEntity studentEntity = this.studentRepository.
				findById(studentId).orElseThrow(
						()->new ResourceNotFoundException("Student", "StudentId",studentId));
		this.studentRepository.delete(studentEntity);
		
	}
	public StudentEntity studentDtoToStudentEntity(StudentDto studentDto)
	{
		StudentEntity studentEntity = new StudentEntity();
		//UserDto userDto = new UserDto();
		studentEntity.setStudentId(studentDto.getStudentId());
		studentEntity.setStudentName(studentDto.getStudentName());
		studentEntity.setStudentEmail(studentDto.getStudentEmail());
		studentEntity.setStudentContact(studentDto.getStudentContact());
		studentEntity.setStudentQualification(studentDto.getStudentQualification());
		studentEntity.setPassword(studentDto.getPassword());
		return studentEntity;
		
	}
	public StudentDto studentEntityToStudentDto(StudentEntity studentEntity)
	{
		StudentDto studentDto = new StudentDto();
		//UserEntity userEntity = new UserEntity();
		studentDto.setStudentId(studentEntity.getStudentId());
		studentDto.setStudentName(studentEntity.getStudentName());
		studentDto.setStudentEmail(studentEntity.getStudentEmail());
		studentDto.setStudentContact(studentEntity.getStudentContact());
		studentDto.setStudentQualification(studentEntity.getStudentQualification());
		studentDto.setPassword(studentEntity.getPassword());
		return studentDto;
	}


}
