package com.placement.Service;

import java.util.List;

import com.placement.Payloads.StudentDto;



public interface StudentService 
{
	public StudentDto createStudent(StudentDto student,int adminId);
	
	
	public StudentDto getStudentById(int studentId);
	
	public StudentDto updateStudentById(StudentDto student,int studentId);
	
	public void deleteStudentById(int studentId);

	public List<StudentDto> getAllStudents();

}
