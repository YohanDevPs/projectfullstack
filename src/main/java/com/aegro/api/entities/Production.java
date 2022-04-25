package com.aegro.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Production implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduction;
	
	@Column(nullable = false)
	private Short production;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "plotId")
	private Plot plot;
	
	public Production() {
	}

	public Production(Short production) {
		this.production = production;
	}

	public void setProduction(Short production) {
		this.production = production;
	}


	public Long getIdProduction() {
		return idProduction;
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

}
