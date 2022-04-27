package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Production;


public interface ProductionService {

	Production saveProduction(Production production);

	List<Production> productionList();
	
	Optional<Production> getProductionById(Long id);
	
	void removeProductionById(Long id);
	
	Integer totalProductionPerPlot (Long idPlot);
}

