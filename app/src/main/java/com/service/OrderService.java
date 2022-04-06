package com.service;

import java.util.List;

import com.entities.Order;

public interface OrderService {
	Order findById(int oid);
	void saveOrder(Order order);
	void deleteOrder(Order order);
	void updateOrder(Order order);
	List<Order> findAllOrders();
	List<Order> findOrdersByUid(int uid);
}
