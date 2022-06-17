package com.placement.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Entity
@Table(name="student")
public class StudentEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentId;
	
	@Column(nullable=false)
	private String studentName;
	
	@Column(nullable=false)
	private String studentEmail;
	
	@Column(nullable=false)
	private String studentContact;

	@Column(nullable=false)
	private String studentQualification;
	
	@Column(nullable=false)
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<CompanyEntity> companyEntity=new ArrayList();
	


}
