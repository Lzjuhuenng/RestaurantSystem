package com.lzjuhuenng.RestaurantSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lzjuhuenng.RestaurantSystem.dao.IFoodDao;
import com.lzjuhuenng.RestaurantSystem.dao.impl.FoodDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.entery.Food;

import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.service.IFoodService;
import com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService;

public  class FoodServiceImpl implements IFoodService {

	public FoodServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	List<Food> foodList = null;
	IFoodDao foodDao = new FoodDaoMysqlImpl();
	IFoodTypeService foodTypeService = new FoodTypeServiceImpl();
	Food food = null;
	/**
	 * �ṩ��ѯ��Ʒ�ķ���
	 */
	public List<Food> queryAllFoodFormDB(HttpServletRequest request){
		food = new Food();
		String foodname = request.getParameter("foodname");
		String id = request.getParameter("id");
		
		if(id != null && !"".equals(id)){
			food.setId(Integer.parseInt(id));
		}
		
		if(foodname != null && !"".equals(foodname)){
				food.setFood_name(foodname);
		}
		if(request.getParameter("foodtype") != null && !"".equals(request.getParameter("foodtype"))){
			food.setFood_type(Integer.parseInt(request.getParameter("foodtype")));
		}
		if(request.getParameter("minprice") != null && !"".equals(request.getParameter("minprice"))){
			food.setMin_price(Double.parseDouble(request.getParameter("minprice")));
		}
		if(request.getParameter("maxprice") != null && !"".equals(request.getParameter("maxprice"))){
			food.setMax_price(Double.parseDouble(request.getParameter("maxprice")));;
		}
		foodList = foodDao.queryFood(food);
		foodList.add(food);
		return foodList;
	}
	/**
	 * �����ݿ�����Ӳ�Ʒ��Ϣ
	 * @param request
	 * @return
	 */
	public boolean addFoodIntoDB(HttpServletRequest request){
		food = new Food();
		food.setFood_name(request.getParameter("foodname"));
		food.setFood_type(Integer.parseInt(request.getParameter("foodtype")));
		food.setPrice(Double.parseDouble(request.getParameter("price")));
		food.setFood_state(1);
		return foodDao.insertFood(food);
	}
	
	public boolean delFoodFromDB(HttpServletRequest request){
		food = new Food();
		food.setId(Integer.parseInt(request.getParameter("id")));
		return foodDao.deleteFood(food);
	}
	public boolean hiddenFoodFromDB(HttpServletRequest request){
		food = new Food();
		food.setId(Integer.parseInt(request.getParameter("id")));
		food = foodDao.queryFood(food).get(0);
		food.setFood_state(0);
		return foodDao.updateFood(food);
	}

	public boolean updateFoodState(HttpServletRequest request){
		food = new Food();
		food.setId(Integer.parseInt(request.getParameter("id")));
		food = foodDao.queryFood(food).get(0);
		if(food.getFood_state() == 2){
			food.setFood_state(1);
		}else{
			food.setFood_state(2);
		}
		return foodDao.updateFood(food);
	}
	
	public boolean updateFood(HttpServletRequest request){
		food = new Food();
		food.setId(Integer.parseInt(request.getParameter("id")));
		food.setFood_name(request.getParameter("foodname"));
		food.setFood_type(Integer.parseInt(request.getParameter("foodtype")));
		food.setPrice(Double.parseDouble(request.getParameter("price")));
		food.setFood_state(Integer.parseInt(request.getParameter("foodstate")));
		return foodDao.updateFood(food);
	}
	
	public Map<Integer, String> getFoodMap(){
		food = new Food();
		Map<Integer, String> foodMap = new HashMap<Integer,String>();
		foodList = foodDao.queryFood(food);
		for(Food food : foodList){
			foodMap.put(food.getId(),food.getFood_name());
		}
		
		return foodMap;
	}
	
	
	public Page<Food> queryFoodFormDBByPage(HttpServletRequest request){
		Page<Food> foodPage = new Page<Food>();
		String foodname = request.getParameter("foodname");
		String id = request.getParameter("id");
		
		food = new Food();
		if(id != null && !"".equals(id)){
			food.setId(Integer.parseInt(id));
		}
		
		if(foodname != null && !"".equals(foodname)){
				food.setFood_name(foodname);
		}
		if(request.getParameter("foodtype") != null && !"".equals(request.getParameter("foodtype"))){
			food.setFood_type(Integer.parseInt(request.getParameter("foodtype")));
		}
		if(request.getParameter("minprice") != null && !"".equals(request.getParameter("minprice"))){
			food.setMin_price(Double.parseDouble(request.getParameter("minprice")));
		}
		if(request.getParameter("maxprice") != null && !"".equals(request.getParameter("maxprice"))){
			food.setMax_price(Double.parseDouble(request.getParameter("maxprice")));;
		}
		if(request.getAttribute("state")!= null&&!"".equals(request.getAttribute("state"))){
			food.setFood_state((int) request.getAttribute("state"));
		}
		if(request.getParameter("pageNo")!=null &&! "".equals(request.getParameter("pageNo"))){
			foodPage.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		foodPage.setQueryAttr(food);
		foodPage = foodDao.queryFoodByPage(foodPage);
		foodPage.getResultList().add(food);
		return foodPage;
	}
	
}
