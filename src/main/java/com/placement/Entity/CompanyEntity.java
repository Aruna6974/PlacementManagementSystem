package com.placement.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class CompanyEntity
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int companyId;
	
	@Column(nullable = false)
	private String companyName;
	
	@Column(nullable = false)
	private String companyAddress;
	
	@Column(nullable = false)
	private String companyType;
	
	@Column(nullable = false)
	private String companyDescription;
	@ManyToOne
	private StudentEntity student;
	@OneToMany(cascade = CascadeType.ALL)
	private List<TrainingEntity> trainingEntity = new ArrayList<TrainingEntity>();
	

}
