package edu.miu.groupx.security.service;

import java.util.List;

import edu.miu.groupx.security.securityservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.security.model.UserAdmin;

@Service
@Transactional
public class AdminService 
{
	@Autowired
	private edu.miu.groupx.security.service.AdminRepository adminRepo;
	
	public List<UserAdmin> findAll()
	{
		return adminRepo.findAll();
	}
	
	public void addUser(UserAdmin user)
	{
		adminRepo.save(user);
	}
	
	public UserAdmin getUser(String id)
	{
		return adminRepo.findById(id).get();
	}
	
	public void delete(String id)
	{
		adminRepo.deleteById(id);
	}
	
	public void updateUserById(UserAdmin user, String id)
	{
		adminRepo.updateUserById(user, id);
	}
	
	public Boolean getApprovalForVendor(String id)
	{
		if(adminRepo.getApprovalForVendor(id) >= 1)
		{
			return true;
		}// if
		
		return false;
	}
	
	public void updateStatusProduct(String id, String status)
	{
		adminRepo.updateStatusProduct(id, status);
	}


	public List<UserAdmin> getPendingVendors() {
		return adminRepo.getPendingVendors();
	}

	public List<UserAdmin> getRejectedVendors() {
		return adminRepo.getRejectedVendors();
	}

	public List<UserAdmin> getApprovedVendors() {
		return adminRepo.getapprovedVendors();
	}

	public UserAdmin approveVendorById(String id) {
		UserAdmin ua = adminRepo.getOne(id);
		ua.setEnabled(1);
		return adminRepo.save(ua);
	}

	public UserAdmin rejectVendorById(String id) {
		UserAdmin ua = adminRepo.getOne(id);
		ua.setEnabled(3);
		return adminRepo.save(ua);
	}
}
