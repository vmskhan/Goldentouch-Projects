package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.dao.UserDAO;
import com.entities.User;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO dao;
	
	
	@Override
	public User findById(int uid) {
		System.out.println("userservice-findbyid method called");
		return dao.findById(uid);
	
	}

	
	@Override
	public void saveUser(User user) {
		System.out.println("userservice-saveuser method called");
		dao.saveUser(user);
		
	}

	
	@Override
	public void updateUser(User user) {
		System.out.println("userservice-updateuser method called");
		User entity = dao.findById(user.getUid());
		if(entity!=null) {
			entity.setEmailid(user.getEmailid());
			entity.setName(user.getName());
			entity.setPassword(user.getPassword());
			entity.setClaim(user.getClaim());
		}
	
	}

	
	@Override
	public void deleteUserById(int uid) {
		System.out.println("userservice-deleteuserbyid method called");
		dao.deleteUserById(uid);
		
	}

	
	@Override
	public List<User> findAllUsers() {
		System.out.println("userservice-findallusers method called");
		return dao.findAllUsers();
	
	}

	
	@Override
	public User findUserByEmailid(String emailid) {
		System.out.println("userservice-finduserbyemailid method called");
		return dao.findUserByEmailid(emailid);
	
	}


	@Override
	public List<User> findAllParticiapnts(String claim) {
		// TODO Auto-generated method stub
		return dao.findUserByClaim(claim);
	}

}
