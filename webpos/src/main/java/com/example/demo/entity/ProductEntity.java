package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;




@Entity
public class ProductEntity {
	@Id
	private String id;
	@NotEmpty(message="Product name should not be blank")
	private String prodName;
	@NotEmpty(message="Product handler should not be blank")
	private String prodHandler;
	@NotEmpty(message="Product description should not be blank")
	private String description;
	@NotEmpty(message="Product Category should not be blank")
	private String category;
	@NotEmpty(message="Product Type should not be blank")
	private String prodType;
	@NotEmpty(message="Product name should not be blank")
	private String prodBrand;
	@NotEmpty(message="Product Supplier should not be blank")
	private String prodSupplier;
	@Size(min=3,max=15)
	private String supplierCode;
	@NotNull(message="Please enter the account code")
	private Integer accountCode;
	@NotNull(message="Please enter the purchasing code")
	private Integer purchasingCode;
	@NotNull(message="Please enter the selling price")
	private Double sp;
	@Lob
	@Column(name="product_image", nullable=false, columnDefinition="mediumblob")
	private byte[] image;
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public ProductEntity() {
		
	}
	public ProductEntity(String id, String prodName, String prodHandler, String description, String category,
			String prodType, String prodBrand, String prodSupplier, String supplierCode, Integer accountCode,
			Integer purchasingCode, Double sp) {
		super();
		this.id = id;
		this.prodName = prodName;
		this.prodHandler = prodHandler;
		this.description = description;
		this.category = category;
		this.prodType = prodType;
		this.prodBrand = prodBrand;
		this.prodSupplier = prodSupplier;
		this.supplierCode = supplierCode;
		this.accountCode = accountCode;
		this.purchasingCode = purchasingCode;
		this.sp = sp;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdHandler() {
		return prodHandler;
	}
	public void setProdHandler(String prodHandler) {
		this.prodHandler = prodHandler;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getProdBrand() {
		return prodBrand;
	}
	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}
	public String getProdSupplier() {
		return prodSupplier;
	}
	public void setProdSupplier(String prodSupplier) {
		this.prodSupplier = prodSupplier;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public Integer getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(Integer accountCode) {
		this.accountCode = accountCode;
	}
	public Integer getPurchasingCode() {
		return purchasingCode;
	}
	public void setPurchasingCode(Integer purchasingCode) {
		this.purchasingCode = purchasingCode;
	}
	public Double getSp() {
		return sp;
	}
	public void setSp(Double sp) {
		this.sp = sp;
	}
	
	
}
