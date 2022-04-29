package com.aegro.api.service;

import com.aegro.api.entities.ProductivityByPlot;

public interface ProductivityByPlotService {
	
	ProductivityByPlot totalProductionPerPlot (Long idPlot);

	ProductivityByPlot saveProductivityByPlot(Long idPlot);

}
