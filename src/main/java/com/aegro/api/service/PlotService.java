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
	
	Optional<Plot> getPlotByIdAndProductions(Long id);

	void removePlotById(Long id);

	void updateProductivityByPlotId(Long idPlot);

//	Double getProductivityByIdPlot(Long idPlot);

	Plot createPlotInFarmId(Plot plot, Long idFarm);

}