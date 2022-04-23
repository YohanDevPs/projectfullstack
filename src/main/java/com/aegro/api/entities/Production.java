package com.aegro.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Production implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduction;
	
	@Column(nullable = false)
	private short production;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "plot_id")
	private Plot plot;
	
	public Production() {
	}
	
	public Production(Long idProduction, short production, Plot plot) {
		this.idProduction = idProduction;
		this.production = production;
		this.plot = plot;
	}

	public Long getIdProduction() {
		return idProduction;
	}

	public void setIdProduction(Long idProduction) {
		this.idProduction = idProduction;
	}

	public short getProduction() {
		return production;
	}

	public void setProduction(short production) {
		this.production = production;
	}

	public Plot getPlot() {
		return plot;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}

	@Override
	public String toString() {
		return "Production [idProduction=" + idProduction + ", production=" + production + ", plot=" + plot + "]";
	}

}
