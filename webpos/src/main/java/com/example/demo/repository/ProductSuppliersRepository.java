package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Attributes;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.ProductSuppliers;
//DAO
@Repository
public interface ProductSuppliersRepository extends CrudRepository<ProductSuppliers,Long>{
	@Modifying(clearAutomatically = true)
	@Transactional 
	@Query("update ProductSuppliers ps set ps.units=?2,ps.weight=?3 where ps.product_supplier_id=?1 ")
	int updateUnitsAndWeight(long productSupplierId,int units,double weight);
}
