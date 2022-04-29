package com.aegro.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aegro.api.entities.Plot;

public interface PlotRepository extends JpaRepository<Plot, Long> {
	
	@Query(value = "SELECT * FROM PLOT p WHERE p.FARM_ID = ?1", nativeQuery = true)
	List<Plot> getAllPlotsByFarmId(Long idFarm);
	
}
