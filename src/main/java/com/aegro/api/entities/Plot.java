package com.aegro.api.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Yohan Silva
 */

@Entity
public class Plot implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlot;
	
	@Column(nullable = true)
	private String namePlot;
	
	@Column(nullable = true)
	private Double plotProductivity;
	
	@Column(nullable = true)
	private Double plotAreaInHectare;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "farm_id")
	private Farm farm;

	@OneToMany(cascade=CascadeType.MERGE, mappedBy="plot", orphanRemoval=true)
	private List<Production> produtions = new ArrayList<>();

	public Plot() {
	}

	public Plot(String namePlot, Double plotAreaInHectare) {
		this.namePlot = namePlot;
		this.plotAreaInHectare = plotAreaInHectare;
	}

	public Long getIdPlot() {
		return idPlot;
	}

	public String getNamePlot() {
		return namePlot;
	}

	public void setNamePlot(String namePlot) {
		this.namePlot = namePlot;
	}

	public Double getPlotAreaInHectare() {
		return plotAreaInHectare;
	}

	public void setPlotAreaInHectare(Double plotAreaInHectare) {
		this.plotAreaInHectare = plotAreaInHectare;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public List<Production> getProdutions() {
		return produtions;
	}

	public Double getPlotProductivity() {
		return plotProductivity;
	}

	public void setPlotProductivity(Double productiviy) {
		this.plotProductivity = productiviy;
	}
	
}
