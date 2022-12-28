package com.aegro.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aegro.api.entities.Plot;

/**
 * @author Yohan Silva
 */

@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {

	@Query("select SUM(plotAreaInHectare) "
			+ "FROM Plot "
			+ "WHERE FARM_ID  = ?1")
	Double totalAreaByFarmId(Long id);
}
