package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.entities.Answer;
import com.entities.Order;

@Repository
public class OrderDAOImpl extends AbstractDAO<Integer, Order> implements OrderDAO {

	@Override
	public Order findById(int oid) {
		System.out.println("order-findbyid method called");
		return findById(oid);
	}

	@Override
	public void saveOrder(Order order) {
		System.out.println("order-saveorder method called");
		persist(order);
	}

	@Override
	public void deleteOrder(Order order) {
		System.out.println("order-deleteorder method called");
		delete(order);
	}

	@Override
	public List<Order> findAllOrders() {
		System.out.println("order-findallorders method called");
		Criteria criteria = createEntityCriteria();
        return (List<Order>) criteria.list();
	}

	@Override
	public List<Order> findOrdersByUid(int uid) {
		System.out.println("order-findordersbyuid method called");
		Criteria criteria = createEntityCriteria();
		return (List<Order>) criteria.add(Restrictions.eq("uid", uid)).list();
	}

}
