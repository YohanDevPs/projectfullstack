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

/**
 * @author Yohan Silva
 */

@Entity
public class Production implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduction;
	
	@Column(nullable = true)
	private Double amount;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "plotId")
	private Plot plot;
	
	public Production() {
	}

	public Production(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setProduction(Double amount) {
		this.amount = amount;
	}

	public Plot getPlot() {
		return plot;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}

	public Long getIdProduction() {
		return idProduction;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
