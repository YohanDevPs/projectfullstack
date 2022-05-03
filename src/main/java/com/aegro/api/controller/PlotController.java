package com.aegro.api.controller;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aegro.api.entities.Plot;
import com.aegro.api.service.PlotService;

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

	@PostMapping("/{idFarm}/farm")
	@ResponseStatus(HttpStatus.CREATED)
	public Plot savePlotinFarm(@RequestBody Plot plot,@PathVariable("idFarm")  Long idFarm) {
		return plotService.createPlotInFarmId(plot, idFarm);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Plot> plotList() {
		return plotService.plotList();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Plot getPlotById(@PathVariable("id") Long id) {
		return plotService.getPlotByIdAndProductions(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhao nao encontrado."));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePlotById(@PathVariable("id") Long id) {
		plotService.getPlotByIdAndProductions(id).map(plot -> {
			plotService.removePlotById(plot.getIdPlot());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhao nao encontrado."));
	}
	
	@PutMapping("/{id}/updateproductivity")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProductivity(@PathVariable Long id) {	
		plotService.updateProductivityByPlotId(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePlot(@PathVariable("id") Long id,@RequestBody Plot plot) {
		plotService.getPlotByIdAndProductions(id)
		.map(basePlot -> {
			modelMapper.map(plot, basePlot);
			plotService.savePlot(basePlot);
			return Void.TYPE;	
		}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhao nao encontrada."));
	}
}
