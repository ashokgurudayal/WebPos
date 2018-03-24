package com.example.demo.entity;

import java.util.Date;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * The entity for storing the different address types.ex:Shipping,Billing,etc
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class AddressTypes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long address_type_id;
	private String address_type;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//OneToManyRelationship with the CustomerAddresses table
	@OneToMany(mappedBy="addressTypes")
	private Set<CustomerAddresses> customerAddresses;
	
	
	public long getAddress_type_id() {
		return address_type_id;
	}
	public void setAddress_type_id(long address_type_id) {
		this.address_type_id = address_type_id;
	}
	public Set<CustomerAddresses> getCustomerAddresses() {
		return customerAddresses;
	}
	public void setCustomerAddresses(Set<CustomerAddresses> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}
	public long getAddress_type_code() {
		return address_type_id;
	}
	public void setAddress_type_code(long address_type_code) {
		this.address_type_id = address_type_code;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
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
