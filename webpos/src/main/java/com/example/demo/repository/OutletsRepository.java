package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Attributes;
import com.example.demo.entity.Company;
import com.example.demo.entity.Outlets;
//DAO
public interface OutletsRepository extends CrudRepository<Outlets,Long>{
	List<Outlets> findByCompany(Company company);
}
