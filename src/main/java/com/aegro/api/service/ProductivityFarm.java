package com.aegro.api.service;

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;

/**
 * @author Yohan Silva
 */

public interface ProductivityFarm {
	
	Double getTotalAreaByFarmId(Long idFarm);
	
	Double getProductionFarmById(Long idFarm);

	void updateFarmProductivityWhenUpdatePlot(Long idPlot, Plot newPlot);

	void updateProductivityFarmWhenCreatePlot(Plot plot, Long idFarm);

	void updateFarmProductivityWhenDeletePlot(Long idFarm);

	void updateFarmProductivityWhenCreateProduction(Production production, Long idPlot);

	void updateFarmProductivityWhenDeleteProduction(Long idProduction);
}
