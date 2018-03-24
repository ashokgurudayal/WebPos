package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * The entity for storing the different values that attributes could use.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class AttributeAndValues {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long attributeNormalValuesId;
	private String value;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//ManyToOne relationship with the Attributes table
	@ManyToOne
	@JoinColumn(name="attributeId")
	@JsonIgnore
	private Attributes attributes;
	
	//OneToMany relationship with the ProductAttributes table
	@OneToMany(mappedBy = "attributeAndValues")
	private Set<ProductAttributes> productAttributes;
	
	public Set<ProductAttributes> getProductAttributes() {
		return productAttributes;
	}
	public void setProductAttributes(Set<ProductAttributes> productAttributes) {
		this.productAttributes = productAttributes;
	}
	public long getAttributeNormalValuesId() {
		return attributeNormalValuesId;
	}
	public void setAttributeNormalValuesId(long attributeNormalValuesId) {
		this.attributeNormalValuesId = attributeNormalValuesId;
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
	
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
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
