package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Company;
import com.example.demo.entity.ProductEntity;
//DAO
public interface CategoryRepository extends CrudRepository<Category,Long>{

	Category findByName(String categoryName);
}
