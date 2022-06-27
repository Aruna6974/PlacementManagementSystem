package com.placement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.Entity.CompanyEntity;
import com.placement.Entity.TrainingEntity;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Integer>
{

	List<TrainingEntity> findByCompany(CompanyEntity companyEntity);

	Optional<TrainingEntity> findBytrainingBatch(String batch);

}
