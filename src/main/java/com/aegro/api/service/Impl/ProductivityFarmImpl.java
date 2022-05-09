package com.aegro.api.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
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
	private FarmRepository farmRepository;

	@Autowired
	private ProductionRepository productionRepository;

	@Autowired
	private PlotRepository plotRepository;

	@Override
	public void updateProductivityFarm(Long idFarm) {
		
		double productivityFarm = getFarmProductivityById(idFarm);
		
		Farm farm = farmRepository.findById(idFarm).get();
		
		farm.setFarmProductivity(productivityFarm);
		
		farmRepository.save(farm);
	}

	
	@Override
	public Double getTotalAreaByFarmId(Long idFarm) {
		return plotRepository.totalAreaByFarmId(idFarm);
	}

	@Override
	public Double getProductionFarmById(Long idFarm) {

		List<Plot> allPlots = plotRepository.findAll();

		double sumProductionFarm = 0;

		for (Plot plot : allPlots) {

			if (plot.getFarm().getId() == idFarm) {
				Long idPlot = plot.getIdPlot();
				sumProductionFarm += productionRepository.totalProductionByPlot(idPlot);
			}
		}

		return sumProductionFarm;
	}

	@Override
	public Double getFarmProductivityById(Long idFarm) {

		double productivityFarm = calculateProductivityFarm(idFarm);
		double productivityFormatted = limitDecimalPlace(productivityFarm);

		return productivityFormatted;
	}

	private Double calculateProductivityFarm(Long idFarm) {

		double c = getProductionFarmById(idFarm);
		double b = getTotalAreaByFarmId(idFarm);

		try {
			double productivityFarm = c / b;
			return productivityFarm;
		} 
		catch (ArithmeticException e) {
			e.getCause();
		}
		
		return null;
	}

	private Double limitDecimalPlace(double productivityFarm) {
		
		double formattedProductivity = (Math.round(productivityFarm * 1000.0) / 1000.0);
		
		return formattedProductivity;
	}

}
