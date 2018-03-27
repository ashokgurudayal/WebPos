package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Company;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderItemsRepository;
import com.example.demo.repository.OrdersRepository;

@Service
public class OrdersService {
	@Autowired
	private OrdersRepository ordersRepository;
	
	public List<Orders> getAllOrders() {
		List<Orders> orders = new ArrayList<>();
		ordersRepository.findAll().forEach(orders::add);
		return orders;
	}
	public void addOrders(Orders orders) {
		// TODO Auto-generated method stub
		if(ordersRepository.findOne(orders.getOrderId())==null)
			ordersRepository.save(orders);
	}
	public List<Orders> findByCreatetsBetween(Date startDate,Date endDate){
		return ordersRepository.findByCreatetsBetween(startDate,endDate);
	}
	
	
}
