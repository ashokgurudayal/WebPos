package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Company;
import com.example.demo.entity.PosUser;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	public List<Company> getAllCompany() {
		List<Company> products = new ArrayList<>();
		companyRepository.findAll().forEach(products::add);
		return products;
	}
	public Company getCompany(Long id) {
		// TODO Auto-generated method stub
		return companyRepository.findOne(id);
	}
	public void addCompany(Company company) {
		// TODO Auto-generated method stub
		if(companyRepository.findOne(company.getCompany_id())==null)
			companyRepository.save(company);
	}
	public void updateCompany(Company company, String id) {
		// TODO Auto-generated method stub
		companyRepository.save(company);
	}
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		companyRepository.delete(id);
	}
		
	public Company findByCompanyName(String companyName)  {
		// TODO Auto-generated method stub
		Company companyNameFound = companyRepository.findByName(companyName);
		return companyNameFound;
	}
}
