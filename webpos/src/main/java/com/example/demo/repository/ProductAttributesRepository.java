package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductAttributes;
//DAO
public interface ProductAttributesRepository extends CrudRepository<ProductAttributes,Long>{
	
}
