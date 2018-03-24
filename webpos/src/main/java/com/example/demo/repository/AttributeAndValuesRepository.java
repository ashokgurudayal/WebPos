package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.AttributeAndValues;
import com.example.demo.entity.Attributes;
//DAO
public interface AttributeAndValuesRepository extends CrudRepository<AttributeAndValues,Long>{
	@Query("select av from AttributeAndValues av where av.attributes.name=?1 and av.value=?2")
	AttributeAndValues findAttributeValuesEntryAttributeAndValue(String name,String value);
}
