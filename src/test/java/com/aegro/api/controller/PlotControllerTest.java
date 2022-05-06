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

import com.aegro.api.entities.Plot;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.PlotService;
import com.aegro.api.service.ProductionService;

import io.restassured.http.ContentType;

@WebMvcTest
class PlotControllerTest {

	@Autowired
	private PlotController plotController;

	@MockBean
	private FarmService farmService;

	@MockBean
	private ProductionService productionServices;

	@MockBean
	private PlotService plotService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.plotController);
	}

	@Test
	public void mustReturnOk_WhenGetAPlotById() {
		Optional<Plot> plot = Optional.ofNullable(new Plot("Talhao Vermelho", 400.0));

		when(this.plotService.getPlotByIdAndProductions(1L)).thenReturn(plot);

		given()
		.accept(ContentType.JSON)
		.when()
		.get("v1/plot/{id}", 1L)
		.then()
		.statusCode(HttpStatus.SC_OK);
	}

	@Test
	public void mustReturnNotFound_WhenGetAPlotById() {
		Optional<Plot> plot = Optional.ofNullable(new Plot("Talhao Vermelho", 400.0));

		when(this.plotService.getPlotByIdAndProductions(1L)).thenReturn(plot);

		given()
		.accept(ContentType.JSON)
		.when()
		.get("v1/plot/{id}", 5L)
		.then()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}
}

