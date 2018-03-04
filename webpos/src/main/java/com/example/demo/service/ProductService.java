package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	public ProductEntity getProduct(String id) {
		// TODO Auto-generated method stub
		return productRepository.findOne(id);
	}
	public void addProduct(ProductEntity product) {
		// TODO Auto-generated method stub
		if(productRepository.findOne(product.getId())==null)
			productRepository.save(product);
	}
	public void updateProduct(ProductEntity topic, String id) {
		// TODO Auto-generated method stub
		productRepository.save(topic);
	}
	public void deleteProduct(String id) {
		// TODO Auto-generated method stub
		productRepository.delete(id);
	}
}
