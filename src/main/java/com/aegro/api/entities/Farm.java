package com.aegro.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Farm implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFarm;

	@Column(unique = true)
	private String nameFarm;


	@OneToMany(mappedBy = "farm")
	private List<Plot> plots = new ArrayList<>();

	public Farm() {
	}

	public Farm(String nameFarm, List<Plot> plots) {
		this.nameFarm = nameFarm;
		this.plots = plots;
	}

	public Long getIdFarm() {
		return idFarm;
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

	public void setPlots(List<Plot> plots) {
		this.plots = plots;
	}
	
}