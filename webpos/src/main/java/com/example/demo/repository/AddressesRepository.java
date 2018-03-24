package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Addresses;
//DAO
public interface AddressesRepository extends CrudRepository<Addresses,Long>{

}
