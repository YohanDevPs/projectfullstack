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
	public void updateFarmProductivity(Farm farm){
		try {
			farm.setFarmProductivity(limitDecimalPlace(getFarmProductivity(farm)));
			farmRepository.save(farm);	
		}catch (ArithmeticException e) {
			e.getMessage();
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
			
		} catch (ArithmeticException e) {
			e.getCause();
		}
	}

	
	public Double getFarmProductivity(Farm farm) {
		try {
			return limitDecimalPlace(
					getProductionFarmById(farm.getId())/getTotalAreaByFarmId(farm.getId())
			);
		} catch (ArithmeticException e) {
			e.getCause();
			return -1.0;
		}		
	}
	
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

	public Double limitDecimalPlace(double productivityFarm) {
		double formattedProductivity = (Math.round(productivityFarm * 100.0) / 100.0);
		return formattedProductivity;
	}
}


