package com.aegro.api.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hamcrest.collection.IsEmptyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.repository.FarmRepository;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductionService;
import com.aegro.api.service.ProductivityFarm;
import com.aegro.api.service.ProductivityPlot;

/**
 * @author Yohan Silva
 */

@Service
public class ProductionServiceImpl implements ProductionService {
	
	@Autowired
	private ProductionRepository productionRepository;
	
	@Autowired
	private PlotService plotService;
	
	@Autowired
	private PlotRepository plotRepository;
	
	@Autowired
	private ProductivityPlot productivityPlot;
	
	@Autowired
	private ProductivityFarm productivityFarm;
	
	@Autowired
	private FarmRepository farmRepository;
	
		
	@Override
	public Production saveProduction(Production production) {
		return productionRepository.save(production); 
	}

	@Override
	public List<Production> productionList() {
		return productionRepository.findAll();			
	}	
	
	@Override
	public Optional<Production> getProductionById(Long id){
		return Optional.ofNullable(productionRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found "+ id)));
	}
	
	@Override
	public void removeProductionById(Long id) {
		productionRepository.deleteById(id);	
	}
	
	@Override
	public Production createProductionInPlotId(Production production, Long idPlot) {
	
		Plot plot = plotRepository.getById(idPlot);
		production.setPlot(plot);
		
		/*
		 * Code for new productivity capture and update in Plot and Farm
		 */
		
		double productivity = productivityPlot.getProductivityByIdPlot(idPlot);
		plot.setPlotProductivity(productivity);
		plot.getProdutions().add(production);
		
		Farm farm = farmRepository.getById(idPlot);
		Long idFarm = farm.getId();
		double prodFarm = productivityFarm.getFarmProductivityById(idFarm);
		farm.setFarmProductivity(prodFarm);
		
		return productionRepository.save(production);	
	}
	
	@Override
	public List<Production> productionListByPlotId(Long idPlot) {
		
		List<Production> productions = productionRepository.findAll();
		productions.get(0);
		
		Production production = new Production();
		
		List<Production> newListProduction = new ArrayList<>();
		
		for(int i = 0; i < productions.size(); i++) {
			if (productions.get(i).getPlot().getIdPlot() == idPlot) {
				production = productions.get(i);
				newListProduction.add(production);
			}
		}
		
		return newListProduction;
	}
	
}
