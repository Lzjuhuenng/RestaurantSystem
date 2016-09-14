package com.lzjuhuenng.RestaurantSystem.service;

import java.util.List;
import java.util.Map;

import com.lzjuhuenng.RestaurantSystem.entery.FoodType;

public interface IFoodTypeService {
	public List<FoodType> getAllFoodType();
	public boolean addFoodTypeToDB(String type);
	public boolean updateFoodTypeState(int id);
	public boolean delFoodTypeFromDB(int id);
	public Map<Integer, String> getAllFoodTypeMap();
}
