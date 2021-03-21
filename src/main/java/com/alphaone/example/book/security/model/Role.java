package com.alphaone.example.book.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = 1092984132200200413L;

	@Id
	private long roleId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	protected Role() {
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
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

	@Override
	public String getAuthority() {
		return name;
	}

}
