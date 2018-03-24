package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	
	public List<Supplier> getAllSuppliers() {
		List<Supplier> suppliers = new ArrayList<>();
		supplierRepository.findAll().forEach(suppliers::add);
		return suppliers;
	}
	public Supplier getSupplier(Long id) {
		// TODO Auto-generated method stub
		return supplierRepository.findOne(id);
	}
	public void addSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		if(supplierRepository.findOne(supplier.getSupplier_id())==null)
			supplierRepository.save(supplier);
	}
	public void updateSupplier(Supplier topic, String id) {
		// TODO Auto-generated method stub
		supplierRepository.save(topic);
	}
	public void deleteSupplier(Long id) {
		// TODO Auto-generated method stub
		supplierRepository.delete(id);
	}
	public Supplier findBySupplierName(String name) {
		return supplierRepository.findBysupplierName(name);
	}
}
