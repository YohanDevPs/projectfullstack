package com.aegro.api.entities;

import java.io.Serializable;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nameFarm;
	
	@Column
	private short productionFarm;

	public Farm() {
	}
	
	public Farm(Long id, String nameFarm, Short productionFarm) {
		this.id = id;
		this.nameFarm = nameFarm;
		this.productionFarm = productionFarm;
	}
	
	public short getProductionFarm() {
		return productionFarm;
	}

	public void setProductionFarm(short productionFarm) {
		this.productionFarm = productionFarm;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNameFarm() {
		return nameFarm;
	}
	
	public void setNameFarm(String nameFarm) {
		this.nameFarm = nameFarm;
	}

	
	}
