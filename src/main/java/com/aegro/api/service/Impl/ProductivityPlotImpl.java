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
	private ProductionRepository productionRepository;
	@Autowired
	private PlotRepository plotRepository;

	@Override
	public void updatePlotProductivity(Plot plot) {
		double newPlotArea = plot.getPlotAreaInHectare();
		double productionPlot = getProductionByPlotId(plot.getIdPlot());
				
		try {
			double newProductivityPlot = productionPlot/newPlotArea;
			double newProductivityPlotFormated = limitDecimalPlace(newProductivityPlot);
			plot.setPlotProductivity(newProductivityPlotFormated);
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
			double newProductivityPlot = productionPlot/newPlotArea;
			double newnewProductivityPlotFormated = limitDecimalPlace(newProductivityPlot);
			newPlot.setPlotProductivity(newnewProductivityPlotFormated);	
			plot.setPlotProductivity(newnewProductivityPlotFormated);
		} catch (ArithmeticException e) {
			e.getCause();
		}
	}
	
	public Double converteNullToZero(Double value) {
		Object obj = value;
		
		if(obj != null) {
		     double amount = Double.parseDouble(obj.toString());
		     return amount;
		}
		else {
		     double amountDefault = 0.0;
		     return amountDefault; 		
		}
	}

	public Double getProductionByPlotId(Long idPlot) {
		return converteNullToZero(productionRepository.totalProductionByPlot(idPlot));		
	}
	
<<<<<<< HEAD
=======
	
	public Double getPlotProductivity(Plot plot) {
		double productionPlot = getProductionByPlotId(plot.getIdPlot());
		double areaPlot = plot.getPlotAreaInHectare();
		
		try {
			double newProductivity = limitDecimalPlace(productionPlot/areaPlot);
			return newProductivity;
		} catch (ArithmeticException e) {
			e.getCause();
			return -1.0;
		}		
	}
	
>>>>>>> ae8f500090ef496cef27bb11e4993218f51bd3ad
	public Double limitDecimalPlace(double numberToFormat) {
		double formatedNumber = (Math.round(numberToFormat * 100.0) / 100.0);
		return formatedNumber;
	}
}

