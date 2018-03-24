package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Outlets {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long outlet_id;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	//ManyToOne relationship with Company table
	@ManyToOne
	@JoinColumn(name = "companyId")
	private Company company;
	
	//OneToOne relationship with Addresses table
	@OneToOne
	@JoinColumn(name = "addressId")
	private Addresses addresses;
	
	//OneToMany relationship with Inventory table
	@OneToMany(mappedBy = "outlets")
	private Set<Inventory> inventory;
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Addresses getAddresses() {
		return addresses;
	}
	public void setAddresses(Addresses addresses) {
		this.addresses = addresses;
	}
	public Set<Inventory> getInventory() {
		return inventory;
	}
	public void setInventory(Set<Inventory> inventory) {
		this.inventory = inventory;
	}
	public long getOutlet_id() {
		return outlet_id;
	}
	public void setOutlet_id(long outlet_id) {
		this.outlet_id = outlet_id;
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
