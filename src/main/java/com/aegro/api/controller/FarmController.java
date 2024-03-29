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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Farm saveFarm(@RequestBody Farm farm) {
		return farmService.saveFarm(farm);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Farm> farmList() {
		return farmService.farmList();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Farm> getFarmById(@PathVariable("id") Long id) {
			return farmService.getFarmByIdWithYourPlots(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeFarmById(@PathVariable("id") Long id) {
		farmService.getFarmByIdWithYourPlots(id).map(farm -> {
			farmService.removeFarmById(farm.getId());
			return Void.TYPE;
		});
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateFarm(@PathVariable("id")Long id, @RequestBody Farm farm) {
		farmService.getFarmByIdWithYourPlots(id)
		.map(baseFarm -> {
			modelMapper.map(farm, baseFarm);
			farmService.saveFarm(baseFarm);
			return Void.TYPE;
		});
	}
}
