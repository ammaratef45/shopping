package edu.miu.groupx.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.groupx.security.model.UserAdmin;
import edu.miu.groupx.security.service.AdminService;



@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private AdminService adminSer;	
	
	
	// Add New User Info
	@PostMapping("/add")
	public void  getAdd(@RequestBody UserAdmin user)
	{	
		adminSer.addUser(user);
	}
	
	// Get the whole User List
	@GetMapping("/display")
	public List<UserAdmin> displayUserList()
	{
		return adminSer.findAll();
	}
	
	// Delete the specific User
	@PostMapping("/delete/{id}")
	public void  getDelete(@PathVariable("id") Long id)
	{
		adminSer.delete(id);
	}
	
	// Edit the specific User by ID
	@PutMapping("/edit/{id}")
	public void  updateUserById(@RequestBody UserAdmin user, @PathVariable("id") Long id)
	{
		UserAdmin tmpUser = adminSer.getUser(id);
		
		tmpUser.setName(user.getName());
		tmpUser.setEmail(user.getEmail());
		tmpUser.setImage(user.getImage());
		tmpUser.setUsername(user.getUsername());
		tmpUser.setPassword(user.getPassword());
		tmpUser.setAddress(user.getAddress());
		tmpUser.setEnabled(user.getEnabled());
		tmpUser.setRoleAdmin(user.getRoleAdmin());
		tmpUser.setUserStatus(user.getUserStatus());
		
		adminSer.addUser(tmpUser);
	}
	
	
	// Check User's enabled and Approve or Reject Vendor's request
	@PostMapping("/vendor/request/{id}")
	public void  getApprovalForVendor(@PathVariable("id") Long id)
	{
		if(adminSer.getApprovalForVendor(id))
		{
			// Approve Product
			adminSer.updateStatusProduct(id, "APPROVED");
			// Update User Approve status
			adminSer.approveUser(adminSer.getUser(id));
		}// if
		else
		{
			// Reject Product
			adminSer.updateStatusProduct(id, "REJECTED");
			// Update User Reject status
			adminSer.rejectUser(adminSer.getUser(id));
		}// else
	}
	
	
	
	
}
