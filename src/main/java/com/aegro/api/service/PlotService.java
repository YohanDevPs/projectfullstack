package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Plot;

public interface PlotService {

	Plot savePlot(Plot plot);
	
	List<Plot> plotList();
	
	Optional<Plot> getPlotById(Long id);
	
	void removePlotById(Long id);

}