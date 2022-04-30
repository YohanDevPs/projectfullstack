package com.aegro.api.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aegro.api.entities.Production;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProductionRepositoryTest {

	public class FarmTest {

//		@Autowired
//		private ProductionRepository productionRepository;
//
////	@Test
////	public void shouldLoadTheProductionById() {
////		Long farmTest = (long) 1;
////		Optional<Farm> farm = farmRepository.findById(farmTest);
////
////		Assert.assertNotNull(farm);
////		Assert.assertEquals(farmTest, farm.get().getId());
////	}
//
////		@Test
////		public void shouldLoadTheProductionById() {
////			Long productionPlot = 1l;
////			Integer prodcutionByPlotId1 = 1075;
////			Production production = productionRepository.totalProductionByPlot(productionPlot);
////
////			Assert.assertNotNull(productionPlot);
////			Assert.assertEquals(prodcutionByPlotId1, production);
////		}
//
//	}
}
}