package com.aegro.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.aegro.api.entities.Production;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductionService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Yohan Silva
 */

@SpringBootTest
@AutoConfigureMockMvc
class ProductionControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PlotService plotService;
	
	@MockBean
	private FarmService farmService;
	
	@MockBean
	private ProductionService productionService;

	@Test
	public void mustReturnIsCreated_AndCreatedMethod() throws Exception {
		
		Production production = new Production(400.0);
		Long idPlot = 1L;
		
		productionService.createProductionInPlotId(production, idPlot);
		
		Mockito.verify(productionService, Mockito.times(1))
		.createProductionInPlotId(production, idPlot);
		
		mockMvc.perform(post("/v1/production/{id}/plot", 1L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(production)))
		.andExpect(status().isCreated());
	}

	@Test
	public void mustReturnOk_AndTestGetListMethod() throws Exception {
	
		productionService.productionList();
		
		Mockito.verify(productionService, Mockito.times(1))
		.productionList();
		
		mockMvc.perform(get("/v1/production"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void mustReturnOk_AndTestGetProductionById() throws Exception {
		
		Long idProduction = 1l;
		
		productionService.getProductionById(idProduction);
		
		Mockito.verify(productionService, Mockito.times(1))
		.getProductionById(idProduction);
		
		mockMvc.perform(get("/v1/production/{id}", 1L))
		.andExpect(status().isOk());
	}
	
	@Test
	public void mustReturnEmpty_AndTestRemoveAFarmById() throws Exception {
		
		Long idProduction =  1L;
						
		productionService.removeProductionById(idProduction);
				
		Mockito.verify(productionService, Mockito.times(1))
					.removeProductionById(idProduction);
				
		mockMvc.perform(delete("/v1/production/{id}",1L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(idProduction)))
				.andExpect(status().isNoContent());
	}
	
	
	@Test
	public void mustReturnNoContent() throws Exception {
		
		Production production = new Production(400.0);;
		
		mockMvc.perform(put("/v1/production/{id}",1L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(production)))
				.andExpect(status().isNoContent());
	}
}
