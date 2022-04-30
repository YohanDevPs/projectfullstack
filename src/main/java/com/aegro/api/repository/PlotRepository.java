package com.aegro.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aegro.api.entities.Plot;

/**
 * @author Yohan Silva
 */

@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {

	
}
