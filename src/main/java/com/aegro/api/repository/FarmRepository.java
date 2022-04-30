package com.aegro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aegro.api.entities.Farm;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long>{
	
	
}
