package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Outlets;
import com.example.demo.repository.OutletsRepository;

@Service
public class OutletsService {
	@Autowired
	private OutletsRepository outletsRepository;
	
	public List<Outlets> getAllOutlets() {
		List<Outlets> attributes = new ArrayList<>();
		outletsRepository.findAll().forEach(attributes::add);
		return attributes;
	}
	public Outlets getOutlets(Long id) {
		// TODO Auto-generated method stub
		return outletsRepository.findOne(id);
	}
	public void addOutlets(Outlets category) {
		// TODO Auto-generated method stub
		if(outletsRepository.findOne(category.getOutlet_id())==null)
			outletsRepository.save(category);
	}
	public void updateOutlets(Outlets topic, String id) {
		// TODO Auto-generated method stub
		outletsRepository.save(topic);
	}
	public void deleteOutlets(Long id) {
		// TODO Auto-generated method stub
		outletsRepository.delete(id);
	}
	public List<Outlets> findByCompany(Company id){
		return outletsRepository.findByCompany(id);
	}
	
}
