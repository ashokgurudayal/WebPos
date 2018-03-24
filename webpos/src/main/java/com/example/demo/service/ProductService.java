package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	public List<Product> findByCategoryId(Category id){
		return productRepository.findByCategory(id);
	}
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findOne(id);
	}
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		if(productRepository.findOne(product.getProduct_id())==null)
			productRepository.save(product);
	}
	public void updateProduct(Product product, Long id) {
		// TODO Auto-generated method stub
		productRepository.save(product);
	}
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRepository.delete(id);
	}
}
