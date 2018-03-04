package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PosUser;

public interface UserRepository extends JpaRepository<PosUser,Long>{

	Optional<PosUser> findByUsername(String userName);
	
}
