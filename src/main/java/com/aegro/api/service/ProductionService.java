package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Production;


public interface ProductionService {

	public Production saveProduction(Production production);

	public List<Production> productionList();
	
	public Optional<Production> getProductionById(Long id);
	
	public void removeProductionById(Long id);
	
	public Integer totalProductionPerPlot (Long idPlot);
}

