package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Company;
//DAO
public interface CompanyRepository extends CrudRepository<Company,Long>{
	Company findByName(String categoryName);
}
