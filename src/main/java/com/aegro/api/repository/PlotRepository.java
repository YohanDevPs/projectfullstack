package com.aegro.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aegro.api.entities.Plot;

/**
 * @author Yohan Silva
 */

public interface PlotRepository extends JpaRepository<Plot, Long> {

}
