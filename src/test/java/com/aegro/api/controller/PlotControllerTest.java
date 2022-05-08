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

import com.aegro.api.entities.Plot;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductionService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Yohan Silva
 */

@SpringBootTest
@AutoConfigureMockMvc
class PlotControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private FarmService farmService;
	
	@MockBean
	private ProductionService productionService;
	
	@MockBean
	private PlotService plotService;

	@Test
	public void mustReturnOk_AndTestCreatePlotMethod() throws Exception {
		
		Plot plot = new Plot("Talhao Azul", 200.0);
		
		Long idFarm = 1L;
		
		plotService.createPlotInFarmId(plot, idFarm);
		
		Mockito.verify(plotService, Mockito.times(1)).createPlotInFarmId(plot, idFarm);
		
		mockMvc.perform(post("/v1/plot/{idFarm}/farm", 1L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(plot)))
		.andExpect(status().isCreated());
	}

	@Test
	public void mustReturnOk_AndTestPlotListMethod() throws Exception {
		
		plotService.plotList();
		
		Mockito.verify(plotService, Mockito.times(1))
		.plotList();

		mockMvc.perform(get("/v1/plot"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void mustReturnOk_AndTestGetAPlotByIdMethod() throws Exception {
		
		Long idPlot = 1L;
		
		plotService.getPlotByIdWithYourProductions(idPlot);
		
		Mockito.verify(plotService, Mockito.times(1))
		.getPlotByIdWithYourProductions(idPlot);
		
		mockMvc.perform(get("/v1/plot/{id}", 1L))
		.andExpect(status().isOk());
	}
	
	
	
	@Test
	public void mustReturnEmpty_AndTestRemoveAPlotByIdMethod() throws Exception {
		
		Long idPlotToRemove =  1L;
					
		plotService.removePlotById(idPlotToRemove);
				
		Mockito.verify(plotService, Mockito.times(1))
					.removePlotById(idPlotToRemove);
				
		mockMvc.perform(delete("/v1/plot/{id}",1L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(idPlotToRemove)))
				.andExpect(status().isNoContent());
	}

	@Test
	public void mustReturnNoContent_AndTestUpdateProductivityOfPlot() throws Exception {
		
		Long idPlotToUpdate =  1L;
		
		plotService.updateProductivityByPlotId(idPlotToUpdate);
		
		Mockito.verify(plotService, Mockito.times(1))
			.updateProductivityByPlotId(idPlotToUpdate);
		
		mockMvc.perform(put("/v1/plot/{id}/updateproductivity", 1L))
			.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void mustReturnNoContent() throws Exception {
		
		Plot plot = new Plot("Fazenda do João", 300.0);
		
		mockMvc.perform(put("/v1/farm/{id}",1L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(plot)))
				.andExpect(status().isNoContent());
		
	}
}