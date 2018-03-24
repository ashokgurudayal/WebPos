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

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The entity for storing the different inventory details that users could use.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long inventory_id;
	private int reorder_point_units;
	private double reorder_point_weight;
	@Formula(value="(select sum(t.units) from product_suppliers t where t.inventory_id = inventory_id)")
	private int units_available;
	@Formula(value="(select sum(t.weight) from product_suppliers t where t.inventory_id = inventory_id)")
	private double total_weight;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	//ManyToOne relationship with Product table
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	//ManyToOne relationship with Outlets table
	@ManyToOne
	@JoinColumn(name = "outletId")
	@JsonIgnore
	private Outlets outlets;
	
	@OneToMany(mappedBy = "inventory")
	@JsonIgnore
	private Set<ProductSuppliers> productSupplier;
	
	@OneToMany
	@JsonIgnore
	private Set<OrderItems> orderItems;
	
	
	public Set<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public Set<ProductSuppliers> getProductSupplier() {
		return productSupplier;
	}

	public void setProductSupplier(Set<ProductSuppliers> productSupplier) {
		this.productSupplier = productSupplier;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Outlets getOutlets() {
		return outlets;
	}

	public void setOutlets(Outlets outlets) {
		this.outlets = outlets;
	}

	public long getInventory_id() {
		return inventory_id;
	}
	
	public void setInventory_id(long inventory_id) {
		this.inventory_id = inventory_id;
	}
	public int getReorder_point_units() {
		return reorder_point_units;
	}
	public void setReorder_point_units(int reorder_point_units) {
		this.reorder_point_units = reorder_point_units;
	}
	public double getReorder_point_weight() {
		return reorder_point_weight;
	}
	public void setReorder_point_weight(double reorder_point_weight) {
		this.reorder_point_weight = reorder_point_weight;
	}
	public int getUnits_available() {
		return units_available;
	}
	public void setUnits_available(int units_available) {
		this.units_available = units_available;
	}
	public double getTotal_weight() {
		return total_weight;
	}
	public void setTotal_weight(double total_weight) {
		this.total_weight = total_weight;
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
