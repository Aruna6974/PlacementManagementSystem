package com.placement.payloads;

import java.util.List;

import com.placement.entity.AdminEntity;

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
public class StudentDto
{
	private int studentId;
	
	private String studentName;
	
	private String studentEmail;
	
	private String studentContact;
	
	private String studentQualification;
	
	private String password;
	
	private AdminEntity admin;
	
	private List<CompanyDto> compayDtoList;
	
	
}
