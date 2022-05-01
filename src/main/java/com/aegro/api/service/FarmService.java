package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Farm;

/**
 * @author Yohan Silva
 */

public interface FarmService {
	
	Farm saveFarm(Farm farm);
	
	List<Farm> farmList();
	
	Optional<Farm> getFarmById(Long id);
	
	void removeFarmById(Long id);

	Double getTotalAreaByFarmId(Long idFarm);

	Double getProductionFarmById(Long idFarm);

	Double getFarmProductivityById(Long idFarm);

	void updateProductivityFarm(Long idFarm);
		
}
