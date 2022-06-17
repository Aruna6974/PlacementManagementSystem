package com.placement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.Entity.CompanyEntity;
import com.placement.Entity.TrainingEntity;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Integer>
{

	List<TrainingEntity> findByCompany(CompanyEntity companyEntity);

}
