package com.aegro.api.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hamcrest.collection.IsEmptyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
import com.aegro.api.service.ProductivityShared;

/**
 * @author Yohan Silva
 */

@Service
public class ProductionServiceImpl implements ProductionService {
	
	@Autowired
	private ProductionRepository productionRepository;

	@Autowired
	private PlotRepository plotRepository;
	
	@Autowired
	private ProductivityPlot productivityPlot;
	
	@Autowired
	private ProductivityFarm productivityFarm;
	
			
	@Override
	public Production saveProduction(Production production) {
		return productionRepository.save(production); 
	}
	
	@Override
	public Production updateProduction(Long id, Production production) {
		Production newProduction = productionRepository.getById(id);
		newProduction.setAmount(production.getAmount());
		return productionRepository.save(newProduction);		
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
		
		productivityFarm.updateFarmProductivityWhenDeleteProduction(id);
		productivityPlot.updatePlotProductivityWhenDeleteProduction(id);
		
		productionRepository.deleteById(id);	
	}
	
	@Override
	public Production createProductionInPlotId(Production production, Long idPlot) {
		
		Plot plot = plotRepository.getById(idPlot);	
		plot.getProdutions().add(production);
		production.setPlot(plot);
			
		productivityPlot.updatePlotProductivityWhenCreateProduction(production, idPlot);
		productivityFarm.updateFarmProductivityWhenCreateProduction(production, idPlot);
		
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
