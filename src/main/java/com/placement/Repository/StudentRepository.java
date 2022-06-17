package com.placement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.Entity.StudentEntity;



public interface StudentRepository extends JpaRepository<StudentEntity,Integer>
{

}
