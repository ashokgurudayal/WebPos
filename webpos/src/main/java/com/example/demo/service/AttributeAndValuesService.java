package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AttributeAndValues;
import com.example.demo.entity.Attributes;
import com.example.demo.repository.AttributeAndValuesRepository;

@Service
public class AttributeAndValuesService {
	@Autowired
	private AttributeAndValuesRepository attributeAndValuesRepository;
	
	public List<AttributeAndValues> getAllAttributeAndValues() {
		List<AttributeAndValues> attributeAndValues = new ArrayList<>();
		attributeAndValuesRepository.findAll().forEach(attributeAndValues::add);
		return attributeAndValues;
	}
	public AttributeAndValues getAttributeAndValues(Long id) {
		// TODO Auto-generated method stub
		return attributeAndValuesRepository.findOne(id);
	}
	public void addAttributeAndValues(AttributeAndValues attributeAndValues) {
		// TODO Auto-generated method stub
		if(attributeAndValuesRepository.findOne(attributeAndValues.getAttributeNormalValuesId())==null)
			attributeAndValuesRepository.save(attributeAndValues);
	}
	public void updateAttributeAndValues(AttributeAndValues topic, String id) {
		// TODO Auto-generated method stub
		attributeAndValuesRepository.save(topic);
	}
	public void deleteAttributeAndValues(Long id) {
		// TODO Auto-generated method stub
		attributeAndValuesRepository.delete(id);
	}
	public AttributeAndValues findAttributeValuesEntryAttributeAndValue(String name,String value) {
		return attributeAndValuesRepository.findAttributeValuesEntryAttributeAndValue(name, value);
	}
}
