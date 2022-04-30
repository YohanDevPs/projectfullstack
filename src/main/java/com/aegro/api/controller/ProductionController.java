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

import com.aegro.api.entities.Production;
import com.aegro.api.service.ProductionService;

@RestController
@RequestMapping("v1/production")
public class ProductionController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductionService productionService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Production saveProduction(Production production) {
		return productionService.saveProduction(production);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	private List<Production> listProduction(){
		return productionService.productionList();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Production getProductionById(@PathVariable("id") Long id) {
		return productionService.getProductionById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producao nao encontrada."));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeProductionById(@PathVariable("id") Long id) {
		productionService.getProductionById(id).map(production -> {
			productionService.removeProductionById(production.getIdProduction());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producao nao encontrada."));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduction(@PathVariable("id") Long id,@RequestBody Production amount) {
		productionService.getProductionById(id).map(baseProduction ->{
			modelMapper.map(amount, baseProduction);
			productionService.saveProduction(baseProduction);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producao nao encontrada."));
	}
}
