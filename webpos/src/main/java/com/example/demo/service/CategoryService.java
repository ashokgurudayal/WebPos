package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Company;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories() {
		List<Category> products = new ArrayList<>();
		categoryRepository.findAll().forEach(products::add);
		return products;
	}
	public Category getCategory(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findOne(id);
	}
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		if(categoryRepository.findOne(category.getCategory_id())==null)
			categoryRepository.save(category);
	}
	public void updateCategory(Category topic, String id) {
		// TODO Auto-generated method stub
		categoryRepository.save(topic);
	}
	public void deleteCategory(Long id) {
		// TODO Auto-generated method stub
		categoryRepository.delete(id);
	}
	public Category findByCategoryName(String categoryName)  {
		// TODO Auto-generated method stub
		return categoryRepository.findByName(categoryName);
	}
}
