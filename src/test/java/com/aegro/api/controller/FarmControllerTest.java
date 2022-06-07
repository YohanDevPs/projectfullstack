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

import com.aegro.api.entities.Farm;
import com.aegro.api.service.FarmService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Yohan Silva
 */

@SpringBootTest
@AutoConfigureMockMvc
class FarmControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private FarmService farmService;
	
	@Test
	public void mustReturnOk_AndTestGetFarmMethod() throws Exception {
		
		Long idFarm = 1l;
		
		farmService.getFarmByIdWithYourPlots(idFarm);
		
		Mockito.verify(farmService, Mockito.times(1))
		.getFarmByIdWithYourPlots(idFarm);
		
		mockMvc.perform(get("/v1/farm/{id}", 1L))
		.andExpect(status().isOk());
	}

	@Test
	public void mustReturnOk_AndTestGetListMethod() throws Exception {
	
		farmService.farmList();
		
		Mockito.verify(farmService, Mockito.times(1))
		.farmList();
		
		mockMvc.perform(get("/v1/farm"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void mustReturnNoContent_AndTestRemoveMethod() throws Exception {
		
		Long idFarmToRemove =  1L;
			    		
		Farm farm = new Farm("Fazenda X");	
					
		farmService.removeFarmById(idFarmToRemove);
				
		Mockito.verify(farmService, Mockito.times(1))
					.removeFarmById(idFarmToRemove);
				
		mockMvc.perform(delete("/v1/farm/{id}",1L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(farm)))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void mustReturnIsCreated_AndTestSaveFarmMethod() throws Exception {
		
		Farm farm = new Farm("Fazenda do João");
		
		farmService.saveFarm(farm);
		
		Mockito.verify(farmService, Mockito.times(1))
			    .saveFarm(farm);
		
		mockMvc.perform(post("/v1/farm")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(farm)))
				.andExpect(status().isCreated());
	}
	
//	@Test
//	public void mustReturnNoContent_AndTestUpgradeProductivityMethod() throws Exception {
//		
//		Long idFarmToUpdate =  1L;
//		
//		farmService.updateProductivityFarm(idFarmToUpdate);
//		
//		Mockito.verify(farmService, Mockito.times(1))
//			.updateProductivityFarm(idFarmToUpdate);
//		
//		mockMvc.perform(put("/v1/farm/{id}/updateproductivity", 1L))
//			.andExpect(status().isNoContent());
//	}
	
	@Test
	public void mustReturnNoContent() throws Exception {
		
		Farm farm = new Farm("Fazenda do João");
		
		mockMvc.perform(put("/v1/farm/{id}",1L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(farm)))
				.andExpect(status().isNoContent());
	}	
}