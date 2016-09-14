package com.lzjuhuenng.RestaurantSystem.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lzjuhuenng.RestaurantSystem.entery.FoodItem;
import com.lzjuhuenng.RestaurantSystem.entery.Order;
import com.lzjuhuenng.RestaurantSystem.entery.Page;

public interface IOredrService {
	public int   generateOrder(HttpServletRequest request);
	public boolean deleteOrderFormDB(HttpServletRequest request);
	public void addItem(HttpServletRequest request);
	public void submitOrder(HttpServletRequest request);
	public List<Order> queryOrderFormDB(HttpServletRequest request);
	public  boolean unsubscribeOrder(HttpServletRequest request);
	public List<FoodItem> getOrderDetails(HttpServletRequest request);
	public boolean settleAccounts(HttpServletRequest request);
	public boolean orderConfirm(HttpServletRequest request);
	public boolean operateFoodItemState(HttpServletRequest request);
	public boolean unsubscribeFoodItem(HttpServletRequest request);
	public Page<Order> queryOrderFormDBByPage(HttpServletRequest request);
}
