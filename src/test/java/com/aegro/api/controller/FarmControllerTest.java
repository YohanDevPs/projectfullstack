package com.aegro.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.aegro.api.entities.Farm;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductionService;

import io.restassured.http.ContentType;

@WebMvcTest
class FarmControllerTest {


	@MockBean
	private FarmService farmService;
	
	@Autowired
	private FarmController farmController;
	
	@MockBean
	private PlotService plotService;
	
	@MockBean
	private ProductionService productionServices;
	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.farmController);
	}
	
	@Test
	public void mustReturnOk_WhenGetAFarmById() {
		Optional<Farm> farm = Optional.ofNullable(new Farm("Fazenda X"));
		
		when(this.farmService.getFarmById(1L)).thenReturn(farm);
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("v1/farm/{id}", 1L)
		.then()
		.statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void mustReturnNotFound_WhenGetAFarmById() {
		Optional<Farm> farm = Optional.ofNullable(new Farm("Fazenda X"));
		
		when(this.farmService.getFarmById(1L)).thenReturn(farm);
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("v1/farm/{id}", 5L)
		.then()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}
		
}