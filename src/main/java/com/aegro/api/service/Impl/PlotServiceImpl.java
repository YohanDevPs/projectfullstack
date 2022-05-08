package com.aegro.api.service.Impl;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.repository.FarmRepository;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductivityPlot;

/**
 * @author Yohan Silva
 */

@Service
public class PlotServiceImpl implements PlotService{

	@Autowired
	private PlotRepository plotRepository;
	
	@Autowired
	private FarmService farmService;
	
	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	private ProductivityPlot productivity;

	@Override
	public Plot savePlot(Plot plot) {
		return plotRepository.save(plot); 
	}

	@Override
	public List<Plot> plotList() {
		return plotRepository.findAll();
	}
	
	@Override
	public Optional<Plot> getPlotByIdWithYourProductions(Long id){
		return Optional.ofNullable(plotRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found "+ id)));
	}
	
	@Override
	public void removePlotById(Long id) {	
		plotRepository.deleteById(id);	
	}
	
	@Override
	public Plot createPlotInFarmId(Plot plot, Long idFarm) {
		
		Farm farm = farmRepository.getById(idFarm);
		
		plot.setFarm(farm);
		
		farm.getPlots().add(plot);
		
		farmService.saveFarm(farm);
		
		return 	plotRepository.save(plot);		
	}
	

	@Override
	public void updateProductivityByPlotId(Long idPlot) {
		productivity.updateProductivityByPlotId(idPlot);
	}

}
