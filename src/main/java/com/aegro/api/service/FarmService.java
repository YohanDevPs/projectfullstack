package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Farm;

public interface FarmService {
	
	Farm saveFarm(Farm farm) ;

	List<Farm> farmList();
	
	Optional<Farm> getFarmById(Long id);
	
	void removeFarmById(Long id);
	
}
