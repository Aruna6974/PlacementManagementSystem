package com.placement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.Entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer>
{

}
