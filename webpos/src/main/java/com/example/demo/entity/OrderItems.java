package com.example.demo.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The entity for storing the different Order items.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class OrderItems {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long order_item_id;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	private int units;
	private double weight;
	private double costPrice;
	private double taxValue;
	
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getTaxValue() {
		return taxValue;
	}
	public void setTaxValue(double taxValue) {
		this.taxValue = taxValue;
	}
	//ManyToOne relationship with Inventory table
	@ManyToOne
	@JoinColumn(name = "inventoryId")
	private Inventory inventory;
	
	//ManyToOne relationship with Orders table
	@ManyToOne
	@JoinColumn(name = "orderId")
	@JsonIgnore
	private Orders orders;
	
	
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public long getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(long order_item_id) {
		this.order_item_id = order_item_id;
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
