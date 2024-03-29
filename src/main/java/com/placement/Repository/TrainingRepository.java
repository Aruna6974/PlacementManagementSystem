package com.placement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.placement.entity.CompanyEntity;
import com.placement.entity.PlacementEntity;
import com.placement.entity.TrainingEntity;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Integer>
{

	//List<TrainingEntity> findByPlacement(PlacementEntity placementEntity);
	
@Query(value = "SELECT * FROM Training train WHERE train.company_company_id = :compid",nativeQuery = true)
	
	public List<TrainingEntity> getTrainingEntityByCompid( @Param("compid") int compid);

	Optional<TrainingEntity> findBytrainingBatch(String batch);

}
