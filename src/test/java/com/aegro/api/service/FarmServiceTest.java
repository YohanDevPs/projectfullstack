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

import com.aegro.api.entities.Farm;
import com.aegro.api.entities.Plot;
import com.aegro.api.repository.FarmRepository;
import com.aegro.api.repository.PlotRepository;
import com.aegro.api.repository.ProductionRepository;
import com.aegro.api.service.Impl.FarmServiceImpl;
import com.aegro.api.service.Impl.ProductivityFarmImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class FarmServiceTest {

	@MockBean
	private FarmRepository farmRepository;

	@MockBean
	private ProductionRepository productionRepository;
	
	@MockBean
	private ProductivityFarmImpl productivity;

	@MockBean
	private PlotRepository plotRepository;

	@Autowired
	private FarmServiceImpl farmService;

	private List<Farm> listOfFarms() {

		List<Farm> farmList = new ArrayList<>();

		Farm farm1 = new Farm("Fazenda X");
		Farm farm2 = new Farm("Fazenda Y");

		farmList.add(farm1);
		farmList.add(farm2);

		return farmList;
	}

	private List<Plot> listOfPlots() {

		List<Plot> plotsList = new ArrayList<>();

		Plot plot1 = new Plot("Talhao Azul", 200.0);
		Plot plot2 = new Plot("Talhao Verde", 500.0);

		plotsList.add(plot1);
		
		
		Mockito.when(plotRepository.save(plot1)).thenReturn(plot1);

		return plotsList;
	}

	private Farm createFarmIfPlots() {

		Farm farm = Mockito.mock(Farm.class);
		
		List<Plot> plotsList = listOfPlots();

		Mockito.when(farm.getNameFarm()).thenReturn("Fazenda X");
		Mockito.when(farm.getId()).thenReturn(1L);
		Mockito.when(farm.getPlots()).thenReturn(plotsList);
		Mockito.when(farm.getFarmProductivity()).thenReturn(200.0);

		Mockito.when(farmService.saveFarm(farm)).thenReturn(farm);

		return farm;
	}

	@Test
	public void mustGetAFarmById() {
		
		Long idFarm = 1L;

		Optional<Farm> farm = Optional.of(createFarmIfPlots());

		Mockito.when(farmRepository.findById(ArgumentMatchers.eq(idFarm))).thenReturn(farm);

		farmService.getFarmByIdWithYourPlots(idFarm);

		Mockito.verify(farmRepository, Mockito.times(1)).findById(idFarm);
	}

	@Test
	public void mustSaveAFarm() {

		Farm farm = new Farm("Fazenda X");

		Mockito.when(farmRepository.save(ArgumentMatchers.eq(farm))).thenReturn(farm);

		farmService.saveFarm(farm);

		Mockito.verify(farmRepository, Mockito.times(1)).save(farm);
	}

	@Test
	public void mustRemoveFarmById() {
		Long idFarm = 1L;
		
		Optional<Farm> farm = Optional.of(createFarmIfPlots());
		
		Mockito.when(farmRepository.findById(ArgumentMatchers.eq(idFarm))).thenReturn(farm);
		
		farmService.removeFarmById(idFarm);
		
		Mockito.verify(farmRepository, Mockito.times(1)).deleteById(idFarm);
	}

	@Test
	public void mustGetAListOfFarms() {

		List<Farm> farmList = listOfFarms();
		
		Mockito.when(farmRepository.findAll()).thenReturn(farmList);
		
		farmService.farmList();
		
		Mockito.verify(farmRepository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void mustGetAreaOfFarm() {

		Long idFarm = 1L;

		double totalArea = 400;
		
		Optional<Farm> farm = Optional.of(createFarmIfPlots());

		Mockito.when(farm.get().getId()).thenReturn(idFarm);

		Mockito.when(plotRepository.totalAreaByFarmId(ArgumentMatchers.eq(idFarm))).thenReturn(totalArea);

		productivity.getTotalAreaByFarmId(idFarm);

		Mockito.verify(productivity, Mockito.times(1)).getTotalAreaByFarmId(idFarm);
	}
	

}
