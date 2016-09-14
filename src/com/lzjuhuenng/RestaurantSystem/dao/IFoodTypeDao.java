package com.lzjuhuenng.RestaurantSystem.dao;

import java.util.List;

import com.lzjuhuenng.RestaurantSystem.entery.FoodType;

public interface IFoodTypeDao {
	public List<FoodType> queryFoodType();
	public FoodType queryFoodTypeById(int id);
	public boolean insertFoodType(FoodType foodtype);
	public boolean delFoodType(int id);
	public boolean updateFoodType(FoodType foodtype);
}
