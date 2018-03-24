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

/**
 * The entity for storing the different payment methods that users could use.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class PaymentMethods {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long payment_method_id;
	private String name_on_card;
	private long card_no;
	@Temporal(TemporalType.DATE)
	private Date expiration_date;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	
	//ManyToOne relationship with CardTypes table
	@ManyToOne
	@JoinColumn(name="cardId")
	private CardTypes cardTypes;
	
	//ManyToOne relationship with Customers table
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customers customers;
	
	//OneToMany relationship with Orders table
	@OneToMany(mappedBy = "paymentMethods")
	private Set<Orders> orders;
	
	
	public long getPayment_method_id() {
		return payment_method_id;
	}
	public void setPayment_method_id(long payment_method_id) {
		this.payment_method_id = payment_method_id;
	}
	public Set<Orders> getOrders() {
		return orders;
	}
	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
	public Customers getCustomers() {
		return customers;
	}
	public void setCustomers(Customers customers) {
		this.customers = customers;
	}
	public long getPayment_method_code() {
		return payment_method_id;
	}
	public void setPayment_method_code(long payment_method_code) {
		this.payment_method_id = payment_method_code;
	}
	public String getName_on_card() {
		return name_on_card;
	}
	public void setName_on_card(String name_on_card) {
		this.name_on_card = name_on_card;
	}
	public long getCard_no() {
		return card_no;
	}
	public void setCard_no(long card_no) {
		this.card_no = card_no;
	}
	public Date getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	public CardTypes getCardTypes() {
		return cardTypes;
	}
	public void setCardTypes(CardTypes cardTypes) {
		this.cardTypes = cardTypes;
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
