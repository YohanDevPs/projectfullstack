package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Production;

/**
 * @author Yohan Silva
 */

public interface ProductionService {

	List<Production> productionList();
	
	Optional<Production> getProductionById(Long id);
	
	void removeProductionById(Long id);

	Production createProductionInPlotId(Production production, Long idPlot);

	List<Production> productionListByPlotId(Long idPlot);

	Production saveProduction(Production production);
	
	Production updateProduction(Long id, Production production);

}

