package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerAddressesEmbeddable implements Serializable{
	private long customer_id;
	private long address_id;
	private long address_type_id;
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public long getAddress_type_id() {
		return address_type_id;
	}
	public void setAddress_type_id(long address_type_id) {
		this.address_type_id = address_type_id;
	}
	public long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (address_id ^ (address_id >>> 32));
		result = prime * result + (int) (customer_id ^ (customer_id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerAddressesEmbeddable other = (CustomerAddressesEmbeddable) obj;
		if (address_id != other.address_id)
			return false;
		if (customer_id != other.customer_id)
			return false;
		return true;
	}
	
}
