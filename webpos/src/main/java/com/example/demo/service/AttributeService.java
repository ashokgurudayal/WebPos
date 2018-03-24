package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Attributes;
import com.example.demo.repository.AttributeRepository;

@Service
public class AttributeService {
	@Autowired
	private AttributeRepository attributeRepository;
	
	public List<Attributes> getAllAttributes() {
		List<Attributes> attributes = new ArrayList<>();
		attributeRepository.findAll().forEach(attributes::add);
		return attributes;
	}
	public Attributes getAttributes(Long id) {
		// TODO Auto-generated method stub
		return attributeRepository.findOne(id);
	}
	public void addAttributes(Attributes category) {
		// TODO Auto-generated method stub
		if(attributeRepository.findOne(category.getAttributeId())==null)
			attributeRepository.save(category);
	}
	public void updateAttributes(Attributes topic, String id) {
		// TODO Auto-generated method stub
		attributeRepository.save(topic);
	}
	public void deleteAttributes(Long id) {
		// TODO Auto-generated method stub
		attributeRepository.delete(id);
	}
	public Attributes findByName(String name) {
		return attributeRepository.findByName(name);
	}
}
