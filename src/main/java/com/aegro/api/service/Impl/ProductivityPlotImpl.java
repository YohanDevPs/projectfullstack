package com.aegro.api.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Plot;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.ProductivityPlot;

/**
 * @author Yohan Silva
 */

@Service
public class ProductivityPlotImpl implements ProductivityPlot {

	@Autowired
	private PlotRepository plotRepository;
	
	@Autowired
	private ProductionRepository productionRepository;
	
	
	@Override
	public Double getProductivityByIdPlot(Long idPlot) {
		
		double productivity = calculatePlotProductivity(idPlot);
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
	

	private Double calculatePlotProductivity(Long idPlot) {
		
		double productionTotal = productionRepository.totalProductionByPlot(idPlot);
		double areaPlot = plotRepository.getById(idPlot).getPlotAreaInHectare();
		double productivity = productionTotal/areaPlot;
		
		return productivity;
	}
	
	private Double limitDecimalPlace(double productivity) {
		
		double formattedProductivity = (Math.round(productivity*1000.0)/1000.0);
		
		return formattedProductivity;
	}
	
}
