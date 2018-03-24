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
 * The entity for storing all the addresses that the system would use.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class Addresses {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long address_id;
	private String line_1;
	private String line_2;
	private String line_3;
	private String line_4;
	private String city;
	private String zipcode;
	private String state;
	private String country;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//OneToMany relationship with CustomerAddresses table
	@OneToMany(mappedBy = "addresses")
	private Set<CustomerAddresses> customerAddresses;
	public long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}
	public String getLine_1() {
		return line_1;
	}
	public void setLine_1(String line_1) {
		this.line_1 = line_1;
	}
	public String getLine_2() {
		return line_2;
	}
	public void setLine_2(String line_2) {
		this.line_2 = line_2;
	}
	public String getLine_3() {
		return line_3;
	}
	public void setLine_3(String line_3) {
		this.line_3 = line_3;
	}
	public String getLine_4() {
		return line_4;
	}
	public void setLine_4(String line_4) {
		this.line_4 = line_4;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
