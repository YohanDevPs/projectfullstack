package com.aegro.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.Impl.ProductionServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class ProductionServiceTest {

	@MockBean
	private ProductionRepository productionRepository;

	@MockBean
	private PlotService plotService;

	@MockBean
	private PlotRepository plotRepository;

	@Autowired
	private ProductionServiceImpl productionService;

	private Production createAMockOfProduction() {

		Production production = Mockito.mock(Production.class);

		Long idProduction = 1L;
		double amount = 200.0;

		Mockito.when(production.getIdProduction()).thenReturn(idProduction);
		Mockito.when(production.getAmount()).thenReturn(amount);

		Mockito.when(productionService.saveProduction(production)).thenReturn(production);

		return production;
	}

	private List<Production> listOfProductions() {

		List<Production> productionsList = new ArrayList<>();

		Production production1 = new Production(200.0);
		Production production2 = new Production(500.0);

		productionsList.add(production1);
		productionsList.add(production2);

		return productionsList;
	}

	@Test
	public void mustSaveAProduction() {

		Production production = new Production(300.0);

		Mockito.when(productionRepository.save(ArgumentMatchers.eq(production))).thenReturn(production);

		productionService.saveProduction(production);
		
		Mockito.verify(productionRepository, Mockito.times(1)).save(production);
	}
	
	@Test
	public void mustGetAProductionById() {

		Long idProduction = 1L;

		Optional<Production> Production = Optional.of(createAMockOfProduction());

		Mockito.when(productionRepository.findById(ArgumentMatchers.eq(idProduction))).thenReturn(Production);

		productionService.getProductionById(idProduction);

		Mockito.verify(productionRepository, Mockito.times(1)).findById(idProduction);
	}
	
	@Test
	public void mustRemovePlotById() {
		
		Long idProduction = 1L;

		Optional<Production> Production = Optional.of(createAMockOfProduction());
		
		Mockito.when(productionRepository.findById(idProduction)).thenReturn(Production);
		
		productionService.removeProductionById(idProduction);
		
		Mockito.verify(productionRepository, Mockito.times(1)).deleteById(idProduction);
	}
	
	@Test
	public void mustGetAListOfPlots() {
		
		List<Production> listProductions = listOfProductions();
		
		Mockito.when(productionRepository.findAll()).thenReturn(listProductions);
		
		productionService.productionList();
		
		Mockito.verify(productionRepository, Mockito.times(1)).findAll();
	}

}
