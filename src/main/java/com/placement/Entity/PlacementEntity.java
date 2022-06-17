package com.placement.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlacementEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int placementId;
	
	@Column(nullable = false)
	private String placementType;
	
	@Column(nullable = false)
	private String placementDesc;
	
	@Column(nullable = false)
	private String placementCompanyName;
	
	@ManyToOne
	private TrainingEntity training;
	

}
