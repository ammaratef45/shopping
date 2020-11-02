package edu.miu.groupx.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "User")
public class UserAdmin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long  id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "IMAGE")
	private String image;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ENABLED")
	private Boolean enabled;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "ROLE")
	@Enumerated(EnumType.STRING)
	private RoleAdmin role;
	
	@Column(name = "USERSTATUS")
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
	
		
	
	
	
	public UserAdmin() {}





	public Long getId() {
		return id;
	}





	public UserStatus getUserStatus() {
		return userStatus;
	}





	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getImage() {
		return image;
	}





	public void setImage(String image) {
		this.image = image;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public Boolean getEnabled() {
		return enabled;
	}





	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public RoleAdmin getRoleAdmin() {
		return role;
	}





	public void setRoleAdmin(RoleAdmin role) {
		this.role = role;
	}
	
	
	
	
	
	
	
	
	
	
}
