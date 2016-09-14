package com.lzjuhuenng.RestaurantSystem.dao;

import java.util.List;

import com.lzjuhuenng.RestaurantSystem.entery.Food;
import com.lzjuhuenng.RestaurantSystem.entery.FoodType;
import com.lzjuhuenng.RestaurantSystem.entery.Page;

public interface IFoodDao {
	public List<Food> queryFood(Food food);
	public boolean insertFood(Food food);
	public boolean deleteFood(Food food);
	public boolean updateFood(Food food);
	public boolean executeUpdateFood(String sql);
	public Page<Food> queryFoodByPage(Page<Food> foodPage);
	public boolean updateFoodsStateByType(FoodType foodType);
}
