package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The entity for storing the employee details who use the POS application.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class PosUser {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long userId;
	@NotEmpty(message="First name should not be blank")
	private String firstName;
	@NotEmpty(message="Last name should not be blank")
	private String lastName;
	private char gender;
	private String emailId;
	private Integer phoneNo;
	private String password;
	private String username;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PosUser_Role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	@JsonIgnore
	private Set<Role> roles;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "companyId")
	private Company company;
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public PosUser() {
		super();
		
	}
	public PosUser(PosUser user) {
		// TODO Auto-generated constructor stub
		this.userId = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.gender = user.getGender();
		this.emailId = user.getEmailId();
		this.phoneNo = user.getPhoneNo();
		this.password = user.getPassword();
		this.username = user.getUsername();
		this.roles = user.getRoles();
	}
	public long getId() {
		return userId;
	}
	public void setId(long id) {
		this.userId = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Integer getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
