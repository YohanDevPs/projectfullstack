package com.aegro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aegro.api.service.ProductivityByPlotService;

@Controller
@RequestMapping("v1/productivity")
public class ProductivityByPlot {

	@Autowired
	private ProductivityByPlotService produ;


	@GetMapping("/{idPlot}")
	@ResponseStatus(HttpStatus.OK)
	public ProductivityByPlot getProductionById(@PathVariable("idPlot")Long idPlot) {
		return produ.saveProductivityByPlot(idPlot);
	}
	
	@PostMapping(value = "/{idPlot}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductivityByPlot addProductivityByPlot(@PathVariable("idPlot") Long idPlot){
		return produ.saveProductivityByPlot(idPlot);
		
		
	}
}
