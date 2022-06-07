package com.aegro.api.service;

import com.aegro.api.entities.Plot;
import com.aegro.api.entities.Production;

/**
 * @author Yohan Silva
 */
public interface ProductivityPlot {

	Double getProductionByPlotId(Long idPlot);

	void updateProductivityPlotWhenChangeArea(Long idPlot, Plot newPlot);

	Double limitDecimalPlace(double numberToFormat);

	void updateProductivityPlot(Plot plot);

	void updatePlotProductivityWhenCreateProduction(Production production, Long idPlot);

	void updatePlotProductivityWhenDeleteProduction(Long idProduction);

}
