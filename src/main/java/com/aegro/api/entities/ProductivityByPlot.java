package com.aegro.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductivityByPlot {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProductivity;
	
	@Column
	private Integer productivity;
		
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "plotId")
	private Plot plot;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "productvity_id")
	private ProductivityByPlot productivityPlot;
	
	public ProductivityByPlot(Integer amount) {
	}	
	
	public ProductivityByPlot() {
	}	
	

	public Long getIdProductivity() {
		return idProductivity;
	}

	public void setIdProductivity(Long idProductivity) {
		this.idProductivity = idProductivity;
	}

	public Plot getPlot() {
		return plot;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}

	public ProductivityByPlot getProductivityPlot() {
		return productivityPlot;
	}

	public void setProductivityPlot(ProductivityByPlot productivityPlot) {
		this.productivityPlot = productivityPlot;
	}

	public void setProductivity(Integer productivity) {
		this.productivity = productivity;
	}
	public Integer getProductivity() {
		return productivity;
	}

}
