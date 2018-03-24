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

/**
 * The entity for storing the different company details.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long company_id;
	private String name;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//OneToMany relationship with PosUser table
	@OneToMany(mappedBy = "company")
	private Set<PosUser> posUser;
	
	//OneTOMany relationship with Outlets table
	@OneToMany(mappedBy = "company")
	private Set<Outlets> outlets;
	
	
	
	public Set<PosUser> getPosUser() {
		return posUser;
	}
	public void setPosUser(Set<PosUser> posUser) {
		this.posUser = posUser;
	}
	public Set<Outlets> getOutlets() {
		return outlets;
	}
	public void setOutlets(Set<Outlets> outlets) {
		this.outlets = outlets;
	}
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
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
