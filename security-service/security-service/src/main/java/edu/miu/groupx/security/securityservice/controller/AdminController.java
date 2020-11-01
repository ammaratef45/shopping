package edu.miu.groupx.security.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
	
	
	// Display the Form for Creating New User
	@GetMapping("/new")
	public String displayEmployeeForm(Model model)
	{
		UserAdmin user = new UserAdmin();
		model.addAttribute("user", user);
		
		return "users/new-user";
	}
	
	// Add New User Info
	@PostMapping("/add")
	public String getAdd(@RequestBody UserAdmin user)
	{	
		adminSer.addUser(user);
		
		return "redirect:/admin";
	}
	
	// Get the whole User List
	@GetMapping("/display")
	public List<User> displayUserList()
	{
		return adminSer.findAll();
	}
	
	// Delete the specific User
	@PostMapping("/delete/{id}")
	public String getDelete(@PathVariable("id") String id)
	{
		adminSer.delete(id);
		
		return "redirect:/admin";
	}
	
	// Edit the specific User by ID
	@PutMapping("/edit/{id}")
	public String updateUserById(@RequestBody UserAdmin user, @PathVariable("id") String id)
	{
		UserAdmin tmpUser = adminSer.getUser(id);
		
		tmpUser.setName(user.getName());
		tmpUser.setEmail(user.getEmail());
		tmpUser.setImage(user.getImage());
		tmpUser.setUsername(user.getUsername());
		tmpUser.setPassword(user.getPassword());
		tmpUser.setAddress(user.getAddress());
		tmpUser.setCardno(user.getCardno());
		tmpUser.setEnabled(user.getEnabled());
		tmpUser.setRole(user.getRole());
		
		adminSer.addUser(tmpUser);
		
		return "redirect:/admin";
	}
	
	
	// Check User's enabled and Approve or Reject Vendor's request
	@PostMapping("/vendor/request/{id}")
	public String getApprovalForVendor(@PathVariable("id") String id)
	{
		if(adminSer.getApprovalForVendor(id))
		{
			// Approve
			adminSer.updateStatusProduct(id, "Approved");
			
			return "redirect:/admin";
		}// if
		else
		{
			// Reject
			// Change Status to Reject
			adminSer.updateStatusProduct(id, "Reject");
			
			return "redirect:/admin";
		}// else
	}
	
	
	
	
}
