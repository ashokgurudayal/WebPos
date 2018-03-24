package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The entity for storing the card types in db.ex:credit card,master card,etc.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class CardTypes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long card_id;
	private String card_type;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//OneToMany Relationship with the PaymentMethods table
	@OneToMany(mappedBy = "cardTypes")
	private Set<PaymentMethods> paymentMethods;
	
	
	public Set<PaymentMethods> getPaymentMethods() {
		return paymentMethods;
	}
	public void setPaymentMethods(Set<PaymentMethods> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	public long getCard_id() {			
		return card_id;
	}
	public void setCard_id(long card_id) {
		this.card_id = card_id;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
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
