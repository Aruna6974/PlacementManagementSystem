package com.placement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.entity.CompanyEntity;
import com.placement.entity.TrainingEntity;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Integer>
{

	List<TrainingEntity> findByCompany(CompanyEntity companyEntity);

	Optional<TrainingEntity> findBytrainingBatch(String batch);

}
