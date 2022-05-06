package com.aegro.api.service;

/**
 * @author Yohan Silva
 */
public interface ProductivityPlot {

	Double getProductivityByIdPlot(Long idPlot);

	void updateProductivityByPlotId(Long idPlot);
	
	Double calculatePlotProductivity(Long idPlot);
	
}
