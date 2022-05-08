package com.aegro.api.service.Impl;



import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aegro.api.entities.Farm;
import com.aegro.api.repository.FarmRepository;
import com.aegro.api.service.FarmService;
import com.aegro.api.service.ProductivityFarm;

/**
 * @author Yohan Silva
 */

@Service
public class FarmServiceImpl implements FarmService {

	
	@Autowired
	private FarmRepository farmRepository;
	
	@Autowired
	private ProductivityFarm updateProductivity;
	

	@Override
	public Farm saveFarm(Farm farm) {
		return farmRepository.save(farm);
	}

	@Override
	public List<Farm> farmList() {
		return farmRepository.findAll();
	}
	
  @Override
	public Optional<Farm> getFarmByIdWithYourPlots(Long id){
		return Optional.ofNullable(farmRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found "+ id)));
	}

	@Override
	public void removeFarmById(Long id) {
		farmRepository.deleteById(id);
	}

	@Override
	public void updateProductivityFarm(Long idFarm) {
		updateProductivity.updateProductivityFarm(idFarm);
	}

}
