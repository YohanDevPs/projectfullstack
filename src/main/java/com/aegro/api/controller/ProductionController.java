package com.aegro.api.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.ProductionService;
import com.aegro.api.service.ProductivityPlot;
import com.aegro.api.service.Impl.ProductivityPlotImpl;

/**
 * @author Yohan Silva
 */

@RestController
@RequestMapping("v1/production")
public class ProductionController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductionRepository productionRepository;

	@Autowired
	private ProductionService productionService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/{idPlot}/plot")
	@ResponseStatus(HttpStatus.CREATED)
	public Production saveProductionInPlot(@RequestBody Production production, @PathVariable("idPlot") Long idPlot) {
		return productionService.createProductionInPlotId(production, idPlot);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	private List<Production> listProduction() {
		return productionService.productionList();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{idPlot}/idplot")
	@ResponseStatus(HttpStatus.OK)
	public List<Production> plotListOfFarmId(@PathVariable("idPlot") Long idPlot) {
		return productionService.productionListByPlotId(idPlot);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Production> getProductionById(@PathVariable("id") Long id) {
		return productionService.getProductionById(id);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeProductionById(@PathVariable("id") Long id) {
		productionService.getProductionById(id).map(production -> {
			productionService.removeProductionById(production.getIdProduction());
			return Void.TYPE;
		});
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public ResponseEntity<Production> updateProduction(@PathVariable("id") Long id, @RequestBody Production production) {
		Production oldProduction = productionRepository.getById(id);
		
		if (oldProduction == null) {
			return new ResponseEntity<Production>(HttpStatus.NOT_FOUND);
		}
	
		productionService.updateProduction(id, production);
		return new ResponseEntity<Production>(HttpStatus.OK);
	}
}
