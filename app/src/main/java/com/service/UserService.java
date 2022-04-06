package com.service;

import java.util.List;

import com.entities.User;

public interface UserService {
	
	User findById(int uid);
    
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserById(int uid);
 
    List<User> findAllUsers(); 
    List<User> findAllParticiapnts(String claim); 
    
    User findUserByEmailid(String emailid);
 
}
