package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Company;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.Orders;
import com.example.demo.entity.ProductEntity;
//DAO
public interface OrdersRepository extends CrudRepository<Orders,Long>{
	List<Orders> findByCreatetsBetween(Date startDate,Date endDate);
}
