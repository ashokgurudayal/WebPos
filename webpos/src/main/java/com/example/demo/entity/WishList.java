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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Entity
public class WishList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long wish_list_id;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//ManyToOne relationship with Customers table
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customers customers;

	//OneToMany relationship with WishListProducts table
	@OneToMany(mappedBy = "wishList")
	private Set<WishListProducts> wishListProducts;
	
	public Customers getCustomers() {
		return customers;
	}
	public void setCustomers(Customers customers) {
		this.customers = customers;
	}
	public Set<WishListProducts> getWishListProducts() {
		return wishListProducts;
	}
	public void setWishListProducts(Set<WishListProducts> wishListProducts) {
		this.wishListProducts = wishListProducts;
	}
	public long getWish_list_id() {
		return wish_list_id;
	}
	public void setWish_list_id(long wish_list_id) {
		this.wish_list_id = wish_list_id;
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
