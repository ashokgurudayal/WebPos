package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductSuppliers;
import com.example.demo.repository.ProductSuppliersRepository;

@Service
public class ProductSuppliersService {
	@Autowired
	private ProductSuppliersRepository productSuppliersRepository;
	
	public List<ProductSuppliers> getAllProductSuppliers() {
		List<ProductSuppliers> productSuppliers = new ArrayList<>();
		productSuppliersRepository.findAll().forEach(productSuppliers::add);
		return productSuppliers;
	}
	public ProductSuppliers getProductSuppliers(Long id) {
		// TODO Auto-generated method stub
		return productSuppliersRepository.findOne(id);
	}
	public void addProductSuppliers(ProductSuppliers productSuppliers) {
		// TODO Auto-generated method stub
		if(productSuppliersRepository.findOne(productSuppliers.getProduct_supplier_id())==null)
			productSuppliersRepository.save(productSuppliers);
	}
	public void updateProductSuppliers(ProductSuppliers productSuppliers, String id) {
		// TODO Auto-generated method stub
		productSuppliersRepository.save(productSuppliers);
	}
	public void deleteProductSuppliers(Long id) {
		// TODO Auto-generated method stub
		productSuppliersRepository.delete(id);
	}
	public void updateUnitsAndWeight(long productSupplierId,int units,double weight) {
		System.out.println(productSuppliersRepository.updateUnitsAndWeight(productSupplierId,units,weight));
	}
}
