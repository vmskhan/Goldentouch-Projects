package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.entities.User;
import com.util.HibernateConfiguration;

@Repository("userDao")
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO{

	@Override
	public User findById(int uid) { 
		System.out.println("user-findbyid method called");
		return getByKey(uid);
		
	}

	@Override
	public void saveUser(User user) {
		System.out.println("user-saveuser method called");
		persist(user);
		
	}

	@Override
	public void deleteUserById(int uid) {
		System.out.println("user-deleteuserbyid method called");
		Criteria criteria =  createEntityCriteria();
        User user=(User)criteria.add(Restrictions.eq("uid", uid)).uniqueResult();
        delete(user);
		
	}

	@Override
	public List<User> findAllUsers() {
		System.out.println("user-findallusers method called");
		Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
        
	}

	@Override
	public User findUserByEmailid(String emailid) {
		System.out.println("user-finduserbyemailid method called");
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("emailid", emailid));  
        return (User) criteria.uniqueResult();
        
	}

	@Override
	public List<User> findUserByClaim(String claim) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("claim", claim));  
        return (List<User>) criteria.list();
	}

	
}
