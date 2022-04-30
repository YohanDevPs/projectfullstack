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
import javax.persistence.OneToMany;

/**
 * @author Yohan Silva
 */

@Entity
public class Farm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String nameFarm;

	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="farm", orphanRemoval=true)
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
}
