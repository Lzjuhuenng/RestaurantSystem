package com.lzjuhuenng.RestaurantSystem.dao;

import java.util.List;

import com.lzjuhuenng.RestaurantSystem.entery.Order;
import com.lzjuhuenng.RestaurantSystem.entery.Page;

public interface IOrderDao {
	public List<Order> queryOrder(Order order);
	public int getOrderIdByOrderNo(String orderNo);
	public boolean insertOrder(Order order);
	public boolean deleteOrder(Order order);
	public boolean updateOrder(Order order);
	public boolean executeUpdate(String sql);
	public Page<Order> queryOrderByPage(Page<Order> orderPage);
}
