package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
/**
 * The entity for storing the different items that have been added to the cart.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class ShoppingBagItems {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String shopping_bag_items_id;
	private String product_id;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//ManyToOne relationship with ShoppingBag
	@ManyToOne
	@JoinColumn(name="shopping_bag_id")
	private ShoppingBag shoppingBag;
	
	public ShoppingBag getShoppingBag() {
		return shoppingBag;
	}
	public void setShoppingBag(ShoppingBag shoppingBag) {
		this.shoppingBag = shoppingBag;
	}
	public String getShopping_bag_items_id() {
		return shopping_bag_items_id;
	}
	public void setShopping_bag_items_id(String shopping_bag_items_id) {
		this.shopping_bag_items_id = shopping_bag_items_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
