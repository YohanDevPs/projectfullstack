package com.aegro.api.service;

/**
 * @author Yohan Silva
 */

public interface ProductivityFarm {
	
	Double getTotalAreaByFarmId(Long idFarm);
	
	Double getProductionFarmById(Long idFarm);
	
	Double getFarmProductivityById(Long idFarm);
	
}
