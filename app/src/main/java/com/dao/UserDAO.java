package com.dao;

import java.util.List;

import com.entities.User;

public interface UserDAO {
	
	User findById(int uid);
	 
    void saveUser(User user);
     
    void deleteUserById(int uid);
     
    List<User> findAllUsers();
 
    User findUserByEmailid(String emailid);

	List<User> findUserByClaim(String claim);
    

}
