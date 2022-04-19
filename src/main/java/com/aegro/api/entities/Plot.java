package com.aegro.api.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;


@Entity
public class Plot implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPlot;

	@Column(nullable = false)
	private String namePlot;
	
	@Column(nullable = false)
	private Short plotAreaInHectare;

	public Plot() {
	}
	
	public Plot(Long idPlot, String namePlot, Short plotAreaInHectare) {
		this.idPlot = idPlot;
		this.namePlot = namePlot;
		this.plotAreaInHectare = plotAreaInHectare;
	}

	public Long getIdPlot() {
		return idPlot;
	}

	public void setIdPlot(Long idPlot) {
		this.idPlot = idPlot;
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
	
}
