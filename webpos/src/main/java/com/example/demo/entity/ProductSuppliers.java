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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The entity for storing the product and supplier information like A supplied B qty of product C.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class ProductSuppliers {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long product_supplier_id;
	private int units;
	private double weight;
	private Date createts;
	private Date modifyts;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name="inventory_id")
	private Inventory inventory;
	
	
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public long getProduct_supplier_id() {
		return product_supplier_id;
	}
	public void setProduct_supplier_id(long product_supplier_id) {
		this.product_supplier_id = product_supplier_id;
	}
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
