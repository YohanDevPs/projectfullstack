package com.aegro.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Yohan Silva
 */

@Entity
public class Farm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true)
	private Double farmProductivity;
	
	@Column(unique = false)
	private String nameFarm;
	
//	@JsonIgnore
	@OneToMany(cascade=CascadeType.MERGE, mappedBy="farm", orphanRemoval=true)
	private List<Plot> plots = new ArrayList<>();
	
	public Farm() {
	}
	
	public Farm(String nameFarm) {
		this.nameFarm = nameFarm;
	}

	public Long getId() {
		return id;
	}

	public String getNameFarm() {
		return nameFarm;
	}

	public void setNameFarm(String nameFarm) {
		this.nameFarm = nameFarm;
	}

	public List<Plot> getPlots() {
		return plots;
	}

	public Double getFarmProductivity() {
		return farmProductivity;
	}

	public void setFarmProductivity(Double farmProductivity) {
		this.farmProductivity = farmProductivity;
	}

}
