package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Company;
import com.example.demo.entity.OrderItems;
import com.example.demo.repository.OrderItemsRepository;

@Service
public class OrderItemsService {
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	
	
	public void addOrderItems(OrderItems orderItems) {
		// TODO Auto-generated method stub
		if(orderItemsRepository.findOne(orderItems.getOrder_item_id())==null)
			orderItemsRepository.save(orderItems);
	}
	
	
}
