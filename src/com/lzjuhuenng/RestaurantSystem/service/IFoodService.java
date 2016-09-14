package com.lzjuhuenng.RestaurantSystem.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lzjuhuenng.RestaurantSystem.entery.Food;
import com.lzjuhuenng.RestaurantSystem.entery.Page;

public interface IFoodService {
	public List<Food> queryAllFoodFormDB(HttpServletRequest request);
	public boolean addFoodIntoDB(HttpServletRequest request);
	public boolean delFoodFromDB(HttpServletRequest request);
	public boolean updateFood(HttpServletRequest request);
	public boolean hiddenFoodFromDB(HttpServletRequest request);
	public boolean updateFoodState(HttpServletRequest request);
	public Map<Integer, String> getFoodMap();
	public Page<Food> queryFoodFormDBByPage(HttpServletRequest request);
}
