package com.Vkart.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Roles  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleId;

	@Column(nullable = false, unique = true)
	private String roleName;

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Roles(String roleName, List<User> user) {
		super();
		this.roleName = roleName;
		this.user = user;
	}

	@ManyToMany(mappedBy = "roles")
	private List<User> user;

	public Roles(String roleName) {
		super();
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}


}
