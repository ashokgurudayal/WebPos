package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Product;
import com.example.demo.repository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;
	
	public List<Inventory> getAllInventories() {
		List<Inventory> inventory = new ArrayList<>();
		inventoryRepository.findAll().forEach(inventory::add);
		return inventory;
	}
	public Inventory getInventory(Long id) {
		// TODO Auto-generated method stub
		return inventoryRepository.findOne(id);
	}
	public void addInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		if(inventoryRepository.findOne(inventory.getInventory_id())==null)
			inventoryRepository.save(inventory);
	}
	public void updateInventory(Inventory inventory, String id) {
		// TODO Auto-generated method stub
		inventoryRepository.save(inventory);
	}
	public void deleteInventory(Long id) {
		// TODO Auto-generated method stub
		inventoryRepository.delete(id);
	}
	public Inventory findByProduct(Product product) {
		return inventoryRepository.findByProduct(product);
	}
	
}
