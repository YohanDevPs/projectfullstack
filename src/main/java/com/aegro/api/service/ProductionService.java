package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.entities.ProductivityByPlot;


public interface ProductionService {

	Production saveProduction(Production amount);

	List<Production> productionList();
	
	Optional<Production> getProductionById(Long id);
	
	void removeProductionById(Long id);
	
	Integer productionByFarm(Long idFarm);

	List<Plot> allPlotsByFarmId(Long idFarm);
	
}

