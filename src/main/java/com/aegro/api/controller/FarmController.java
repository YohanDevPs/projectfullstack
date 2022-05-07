package com.aegro.api.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.service.FarmService;

/**
 * @author Yohan Silva
 */

@RestController
@RequestMapping("v1/farm")
public class FarmController {

	
	@Autowired
	private FarmService farmService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Farm saveFarm(@RequestBody Farm farm) {
		return farmService.saveFarm(farm);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Farm> farmList() {
		return farmService.farmList();
	}

	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Optional<Farm>> getProductionById(@PathVariable("id") Long id) {
			Optional<Farm> farm= farmService.getFarmById(id);
			return ResponseEntity.ok().body(farm);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeFarmById(@PathVariable("id") Long id) {
		farmService.getFarmById(id).map(farm -> {
			farmService.removeFarmById(farm.getId());
			return Void.TYPE;
		});
	}
	
	@PutMapping("/{id}/updateproductivity")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProductivity(@PathVariable Long id) {	
		farmService.updateProductivityFarm(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateFarm(@PathVariable("id")Long id, @RequestBody Farm farm) {
		farmService.getFarmById(id)
		.map(baseFarm -> {
			modelMapper.map(farm, baseFarm);
			farmService.saveFarm(baseFarm);
			return Void.TYPE;
		});
	}
}
