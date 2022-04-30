package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Production;


public interface ProductionService {

	Production saveProduction(Production amount);

	List<Production> productionList();
	
	Optional<Production> getProductionById(Long id);
	
	void removeProductionById(Long id);

	Production productionByIdPlot(Long id);

}

