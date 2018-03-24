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
/**
 * The entity for storing the different Product attributes in the database.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class ProductAttributes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long productAttributeId;
	private String value;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	//ManyToOne relationship with AttributeAndValues table
	@ManyToOne
	@JoinColumn(name="attribute_id")
	private AttributeAndValues attributeAndValues;
	
	//ManyToOne relationship with Product table
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getProductAttributeId() {
		return productAttributeId;
	}

	public void setProductAttributeId(long productAttributeId) {
		this.productAttributeId = productAttributeId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public AttributeAndValues getAttributeAndValues() {
		return attributeAndValues;
	}

	public void setAttributeAndValues(AttributeAndValues attributeAndValues) {
		this.attributeAndValues = attributeAndValues;
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
