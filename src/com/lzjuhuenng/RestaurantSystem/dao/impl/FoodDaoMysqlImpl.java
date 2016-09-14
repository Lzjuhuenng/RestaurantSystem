package com.lzjuhuenng.RestaurantSystem.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lzjuhuenng.RestaurantSystem.dao.IFoodDao;
import com.lzjuhuenng.RestaurantSystem.entery.Food;
import com.lzjuhuenng.RestaurantSystem.entery.FoodType;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.util.DBUtil;

public class FoodDaoMysqlImpl implements IFoodDao{

	public FoodDaoMysqlImpl() {
	}

	private List<Food> foodList = null;
	private Connection conn = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private Food food = null;
	public List<Food> queryFood(Food food) {
		String sql = "select * from foods where 1=1";
		if(food.getId()>0){
			sql += " and id = "+food.getId();
		}
		if (food.getFood_name() != null) {
			sql +=  " and food_name like '%" + food.getFood_name() + "%'";
		}
		if (food.getFood_type() != 0) {
			sql +=  " and  food_type = " + food.getFood_type();
		}
		if (food.getMin_price() > 0) {
			sql +=  " and price >=" + food.getMin_price();
		}
		if (food.getMax_price() > 0) {
			sql +=  " and price <=" + food.getMax_price();
		}
		System.out.println(sql);
		try {
			// ����Ԥ�Ȳ�ѯ�ܲ������������¼
			foodList = new ArrayList<Food>();
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				food = new Food();
				food.setId(rs.getInt("id"));
				food.setFood_name(rs.getString("food_name"));
				food.setFood_type(rs.getInt("food_type"));
				food.setPrice(rs.getDouble("price"));
				food.setFood_state(rs.getInt("food_state"));
				foodList.add(food);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return foodList;
	}
	/**
	 * �����ݿ��в���food��Ϣ
	 * @param food
	 * @return
	 */
	public boolean insertFood(Food food) {
		String sql = "insert into foods (food_name,food_type,price,food_state) values ('" + food.getFood_name() + "',"
				+ food.getFood_type() + "," + food.getPrice() + "," + food.getFood_state()+")";
		System.out.println(sql);
		return this.executeUpdateFood(sql);
	}
	/**
	 * ɾ�����ݿ���food��Ϣ
	 * @param food
	 * @return
	 */
	public boolean deleteFood(Food food) {
		String sql = "delete form foods  where  id=" + food.getId();
		return this.executeUpdateFood(sql);
	}
	/**
	 * �������ݿ���food��Ϣ
	 * @param food
	 * @return
	 */
	public boolean updateFood(Food food) {
		String sql = "update foods set food_name='" + food.getFood_name() + "',food_type =" + food.getFood_type() + ", price="
				+ food.getPrice() + ", food_state =" + food.getFood_state()+ " where id = "+food.getId();
		System.out.println(sql);
		return this.executeUpdateFood(sql);
	}
	/**
	 * �ṩ�������������ݿⷽ��
	 * @param sql
	 * @return
	 */
	public boolean executeUpdateFood(String sql) {
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			int effectedRows = stat.executeUpdate(sql);
			if (effectedRows > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
 				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public Page<Food> queryFoodByPage(Page<Food> foodPage){
		Page<Food> rsFoodPage= new Page<Food>();
		foodList = new ArrayList<Food>(15);
		String sql = "select * from foods where 1=1";
		String sql2 = "select  count(*)  as rows from foods where 1=1";
		if(foodPage.getQueryAttr().getId()>0){
			sql += " and id = "+foodPage.getQueryAttr().getId();
			sql2 += " and id = "+foodPage.getQueryAttr().getId();
		}
		if (foodPage.getQueryAttr().getFood_name() != null) {
			sql +=  " and food_name like '%" + foodPage.getQueryAttr().getFood_name() + "%'";
			sql2 +=  " and food_name like '%" + foodPage.getQueryAttr().getFood_name() + "%'";
		}
		if (foodPage.getQueryAttr().getFood_type() != 0) {
			sql +=  " and  food_type = " + foodPage.getQueryAttr().getFood_type();
			sql2 +=  " and  food_type = " + foodPage.getQueryAttr().getFood_type();
		}
		if (foodPage.getQueryAttr().getFood_state() != 0) {
			sql +=  " and  food_state = " + foodPage.getQueryAttr().getFood_state();
			sql2 +=  " and  food_state = " + foodPage.getQueryAttr().getFood_state();
		}
		if (foodPage.getQueryAttr().getMin_price() > 0) {
			sql +=  " and price >=" + foodPage.getQueryAttr().getMin_price();
			sql2 +=  " and price >=" + foodPage.getQueryAttr().getMin_price();
		}
		if (foodPage.getQueryAttr().getMax_price() > 0) {
			sql +=  " and price <=" + foodPage.getQueryAttr().getMax_price();
			sql2 +=  " and price <=" + foodPage.getQueryAttr().getMax_price();
		}
		sql += " limit " + foodPage.getStartRowNo() + ", " + foodPage.getPageSize();
		System.out.println(sql);
		try {
			// ����Ԥ�Ȳ�ѯ�ܲ������������¼
			foodList = new ArrayList<Food>();
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				food = new Food();
				food.setId(rs.getInt("id"));
				food.setFood_name(rs.getString("food_name"));
				food.setFood_type(rs.getInt("food_type"));
				food.setPrice(rs.getDouble("price"));
				food.setFood_state(rs.getInt("food_state"));
				foodList.add(food);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		rsFoodPage.setTotalRows(getTotleRows(sql2));
		rsFoodPage.setPageNo(foodPage.getPageNo());
		rsFoodPage.setResultList(foodList);
		return rsFoodPage;
	}
	private int getTotleRows(String sql){
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			System.out.println(sql);
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("rows");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public boolean updateFoodsStateByType(FoodType foodType){
		String sql = "UPDATE foods SET food_state = "+foodType.getState()+ " WHERE food_type ="+foodType.getId() ;
		System.out.println(sql);
		return executeUpdateFood(sql);
	}
	
}
