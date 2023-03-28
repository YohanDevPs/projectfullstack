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

	public static final double AMOUNT_DEFAULT = 0.0;
	@Autowired
	private ProductionRepository productionRepository;
	@Autowired
	private PlotRepository plotRepository;

	@Override
	public void updatePlotProductivity(Plot plot) {
		double plotArea = plot.getPlotAreaInHectare();
		double productionPlot = getProductionByPlotId(plot.getIdPlot());
				
		try {
			plot.setPlotProductivity(limitDecimalPlace(productionPlot/plotArea));
			plotRepository.save(plot);
		} catch (ArithmeticException e) {
			e.getCause();
		}
		
	}

	@Override
	public void updateProductivityPlotWhenChangeArea(Long idPlot, Plot newPlot) {
		double newPlotArea = newPlot.getPlotAreaInHectare();
		double productionPlot = getProductionByPlotId(idPlot);
		
		Plot plot = plotRepository.getById(idPlot);
		
		try {
			newPlot.setPlotProductivity(limitDecimalPlace( productionPlot/newPlotArea));
			plot.setPlotProductivity(limitDecimalPlace( productionPlot/newPlotArea));
		} catch (ArithmeticException e) {
			e.getCause();
		}
	}
	
	public Double converteNullToZero(Double value) {
		Object obj = value;
		
		if(obj != null) {
		     return Double.parseDouble(obj.toString());
		}
		else {
		     return AMOUNT_DEFAULT;
		}
	}

	public Double getProductionByPlotId(Long idPlot) {
		return converteNullToZero(productionRepository.totalProductionByPlot(idPlot));		
	}

	public Double limitDecimalPlace(double numberToFormat) {
		return (Math.round(numberToFormat * 100.0) / 100.0);
	}
}

