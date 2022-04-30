package com.aegro.api.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.ProductionService;

@Service
public class ProductionServiceImpl implements ProductionService {

	@Autowired
	private ProductionRepository productionRepository;

	@Autowired
	public ProductionServiceImpl(ProductionRepository repository) {
		this.productionRepository = repository;
	}

	@Override
	public Production saveProduction(Production amount) {
		return productionRepository.save(amount);
	}

	@Override
	public List<Production> productionList() {
		return productionRepository.findAll();
	}

	@Override
	public Optional<Production> getProductionById(Long id) {
		return productionRepository.findById(id);
	}

	@Override
	public void removeProductionById(Long id) {
		productionRepository.deleteById(id);
	}

	@Override
	public Production productionByIdPlot(Long id) {		
		Integer productivity = productionRepository.totalProductionByPlot(id);
		Production productivityPlot = new Production(productivity);
		return productivityPlot;
	}

}

