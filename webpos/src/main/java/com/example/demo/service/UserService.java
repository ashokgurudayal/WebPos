package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Company;
import com.example.demo.entity.PosUser;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.Role;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	public List<PosUser> getAllProducts() {
		List<PosUser> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	public PosUser getUsers(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}
	public void addUser(PosUser user) {
		// TODO Auto-generated method stub
		Set<Role> userRoles = new HashSet<Role>();
		userRoles.add(roleRepository.findOne(1));
		user.setRoles(userRoles);
		userRepository.save(user);
	}
	public void updateUser(PosUser user, String id) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		userRepository.delete(id);
	}
	public Optional<PosUser> findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}
	public Company findCompanyOfUser(String userName) {
		return userRepository.findCompanyOfUser(userName);
	}
}
