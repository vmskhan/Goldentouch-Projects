package com.dao;

import java.util.List;

import com.entities.Order;

public interface OrderDAO {
	Order findById(int oid);
	void saveOrder(Order order);
	void deleteOrder(Order order);
	List<Order> findAllOrders();
	List<Order> findOrdersByUid(int uid);
}
