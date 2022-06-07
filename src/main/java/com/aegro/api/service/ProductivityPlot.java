package com.aegro.api.service;

import com.aegro.api.entities.Plot;

/**
 * @author Yohan Silva
 */
public interface ProductivityPlot {

	void updateProductivityPlotWhenChangeArea(Long idPlot, Plot newPlot);

	void updatePlotProductivity(Plot plot);

}
