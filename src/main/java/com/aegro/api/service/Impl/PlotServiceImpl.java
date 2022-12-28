package com.aegro.api.service.Impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.repository.FarmRepository;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductivityFarm;

/**
 * @author Yohan Silva
 */

@Service
public class PlotServiceImpl implements PlotService{

	@Autowired
	private PlotRepository plotRepository;
	@Autowired
	private FarmRepository farmRepository;
	@Autowired
	private ProductivityFarm productivityFarm;

	@Override
	public Plot savePlot(Plot plot) {
		return plotRepository.save(plot); 
	}

	@Override
	public List<Plot> plotList() {
		return plotRepository.findAll();
	}
	
	@Override
	public Optional<Plot> getPlotByIdWithYourProductions(Long id){
		return Optional.ofNullable(plotRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found "+ id)));
	}
	
	@Override
	public void removePlotById(Long id) {	
		plotRepository.getById(id);
		Farm farm = plotRepository.getById(id).getFarm();
		plotRepository.deleteById(id);
		productivityFarm.updateFarmProductivity(farm);
	}
	
	@Override
	public Plot createPlotInFarmId(Plot plot, Long idFarm) {
		Farm farm = farmRepository.getById(idFarm);
		plot.setFarm(farm);
		farm.getPlots().add(plot);
		plotRepository.save(plot);
		productivityFarm.updateFarmProductivity(farm);
	
		return plot;
	}
	

	@Override
	public List<Plot> plotListOfFarmId(Long idFarm) {
		List<Plot> plots = plotRepository.findAll();
		Plot plot = new Plot();
		List<Plot> newListPlot = new ArrayList<>();

			for(int i = 0; i < plots.size(); i++) {
				if (plots.get(i).getFarm().getId() == idFarm) {
					plot = plots.get(i);
					newListPlot.add(plot);
				}
			}
			return newListPlot;
		}
}
