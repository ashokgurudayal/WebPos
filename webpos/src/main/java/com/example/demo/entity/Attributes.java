package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The entity for storing the different attributes in the database.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class Attributes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long attributeId;
	private String name;
	private String description;
	private Date createts;
	private Date modifyts;
	
	//OneToMany relationship with the AttributeAndValues table
	@OneToMany(mappedBy = "attributes")
	@JsonIgnore
	private Set<AttributeAndValues> attributeNormalValues;
	
	public Set<AttributeAndValues> getAttributeNormalValues() {
		return attributeNormalValues;
	}
	public void setAttributeNormalValues(Set<AttributeAndValues> attributeNormalValues) {
		this.attributeNormalValues = attributeNormalValues;
	}
	public long getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatets() {
		return createts;
	}
	public void setCreatets(Date createts) {
		this.createts = createts;
	}
	public Date getModifyts() {
		return modifyts;
	}
	public void setModifyts(Date modifyts) {
		this.modifyts = modifyts;
	}
	@PrePersist
	protected void onCreate() {
		this.createts = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.modifyts = new Date();
	}
}
