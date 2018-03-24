package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

/**
 * The entity for storing the orders of customers.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long orderId;
	private String order_type;
	private double total_amount;
	private double total_tax;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//ManyToOne relationship with PaymentMethods table
	@ManyToOne
	@JoinColumn(name = "paymentMethodId")
	private PaymentMethods paymentMethods;
	
	//OneToMany relationship with OrderItems table
	@OneToMany(mappedBy="orders")
	private Set<OrderItems> orderItems;
	
	//ManyToOne relationship with CustomerAddresses table
	@ManyToOne
	@JoinColumns({
	        @JoinColumn(name="customer_id", referencedColumnName="customer_id"),
	        @JoinColumn(name="address_id", referencedColumnName="address_id"),
	        @JoinColumn(name="address_type_id", referencedColumnName="address_type_id")
	    })
	private CustomerAddresses customerAddresses;
	
	
	public PaymentMethods getPaymentMethods() {
		return paymentMethods;
	}
	public void setPaymentMethods(PaymentMethods paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	public Set<OrderItems> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	public CustomerAddresses getCustomerAddresses() {
		return customerAddresses;
	}
	public void setCustomerAddresses(CustomerAddresses customerAddresses) {
		this.customerAddresses = customerAddresses;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public double getTotal_tax() {
		return total_tax;
	}
	public void setTotal_tax(double total_tax) {
		this.total_tax = total_tax;
	}
	@JsonSerialize(using = DateSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="EST")
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
