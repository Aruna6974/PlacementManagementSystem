package com.placement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.entity.PlacementEntity;
import com.placement.entity.TrainingEntity;

public interface PlacementRepository extends JpaRepository<PlacementEntity, Integer>
{

	List<PlacementEntity> findByTraining(TrainingEntity trainingEntity);

	Optional<PlacementEntity> findByplacementType(String placementtype);

}
