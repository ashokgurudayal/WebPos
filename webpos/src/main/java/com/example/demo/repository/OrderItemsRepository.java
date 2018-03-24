package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Company;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.ProductEntity;
//DAO
public interface OrderItemsRepository extends CrudRepository<OrderItems,Long>{
	
}
