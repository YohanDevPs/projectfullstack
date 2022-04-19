package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.repository.FarmRepository;

@Service
public class FarmService {

	@Autowired
	private FarmRepository farmRepository;
	
	public Farm saveFarm(Farm farm) {
		return farmRepository.save(farm); 
	}

	public List<Farm> farmList() {
		return farmRepository.findAll();
	}
	
	public Optional<Farm> getFarmById(Long id){
		return farmRepository.findById(id);
	}
	
	public void removeFarmById(Long id) {
		farmRepository.deleteById(id);	
	}
}
