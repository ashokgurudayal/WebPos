package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * The entity for storing the different products in the database.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long product_id;
	private long upccode;
	@NotEmpty(message = "Image has to be uploaded")
	private String name;
	private String description;
	private double costPrice;
	private double taxvalue;
	private double taxPercentage;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	@Lob
	//@Column(nullable=false)
	//@NotEmpty(message = "Image has to be uploaded")
	private byte[] productImage;
	
	//OneToMany relationship with WishListProducts table
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private Set<WishListProducts> wishListProducts;
	
	//ManyToOne relationship with Category table
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	//OneToMany relationship with ProductAttributes table
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private Set<ProductAttributes> productAttributes;
	
	
	
	//OneToMany relationship with Inventory table
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Set<Inventory> inventory;
	public Set<WishListProducts> getWishListProducts() {
		return wishListProducts;
	}
	
	
	public double getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}


	

	public double getTaxvalue() {
		return taxvalue;
	}


	public void setTaxvalue(double taxvalue) {
		this.taxvalue = taxvalue;
	}


	public double getTaxPercentage() {
		return taxPercentage;
	}


	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}


	public byte[] getProductImage() {
		return productImage;
	}


	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}


	public byte[] getImage() {
		return productImage;
	}

	public void setImage(byte[] image) {
		this.productImage = image;
	}

	public void setWishListProducts(Set<WishListProducts> wishListProducts) {
		this.wishListProducts = wishListProducts;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<ProductAttributes> getProductAttributes() {
		return productAttributes;
	}
	public void setProductAttributes(Set<ProductAttributes> productAttributes) {
		this.productAttributes = productAttributes;
	}
	
	public Set<Inventory> getInventory() {
		return inventory;
	}
	public void setInventory(Set<Inventory> inventory) {
		this.inventory = inventory;
	}
	
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public long getUpccode() {
		return upccode;
	}
	public void setUpccode(long upccode) {
		this.upccode = upccode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
