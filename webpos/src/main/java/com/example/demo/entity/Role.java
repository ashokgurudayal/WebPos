package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	private Integer roleId;
	private String role;
	@ManyToMany(mappedBy="roles")
	private Set<PosUser> posUser;
	public Set<PosUser> getPosUser() {
		return posUser;
	}
	public void setPosUser(Set<PosUser> posUser) {
		this.posUser = posUser;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Role() {
		super();
	}
	public Role(Integer roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
