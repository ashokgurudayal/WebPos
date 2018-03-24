package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The entity for storing the addresses of customers.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class CustomerAddresses {
	@EmbeddedId
	private CustomerAddressesEmbeddable custAddrId;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	//ManyToOne relationship with Customers table
	@ManyToOne
	@MapsId("customer_id")
	@JoinColumn(name="customer_id")
	private Customers customers;
	
	
	//ManyToOne relationship with Addresses table
	@ManyToOne
	@MapsId("address_id")
	@JoinColumn(name="address_id")
	private Addresses addresses;
	
	//ManyToOne relationship with AddressTypes table
	@ManyToOne
	@MapsId("address_type_id")
	@JoinColumn(name="address_type_id")
	private AddressTypes addressTypes;
	
	//OneToMany relationship with Orders table
	@OneToMany(mappedBy = "customerAddresses")
	private Set<Orders> orders;
	public Addresses getAddresses() {
		return addresses;
	}
	public void setAddresses(Addresses addresses) {
		this.addresses = addresses;
	}
	public AddressTypes getAddressTypes() {
		return addressTypes;
	}
	public void setAddressTypes(AddressTypes addressTypes) {
		this.addressTypes = addressTypes;
	}
	public Customers getCustomers() {
		return customers;
	}
	public void setCustomers(Customers customers) {
		this.customers = customers;
	}
	public CustomerAddressesEmbeddable getCustAddrId() {
		return custAddrId;
	}
	public void setCustAddrId(CustomerAddressesEmbeddable custAddrId) {
		this.custAddrId = custAddrId;
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
