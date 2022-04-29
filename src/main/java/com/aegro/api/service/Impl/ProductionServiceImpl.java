package com.aegro.api.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.entities.ProductivityByPlot;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductionService;

@Service
public class ProductionServiceImpl implements ProductionService {

	@Autowired
	private ProductionRepository productionRepository;

	@Autowired
	private PlotRepository plotRepository;

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
	public List<Plot> allPlotsByFarmId(Long idFarm) {
		return plotRepository.getAllPlotsByFarmId(idFarm);
	}

	@Override
	public Integer productionByFarm(Long idFarm) {
		return null;
	}

}

//		for (Plot plot : plots) {
//			sum += productionRepository.totalProductionByPlot(idFarm);	
//		}

//	public Integer productionByFarm(Long idFarm) {
//		Collection<Plot> plots = plotService.allPlotsByFarmId(idFarm);
//		
//		int sum = 0;
//		
//		plots.stream().map(c -> c.getProdutions());
//		
//		for(int i = 0; i > (plots.size()-1);i++) {
//			sum += productionRepository.totalProductionByPlot(idFarm);	
//		}
//
//		return sum;
//	}