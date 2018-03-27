package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
//DAO
public interface ProductRepository extends CrudRepository<Product,Long>{
	
	List<Product> findByCategory(Category category);
	
	Product findByupccode(long upcCode);
}
