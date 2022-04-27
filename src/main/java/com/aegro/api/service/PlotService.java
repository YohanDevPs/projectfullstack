package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Plot;

public interface PlotService {

	public Plot savePlot(Plot plot);
	
	public List<Plot> plotList();
	
	public Optional<Plot> getPlotById(Long id);
	
	public void removePlotById(Long id);

}