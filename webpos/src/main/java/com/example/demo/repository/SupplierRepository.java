package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Attributes;
import com.example.demo.entity.Supplier;
//DAO
public interface SupplierRepository extends CrudRepository<Supplier,Long>{
	Supplier findBysupplierName(String name);
}
