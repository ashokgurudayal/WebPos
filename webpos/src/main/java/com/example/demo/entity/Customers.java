package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

/**
 * The entity for storing the different customer details.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class Customers {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long customer_id;
	private String first_name;
	private String last_name;
	private String email_id;
	private int phone_no;
	private String userName;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date join_date;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	
	//OneToMany relationship with PaymentMethods table
	@OneToMany(mappedBy="customers")
	private Set<PaymentMethods> paymentMethods;
	
	//OneToMany relationship with CustomerAddresses table
	@OneToMany(mappedBy = "customers")
	private Set<CustomerAddresses> customerAddresses;
	
	//OneToMany relationship with ShoppingBag table
	@OneToMany(mappedBy = "customers")
	private Set<ShoppingBag> shoppingBag;
	
	//OneToMany relationship with WishList table
	@OneToMany(mappedBy = "customers")
	private Set<WishList> wishList;
	
	public int getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(int phone_no) {
		this.phone_no = phone_no;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<ShoppingBag> getShoppingBag() {
		return shoppingBag;
	}
	public void setShoppingBag(Set<ShoppingBag> shoppingBag) {
		this.shoppingBag = shoppingBag;
	}
	public Set<WishList> getWishList() {
		return wishList;
	}
	public void setWishList(Set<WishList> wishList) {
		this.wishList = wishList;
	}
	public Set<CustomerAddresses> getCustomerAddresses() {
		return customerAddresses;
	}
	public void setCustomerAddresses(Set<CustomerAddresses> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}
	public Set<PaymentMethods> getPaymentMethods() {
		return paymentMethods;
	}
	public void setPaymentMethods(Set<PaymentMethods> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
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
