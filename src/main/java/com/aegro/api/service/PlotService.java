package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Plot;

/**
 * @author Yohan Silva
 */

public interface PlotService {

	Plot savePlot(Plot plot);
	
	List<Plot> plotList();
	
	Optional<Plot> getPlotByIdWithYourProductions(Long id);

	void removePlotById(Long id);

	Plot createPlotInFarmId(Plot plot, Long idFarm);
	
	List<Plot> plotListOfFarmId(Long idFarm);

		
}