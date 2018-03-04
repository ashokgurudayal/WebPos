package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.ProductEntity;
//DAO
public interface ProductRepository extends CrudRepository<ProductEntity,String>{

}
