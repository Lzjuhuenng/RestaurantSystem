package com.lzjuhuenng.RestaurantSystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lzjuhuenng.RestaurantSystem.dao.IFoodDao;
import com.lzjuhuenng.RestaurantSystem.dao.IFoodTypeDao;
import com.lzjuhuenng.RestaurantSystem.dao.impl.FoodDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.dao.impl.FoodTypeDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.entery.FoodType;
import com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService;

public class FoodTypeServiceImpl implements IFoodTypeService {

	public FoodTypeServiceImpl() {
	}
	IFoodTypeDao foodtypeDao = new FoodTypeDaoMysqlImpl();
	IFoodDao foodDao = new FoodDaoMysqlImpl();
	public List<FoodType> getAllFoodType(){
		List<FoodType> foodtypeList = null;
		foodtypeList = foodtypeDao.queryFoodType();
		return foodtypeList;
	}
	public boolean addFoodTypeToDB(String type){
		FoodType foodtype = new FoodType();
		foodtype.setType(type);
		foodtype.setState(0);
		return foodtypeDao.insertFoodType(foodtype);
	}
	
	public boolean updateFoodTypeState(int id){
		 FoodType foodtype = foodtypeDao.queryFoodTypeById(id);
		 if(foodtype.getState() == 1){
			 foodtype.setState(2);
		 }else{
			 foodtype.setState(1);
		 }
		 return foodtypeDao.updateFoodType(foodtype)&&foodDao.updateFoodsStateByType(foodtype);
	}
	
	public boolean delFoodTypeFromDB(int id){
		return foodtypeDao.delFoodType(id);
	}
	public Map<Integer, String> getAllFoodTypeMap(){
		Map<Integer,String> foodtypeMap = new HashMap<Integer,String>();
		List<FoodType> foodTypeList =this.getAllFoodType();
		for(FoodType foodtype : foodTypeList){
			foodtypeMap.put( foodtype.getId(),foodtype.getType());
		}
		return foodtypeMap;
	}
}
