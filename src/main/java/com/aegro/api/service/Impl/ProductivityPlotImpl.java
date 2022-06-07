package com.aegro.api.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
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
	public void updatePlotProductivityWhenCreateProduction(Production production, Long idPlot) {
		
		Plot plot = plotRepository.getById(idPlot);		
		
		double newProductionAmount = production.getAmount() + converteNullToZero(productionRepository.totalProductionByPlot(idPlot));
	
		double areaPlot = plotRepository.getById(idPlot).getPlotAreaInHectare();
		
		try {
			double productivity = newProductionAmount/areaPlot;
			plot.setPlotProductivity(limitDecimalPlace(productivity));	
			} catch (ArithmeticException e) {
			e.getCause();
			}		
	}
	
	@Override
	public void updatePlotProductivityWhenDeleteProduction(Long idProduction) {
		
		Production production = productionRepository.getById(idProduction);
		
		Plot plot = production.getPlot();

		double oldProductionPlot = converteNullToZero(productionRepository.totalProductionByPlot(idProduction));
		double newProductionPlot = oldProductionPlot - production.getAmount();
	
		try {
			double newProductivityPlot = newProductionPlot/plot.getPlotAreaInHectare();
			plot.setPlotProductivity(limitDecimalPlace(newProductivityPlot));
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
	
	@Override
	public void updateProductivityPlot(Plot plot) {

		double newPlotArea = plot.getPlotAreaInHectare();
		double productionPlot = getProductionByPlotId(plot.getIdPlot());
				
		try {
			double newProductivityPlot = productionPlot/newPlotArea;
			double newnewProductivityPlotFormated = limitDecimalPlace(newProductivityPlot);
			plot.setPlotProductivity(newnewProductivityPlotFormated);
		} catch (ArithmeticException e) {
			e.getCause();
		}
		
	}

	@Override
	public Double getProductionByPlotId(Long idPlot) {
		return converteNullToZero(productionRepository.totalProductionByPlot(idPlot));		
	}

	@Override
	public Double limitDecimalPlace(double numberToFormat) {
		double formatedNumber = (Math.round(numberToFormat * 100.0) / 100.0);
		return formatedNumber;
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


}
