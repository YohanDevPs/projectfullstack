package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Plot;
import com.aegro.api.repository.PlotRepository;

@Service
public class PlotService {

	@Autowired
	private PlotRepository plotRepository;

	public Plot savePlot(Plot plot) {
		return plotRepository.save(plot); 
	}

	public List<Plot> plotList() {
		return plotRepository.findAll();
	}
	
	public Optional<Plot> getPlotById(Long id){
		return plotRepository.findById(id);
	}
	
	public void removePlotById(Long id) {
		plotRepository.deleteById(id);	
	}
}
