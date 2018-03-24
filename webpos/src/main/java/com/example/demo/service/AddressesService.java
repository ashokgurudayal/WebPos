package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Addresses;
import com.example.demo.repository.AddressesRepository;

@Service
public class AddressesService {
	@Autowired
	private AddressesRepository addressesRepository;
	
	public List<Addresses> getAllAddresses() {
		List<Addresses> addresses = new ArrayList<>();
		addressesRepository.findAll().forEach(addresses::add);
		return addresses;
	}
	public Addresses getAddresses(Long id) {
		// TODO Auto-generated method stub
		return addressesRepository.findOne(id);
	}
	public void addAddresses(Addresses address) {
		// TODO Auto-generated method stub
		if(addressesRepository.findOne(address.getAddress_id())==null)
			addressesRepository.save(address);
	}
	public void updateAddresses(Addresses topic, String id) {
		// TODO Auto-generated method stub
		addressesRepository.save(topic);
	}
	public void deleteAddresses(Long id) {
		// TODO Auto-generated method stub
		addressesRepository.delete(id);
	}
}
