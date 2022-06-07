package com.aegro.api.service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;

/**
 * @author Yohan Silva
 */

public interface ProductivityFarm {
	
	void updateFarmProductivityWhenUpdatePlot(Long idPlot, Plot newPlot);

	void updateFarmProductivity(Farm farm);

}
