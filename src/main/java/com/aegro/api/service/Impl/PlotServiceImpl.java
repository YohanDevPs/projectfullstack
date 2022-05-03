package com.aegro.api.service.Impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.repository.FarmRepository;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.PlotService;

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
	private ProductionRepository productionRepository;

	@Override
	public Plot savePlot(Plot plot) {
		return plotRepository.save(plot); 
	}

	@Override
	public List<Plot> plotList() {
		return plotRepository.findAll();
	}
	
	@Override
	public Optional<Plot> getPlotByIdAndProductions(Long id){
		return plotRepository.findById(id);
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
	public Double getProductivityByIdPlot(Long idPlot) {
		
		double productivity = calculateProductivity(idPlot);
		double formattedProductivity = limitDecimalPlace(productivity);
		
		return formattedProductivity;
	}

	@Override
	public void updateProductivityByPlotId(Long idPlot) {
		
		double plotProductivity = getProductivityByIdPlot(idPlot);
		
		Plot plot = plotRepository.findById(idPlot).get();
		
		plot.setPlotProductivity(plotProductivity);
		
		this.plotRepository.save(plot);
	}
	
	private double calculateProductivity(Long idPlot) {
		
		double productionTotal = productionRepository.totalProductionByPlot(idPlot);
		double areaPlot = plotRepository.getById(idPlot).getPlotAreaInHectare();
		double productivity = productionTotal/areaPlot;
		
		return productivity;
	}
	
	private double limitDecimalPlace(double productivity) {
		
		double formattedProductivity = (Math.round(productivity*1000.0)/1000.0);
		
		return formattedProductivity;
	}

}
