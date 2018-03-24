package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductAttributes;
import com.example.demo.repository.ProductAttributesRepository;

@Service
public class ProductAttributesService {
	@Autowired
	private ProductAttributesRepository productAttributesRepository;
	
	public List<ProductAttributes> getAllProductAttributes() {
		List<ProductAttributes> productAttributes = new ArrayList<>();
		productAttributesRepository.findAll().forEach(productAttributes::add);
		return productAttributes;
	}
	public ProductAttributes getProductAttributes(Long id) {
		// TODO Auto-generated method stub
		return productAttributesRepository.findOne(id);
	}
	public void addProductAttributes(ProductAttributes productAttributes) {
		// TODO Auto-generated method stub
		if(productAttributesRepository.findOne(productAttributes.getProductAttributeId())==null)
			productAttributesRepository.save(productAttributes);
	}
	public void updateProduct(ProductAttributes product, Long id) {
		// TODO Auto-generated method stub
		productAttributesRepository.save(product);
	}
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productAttributesRepository.delete(id);
	}
}
