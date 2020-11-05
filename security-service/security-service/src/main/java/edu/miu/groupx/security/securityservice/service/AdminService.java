package edu.miu.groupx.security.securityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.security.securityservice.repository.AdminRepository;
import edu.miu.groupx.security.securityservice.model.UserAdmin;
import edu.miu.groupx.security.securityservice.model.UserStatus;

@Service
@Transactional
public class AdminService 
{
	@Autowired
	private AdminRepository adminRepo;
	
	public List<UserAdmin> findAll()
	{
		return adminRepo.findAll();
	}
	
	public void addUser(UserAdmin user)
	{
		user.setUserStatus(UserStatus.PENDING);
		adminRepo.save(user);
	}
	
	public UserAdmin getUser(Long id)
	{
		return adminRepo.findById(id).get();
	}
	
	public void delete(Long id)
	{
		adminRepo.deleteById(id);
	}
	
	public void updateUserById(UserAdmin user, Long id)
	{
		adminRepo.updateUserById(user, id);
	}
	
	public Boolean getApprovalForVendor(Long id)
	{
		if(adminRepo.getApprovalForVendor(id) >= 1)
		{
			return true;
		}// if
		
		return false;
	}
	
	public void updateStatusProduct(Long id, String status)
	{
		adminRepo.updateStatusProduct(id, status);
	}
	
	public UserAdmin approveUser(UserAdmin user) 
	{
		UserAdmin fetchedUser= adminRepo.findById(user.getId()).get();
		fetchedUser.setUserStatus(UserStatus.APPROVED);
		adminRepo.save(fetchedUser);
		
		return fetchedUser;
	}

	public UserAdmin  rejectUser(UserAdmin user) 
	{
		UserAdmin fetchedUser= adminRepo.findById(user.getId()).get();
		fetchedUser.setUserStatus(UserStatus.REJECTED);
		adminRepo.save(fetchedUser);
		
		return fetchedUser; 
	}
	
}
