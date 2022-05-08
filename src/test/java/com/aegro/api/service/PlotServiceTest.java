package com.aegro.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.aegro.api.entities.Plot;
import com.aegro.api.repository.FarmRepository;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.Impl.PlotServiceImpl;

/**
 * @author Yohan Silva
 */

@SpringBootTest
@AutoConfigureMockMvc
class PlotServiceTest {

	@MockBean
	private PlotRepository plotRepository;

	@MockBean
	private FarmService farmService;

	@MockBean
	private FarmRepository farmRepository;

	@MockBean
	private ProductionRepository productionRepository;

	@Autowired
	private PlotServiceImpl plotService;

	private Plot createAMockOfPlot() {
		
		Plot plot = Mockito.mock(Plot.class);
		
		Mockito.when(plot.getNamePlot()).thenReturn("Talhao Vermelho");
		Mockito.when(plot.getIdPlot()).thenReturn(1L);
		Mockito.when(plot.getPlotAreaInHectare()).thenReturn(500.0);
		
		Mockito.when(plotService.savePlot(plot)).thenReturn(plot);
		
		return plot;
	}

	private List<Plot> listOfPlots() {

		List<Plot> plotsList = new ArrayList<>();

		Plot plot1 = new Plot("Talhao Azul", 200.0);
		Plot plot2 = new Plot("Talhao Verde", 500.0);

		plotsList.add(plot1);
		plotsList.add(plot2);

		return plotsList;
	}

	@Test
	public void mustSaveAPlot() {

		Plot plot = new Plot("Talhao Vermelho", 500.00);

		Mockito.when(plotRepository.save(ArgumentMatchers.eq(plot))).thenReturn(plot);

		plotService.savePlot(plot);

		Mockito.verify(plotRepository, Mockito.times(1)).save(plot);
	}

	@Test
	public void mustGetAPlotById() {

		Long idPlot = 1L;

		Optional<Plot> plot = Optional.of(createAMockOfPlot());

		Mockito.when(plotRepository.findById(ArgumentMatchers.eq(idPlot))).thenReturn(plot);

		plotService.getPlotByIdWithYourProductions(idPlot);

		Mockito.verify(plotRepository, Mockito.times(1)).findById(idPlot);
	}

	@Test
	public void mustRemovePlotById() {
		
		Long idPlot = 1L;

		Optional<Plot> plot = Optional.of(createAMockOfPlot());
		
		Mockito.when(plotRepository.findById(idPlot)).thenReturn(plot);
		
		plotService.removePlotById(idPlot);
		
		Mockito.verify(plotRepository, Mockito.times(1)).deleteById(idPlot);

	}
	
	@Test
	public void mustGetAListOfPlots() {
		
		List<Plot> listPlots = listOfPlots();
		
		Mockito.when(plotRepository.findAll()).thenReturn(listPlots);
		
		plotService.plotList();
		
		Mockito.verify(plotRepository, Mockito.times(1)).findAll();
	}

}
