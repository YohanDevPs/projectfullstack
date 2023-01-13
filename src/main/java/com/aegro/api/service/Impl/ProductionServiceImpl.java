package com.aegro.api.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
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
		productionRepository.save(newProduction);	
		
		Plot plot = newProduction.getPlot();
		Farm farm = plot.getFarm();
		
		productivityFarm.updateFarmProductivity(farm);
		productivityPlot.updatePlotProductivity(plot);
		
		return newProduction;
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
		Production production = productionRepository.getById(id);
		Plot plot = production.getPlot();
		Farm farm = plot.getFarm();
		
		productionRepository.deleteById(id);	
		
		productivityFarm.updateFarmProductivity(farm);
		productivityPlot.updatePlotProductivity(plot);
	}
	
	@Override
	public Production createProductionInPlotId(Production production, Long idPlot) {
		Plot plot = plotRepository.getById(idPlot);	
		plot.getProdutions().add(production);
		production.setPlot(plot);

		Farm farm = plot.getFarm();

		productionRepository.save(production);	
		
		productivityFarm.updateFarmProductivity(farm);
		productivityPlot.updatePlotProductivity(plot);
		return production;
	}

	
	@Override
	public List<Production> productionListByPlotId(Long idPlot) {
		List<Production> productions = productionRepository.findAll();
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
