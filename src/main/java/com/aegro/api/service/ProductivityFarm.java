package com.aegro.api.service;

/**
 * @author Yohan Silva
 */

public interface ProductivityFarm {
	
	void updateProductivityFarm(Long idFarm);
	
	Double getTotalAreaByFarmId(Long idFarm);
	
	Double getProductionFarmById(Long idFarm);
	
	Double getFarmProductivityById(Long idFarm);
	
}
