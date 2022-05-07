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

import com.aegro.api.entities.Production;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductionService;

import io.restassured.http.ContentType;

@WebMvcTest
class ProductionControllerTest {

	@Autowired
	private ProductionController productionController;
	
	@MockBean
	private PlotService plotService;
	
	@MockBean
	private ProductionService productionServices;
	
	@MockBean
	private FarmService farmService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.productionController);
	}
	
	@Test
	public void mustReturnOk_WhenGetAProductionById() {
		Optional<Production> Production = Optional.ofNullable(new Production(500.0));
		
		when(this.productionServices.getProductionById(1L)).thenReturn(Production);
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("v1/production/{id}", 1L)
		.then()
		.statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void mustReturnNotFound_WhenGetAProductionById() {
		Optional<Production> Production = Optional.ofNullable(new Production(500.0));
		
		when(this.productionServices.getProductionById(1L)).thenReturn(Production);
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("v1/production/{id}", 90L)
		.then()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}
}
