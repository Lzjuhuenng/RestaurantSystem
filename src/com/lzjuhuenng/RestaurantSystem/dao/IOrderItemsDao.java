package com.lzjuhuenng.RestaurantSystem.dao;

import java.util.List;

import com.lzjuhuenng.RestaurantSystem.entery.FoodItem;

public interface IOrderItemsDao {
	public List<FoodItem> queryOrderItems(FoodItem foodItem) ;
	public boolean insertOrderItem(FoodItem foodItem) ;
	public boolean updateOrderItem(FoodItem foodItem) ;
	public boolean deleteOrderItem(FoodItem foodItem);
	public boolean executeUpdate(String sql);
}
