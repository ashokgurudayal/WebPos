package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.Role;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;

public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public Role getRole(int id) {
		// TODO Auto-generated method stub
		return roleRepository.findOne(id);
	}
	
}
