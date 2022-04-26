package com.aegro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Production;
import com.aegro.api.repository.ProductionRepository;

@Service
public class ProductionService {
	
	@Autowired
	private ProductionRepository productionRepository;
	
	public Production saveProduction(Production production) {
		return productionRepository.save(production); 
	}

	public List<Production> productionList() {
		return productionRepository.findAll();
	}
	
	public Optional<Production> getProductionById(Long id){
		return productionRepository.findById(id);
	}
	
	public void removeProductionById(Long id) {
		productionRepository.deleteById(id);	
	}
	
	public Short totalProductionPerPlot (Long idPlot) {
		return productionRepository.totalProductionByPlot(idPlot);
	}
}
