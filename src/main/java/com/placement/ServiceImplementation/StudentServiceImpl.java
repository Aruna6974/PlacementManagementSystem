package com.placement.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placement.Entity.StudentEntity;
import com.placement.Exception.ResourceNotFoundException;
import com.placement.Repository.StudentRepository;
import com.placement.Service.StudentService;
import com.placement.Payloads.StudentDto;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentDto createStudent(StudentDto student) 
	{
		StudentEntity studentEntity = this.studentDtoToStudentEntity(student);
		StudentEntity savedStudent = this.studentRepository.save(studentEntity);
		return this.studentEntityToStudentDto(savedStudent);
	}

	@Override
	public List<StudentDto> getAllStudents() 
	{
		List<StudentEntity> studentList = this.studentRepository.findAll();
		List<StudentDto> studentDtoList = studentList.stream().map(student->this.studentEntityToStudentDto(student)).collect(Collectors.toList());
		return studentDtoList;
	}

	@Override
	public StudentDto getStudentById(int studentId)
	{
		StudentEntity studentEntity = this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student", "StudentId",studentId));
		return this.studentEntityToStudentDto(studentEntity);
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
