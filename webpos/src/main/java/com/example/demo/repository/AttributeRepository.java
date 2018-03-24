package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Attributes;
//DAO
public interface AttributeRepository extends CrudRepository<Attributes,Long>{
	Attributes findByName(String name);
}
