package com.placement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.Entity.PlacementEntity;
import com.placement.Entity.TrainingEntity;

public interface PlacementRepository extends JpaRepository<PlacementEntity, Integer>
{

	List<PlacementEntity> findByTraining(TrainingEntity trainingEntity);

}