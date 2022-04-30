package com.aegro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aegro.api.entities.Production;

/**
 * @author Yohan Silva
 */

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long>{

	@Query("select SUM(production) "
			+ "FROM Production "
			+ "WHERE PLOT_ID = ?1")
	Double totalProductionByPlot(Long id);
	
}
