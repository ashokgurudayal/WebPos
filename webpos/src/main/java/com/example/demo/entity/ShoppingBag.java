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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
/**
 * The entity for storing the products that have been added to cart.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class ShoppingBag {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long shopping_bag_id;
	private String order_type;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//OneToMany relationship with ShoppingBagItems
	@OneToMany(mappedBy="shoppingBag")
	private Set<ShoppingBagItems> shoppingBagItems;
	
	//ManyToOne relationship with Customers
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customers customers;
	
	
	
	public Set<ShoppingBagItems> getShoppingBagItems() {
		return shoppingBagItems;
	}
	public void setShoppingBagItems(Set<ShoppingBagItems> shoppingBagItems) {
		this.shoppingBagItems = shoppingBagItems;
	}
	public Customers getCustomers() {
		return customers;
	}
	public void setCustomers(Customers customers) {
		this.customers = customers;
	}
	public long getShopping_bag_id() {
		return shopping_bag_id;
	}
	public void setShopping_bag_id(long shopping_bag_id) {
		this.shopping_bag_id = shopping_bag_id;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
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
