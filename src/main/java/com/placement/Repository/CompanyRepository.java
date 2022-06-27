package com.placement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.Entity.CompanyEntity;
import com.placement.Entity.StudentEntity;
import com.placement.Payloads.CompanyDto;

public interface CompanyRepository  extends JpaRepository<CompanyEntity,Integer>
{

	List<CompanyEntity> findByStudent(StudentEntity studentEntity);

	Optional<CompanyEntity> findBycompanyType(String companyType);

}
