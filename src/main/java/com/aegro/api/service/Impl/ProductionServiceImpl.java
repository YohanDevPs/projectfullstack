package com.aegro.api.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hamcrest.collection.IsEmptyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductionService;

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
		
		plot.getProdutions().add(production);
		
		plotService.savePlot(plot);
		
		return productionRepository.save(production);	
	}
	
}
