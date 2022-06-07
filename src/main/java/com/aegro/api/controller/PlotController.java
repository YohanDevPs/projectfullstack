package com.aegro.api.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductivityFarm;
import com.aegro.api.service.ProductivityPlot;

/**
 * @author Yohan Silva
 */

@RestController
@RequestMapping("v1/plot")
public class PlotController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PlotService plotService;
	
	@Autowired
	private ProductivityPlot productivityPlot;
	
	@Autowired
	private ProductivityFarm productivityFarm;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/{idFarm}/farmid")
	@ResponseStatus(HttpStatus.CREATED)
	public Plot savePlotinFarm(@RequestBody Plot plot, @PathVariable("idFarm") Long idFarm) {
		return plotService.createPlotInFarmId(plot, idFarm);
	}
	

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Plot> plotList() {
		return plotService.plotList();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}/farmid")
	@ResponseStatus(HttpStatus.OK)
	public List<Plot> plotListOfFarmId(@PathVariable("id") Long id) {
		return plotService.plotListOfFarmId(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Plot> getPlotById(@PathVariable("id") Long id) {
		return  plotService.getPlotByIdWithYourProductions(id);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePlotById(@PathVariable("id") Long id) {
		plotService.getPlotByIdWithYourProductions(id).map(plot -> {
			plotService.removePlotById(plot.getIdPlot());
			return Void.TYPE;
		});
		
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePlot(@PathVariable("id") Long id, @RequestBody Plot plot) {
		
		productivityFarm.updateFarmProductivityWhenUpdatePlot(id, plot);
		productivityPlot.updateProductivityPlotWhenChangeArea(id, plot);
		
		plotService.getPlotByIdWithYourProductions(id).map(basePlot -> {
			modelMapper.map(plot, basePlot);
			plotService.savePlot(basePlot);
			return Void.TYPE;
		});
	}
}
