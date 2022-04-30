package com.aegro.api.service.Impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Plot;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.service.PlotService;

/**
 * @author Yohan Silva
 */

@Service
public class PlotServiceImpl implements PlotService{

	@Autowired
	private PlotRepository plotRepository;

	@Override
	public Plot savePlot(Plot plot) {
		return plotRepository.save(plot); 
	}

	@Override
	public List<Plot> plotList() {
		return plotRepository.findAll();
	}
	
	@Override
	public Optional<Plot> getPlotById(Long id){
		return plotRepository.findById(id);
	}
	
	@Override
	public void removePlotById(Long id) {
		plotRepository.deleteById(id);	
	}
	
}