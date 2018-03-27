package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Company;
import com.example.demo.entity.PosUser;
@Repository
public interface UserRepository extends JpaRepository<PosUser,Long>{
	Optional<PosUser> findByUsername(String userName);
	
	@Query("select company from PosUser pu where pu.username=?1")
	Company findCompanyOfUser(String userName) ;
	
	@Query("select pu from PosUser pu where pu.company=?1")
	List<PosUser> findAllUsersOfCompany(Company company) ;
}
