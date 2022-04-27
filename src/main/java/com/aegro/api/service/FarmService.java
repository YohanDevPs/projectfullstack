package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import com.aegro.api.entities.Farm;

public interface FarmService {
	
	public Farm saveFarm(Farm farm);
	
	public List<Farm> farmList();
	
	public Optional<Farm> getFarmById(Long id);
	
	public void removeFarmById(Long id);
	
}
