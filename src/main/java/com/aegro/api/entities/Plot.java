package com.aegro.api.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Plot implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlot;
	
	@Column(nullable = false)
	private String namePlot;
	
	@Column(nullable = false)
	private Short plotAreaInHectare;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "farm_id")
	private Farm farm;
	
	@OneToMany(mappedBy = "plot")
	private List<Production> produtions = new ArrayList<>();

	public Plot() {
	}

	public Plot(String namePlot, Short plotAreaInHectare) {
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

	public Short getPlotAreaInHectare() {
		return plotAreaInHectare;
	}

	public void setPlotAreaInHectare(Short plotAreaInHectare) {
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

	public void setProdutions(List<Production> produtions) {
		this.produtions = produtions;
	}

}
