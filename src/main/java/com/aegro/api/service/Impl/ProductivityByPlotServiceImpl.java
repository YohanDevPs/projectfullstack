package com.aegro.api.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.ProductivityByPlot;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.repository.ProductivityByPlotRepository;
import com.aegro.api.service.ProductivityByPlotService;

@Service
public class ProductivityByPlotServiceImpl implements ProductivityByPlotService {

	@Autowired
	private ProductionRepository repository;

	@Autowired
	private ProductivityByPlotRepository repoProductivity;

	@Override
	public ProductivityByPlot totalProductionPerPlot(Long idPlot) {
		Integer productionPlot = repository.totalProductionByPlot(idPlot);
		ProductivityByPlot hshsh = new ProductivityByPlot(productionPlot);
		return hshsh;
	}
	

	@Override
	public ProductivityByPlot saveProductivityByPlot(Long idPlot) {
		ProductivityByPlot produ = new ProductivityByPlot();
		produ = totalProductionPerPlot(idPlot);
		return repoProductivity.save(produ);
	}
	
}
