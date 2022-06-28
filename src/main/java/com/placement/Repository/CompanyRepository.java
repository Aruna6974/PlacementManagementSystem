package com.placement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.entity.CompanyEntity;
import com.placement.entity.StudentEntity;
import com.placement.payloads.CompanyDto;

public interface CompanyRepository  extends JpaRepository<CompanyEntity,Integer>
{

	List<CompanyEntity> findByStudent(StudentEntity studentEntity);

	Optional<CompanyEntity> findBycompanyType(String companyType);

}
