package com.aegro.api.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.repository.FarmRepository;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.ProductivityFarm;

/**
 * @author Yohan Silva
 */

@Service
public class ProductivityFarmImpl implements ProductivityFarm{

	@Autowired
	private ProductionRepository productionRepository;

	@Autowired
	private PlotRepository plotRepository;
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Override
	public void updateFarmProductivityWhenCreateProduction(Production production, Long idPlot) {
		
		Plot plot = plotRepository.getById(idPlot);
		Farm farm = plot.getFarm();
		
		double newProduction = getProductionFarmById(farm.getId()) + production.getAmount();
		
		try {
			double newFarmProductivity = newProduction/plotRepository.totalAreaByFarmId(farm.getId());
			farm.setFarmProductivity(limitDecimalPlace(newFarmProductivity));
			} catch (ArithmeticException e) {
			e.getCause();
			}

	}
	
	@Override
	public void updateFarmProductivityWhenDeleteProduction(Long idProduction) {
		
		Production production = productionRepository.getById(idProduction);
		Plot plot = production.getPlot();
				
		Farm farm = plot.getFarm();
		Long farmId = farm.getId();
		
		double areaFarm = plotRepository.totalAreaByFarmId(farmId);
		double productionOfFarm = getProductionFarmById(farmId);
		double newProductionFarm = productionOfFarm - production.getAmount();
		
		try {
			double newProductivityFarm = newProductionFarm/areaFarm;
			farm.setFarmProductivity(limitDecimalPlace(newProductivityFarm));			
		} catch (ArithmeticException e) {
			e.getCause();
		}
		
	}

	
	@Override
	public void updateFarmProductivityWhenDeletePlot(Long idPlot) {
		
		Plot plot = plotRepository.getById(idPlot);
		double productionPlot = converteNullToZero(productionRepository.totalProductionByPlot(idPlot));
		double areaPlot = plot.getPlotAreaInHectare();
		
		Farm farm =	plot.getFarm();
		
		double newProductionFarm = getProductionFarmById(farm.getId()) - productionPlot;
		double newAreaFarm = converteNullToZero(plotRepository.totalAreaByFarmId(idPlot)) - areaPlot;
		
		double productivityFarm = newProductionFarm/newAreaFarm;
		double formatedProductivity = limitDecimalPlace(productivityFarm);

		farm.setFarmProductivity(formatedProductivity);
	}

	
	@Override
	public void updateProductivityFarmWhenCreatePlot(Plot plot, Long idFarm) {
		
		Farm farm = farmRepository.getById(idFarm);
		double productionByFarmId = getProductionFarmById(idFarm);
		double oldAreaOfFarmId = getTotalAreaByFarmId(idFarm);
		double areaNewPlot = oldAreaOfFarmId + plot.getPlotAreaInHectare();
		
		try {
			double newProductivityOfFarm = productionByFarmId/areaNewPlot;
			double newProdcutionOfFarmFormated = limitDecimalPlace(newProductivityOfFarm);
			
			farm.setFarmProductivity(newProdcutionOfFarmFormated);
			
		} catch (ArithmeticException e) {
				e.getCause();
		}
	}

	@Override
	public void updateFarmProductivityWhenUpdatePlot(Long idPlot,Plot newPlot) {
		
		Optional<Plot> oldPlot = plotRepository.findById(idPlot);
		
		double oldAreaOfPlot = oldPlot.get().getPlotAreaInHectare();
		double newPlotArea = newPlot.getPlotAreaInHectare();
	
		Farm farm = oldPlot.get().getFarm();
		Long idFarm = farm.getId();
		
		double oldAreaOfFarm = getTotalAreaByFarmId(idFarm);
		double newAreaOfFarm = oldAreaOfFarm - oldAreaOfPlot + newPlotArea;
		double productionFarm = getProductionFarmById(idFarm);
		
		try {
			double newProductivityFarm = productionFarm/newAreaOfFarm;
			double newFormatedProductivityFarm = limitDecimalPlace(newProductivityFarm);
			
			farm.setFarmProductivity(newFormatedProductivityFarm);
			
		} catch (Exception e) {
			e.getCause();
		}
	}
	

	@Override
	public Double getTotalAreaByFarmId(Long idFarm) {
				
		Object obj = plotRepository.totalAreaByFarmId(idFarm);
		
		if(obj != null) {
		     double totalAreaByFarmId = Double.parseDouble(obj.toString());
		     return totalAreaByFarmId;
		}
		else {
		     double totalAreaByFarmId = 0.0;
		     return totalAreaByFarmId; 		
		}
		
	}
	
	@Override
	public Double getProductionFarmById(Long idFarm) {

		List<Plot> allPlots = plotRepository.findAll();

		double sumProductionFarm = 0;

		for (Plot plot : allPlots) {
			
			if (plot.getFarm().getId() == idFarm) {
				Long idPlot = plot.getIdPlot();
				Object obj = productionRepository.totalProductionByPlot(idPlot);
				if(obj != null) {
				     double productionFarm = Double.parseDouble(obj.toString());
				     sumProductionFarm += productionFarm;
				}
				else {
					sumProductionFarm += 0;
				}
			}
		}
		return sumProductionFarm;
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

	public Double limitDecimalPlace(double productivityFarm) {
		double formattedProductivity = (Math.round(productivityFarm * 100.0) / 100.0);
		return formattedProductivity;
	}


}
