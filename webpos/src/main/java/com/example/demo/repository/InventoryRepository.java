package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Attributes;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Product;
//DAO
public interface InventoryRepository extends CrudRepository<Inventory,Long>{
	Inventory findByProduct(Product product);
}
