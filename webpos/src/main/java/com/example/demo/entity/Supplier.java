package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * The entity for storing the supplier information.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class Supplier {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long supplier_id;
	private String supplierName;
	private String description;
	private int phone_no;
	@Email
	private String email_id;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	@OneToOne
	private Addresses addresses;
	
	@OneToMany(mappedBy = "supplier")
	@JsonIgnore
	private Set<ProductSuppliers> productSuppliers;
	
	public int getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(int phone_no) {
		this.phone_no = phone_no;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public Set<ProductSuppliers> getProductSuppliers() {
		return productSuppliers;
	}
	public void setProductSuppliers(Set<ProductSuppliers> productSuppliers) {
		this.productSuppliers = productSuppliers;
	}
	public long getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(long supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getSupplier_name() {
		return supplierName;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplierName = supplier_name;
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
	public Addresses getAddresses() {
		return addresses;
	}
	public void setAddresses(Addresses addresses) {
		this.addresses = addresses;
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
