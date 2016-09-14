package com.lzjuhuenng.RestaurantSystem.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lzjuhuenng.RestaurantSystem.dao.IOrderItemsDao;
import com.lzjuhuenng.RestaurantSystem.entery.FoodItem;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.util.DBUtil;

public class OrderItemsDaoMysqlImpl implements IOrderItemsDao {

	public OrderItemsDaoMysqlImpl() {
	}

	List<FoodItem> foodItemsList = null;
	FoodItem foodItem = null;
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;

	public List<FoodItem> queryOrderItems(FoodItem foodItem) {
		String sql = "select * from orderitem where 1=1";
		if(foodItem.getOrderId() > 0){
			sql += " and order_id ="+foodItem.getOrderId(); 
		}
		if(foodItem.getId()>0){
			sql += " and  id ="+foodItem.getId(); 
		}
		foodItemsList = new ArrayList<FoodItem>();
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				foodItem = new FoodItem();
				foodItem.setId(rs.getInt("id"));
				foodItem.setOrderId(rs.getInt("order_id"));
				foodItem.setFoodId(rs.getInt("food_id"));
				foodItem.setFoodState(rs.getInt("state"));
				foodItem.setPrice(rs.getDouble("price"));
				foodItem.setNum(rs.getInt("num"));
				foodItemsList.add(foodItem);
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
		return foodItemsList;
	}

	public boolean insertOrderItem(FoodItem foodItem) {
		String sql = "insert into orderitem (order_id,food_id,num,price,state) values (" + foodItem.getOrderId() + ","
				+ foodItem.getFoodId() + "," + foodItem.getNum() + "," + foodItem.getPrice() + ","
				+ foodItem.getFoodState() + ")";
		System.out.println(sql);
		return executeUpdate(sql);
	}

	public boolean updateOrderItem(FoodItem foodItem) {
		String sql = "update orderitem set order_id=" + foodItem.getOrderId() + ",food_id=" + foodItem.getFoodId()
				+ ",num=" + foodItem.getNum() + ",price=" + foodItem.getPrice() + ",state=" + foodItem.getFoodState()
				+ " where id=" + foodItem.getId();
		System.out.println(sql);
		return executeUpdate(sql);
	}
	
	public boolean deleteOrderItem(FoodItem foodItem){
		String sql = "delete from orderitem where id = "+foodItem.getId();
		System.out.println(sql);
		return executeUpdate(sql);
	}

	public boolean executeUpdate(String sql) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public Page<FoodItem> queryOrderItemsByPage(Page<FoodItem> foodItemPage) {
		Page<FoodItem> rsFoodItemPage = new Page<FoodItem>();
		foodItemsList = new ArrayList<FoodItem>();
		String sql = "select * from orderitem where 1=1";
		String sql2 = "select * from orderitem where 1=1";
		if(foodItemPage.getQueryAttr().getOrderId() > 0){
			sql += " and order_id ="+foodItemPage.getQueryAttr().getOrderId(); 
			sql2 += " and order_id ="+foodItemPage.getQueryAttr().getOrderId(); 
		}
		if(foodItemPage.getQueryAttr().getId()>0){
			sql += " and  id ="+foodItemPage.getQueryAttr().getId(); 
			sql2 += " and  id ="+foodItemPage.getQueryAttr().getId(); 
		}
		sql += " limit " + foodItemPage.getStartRowNo() + ", " + foodItemPage.getPageSize();
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				foodItem = new FoodItem();
				foodItem.setId(rs.getInt("id"));
				foodItem.setOrderId(rs.getInt("order_id"));
				foodItem.setFoodId(rs.getInt("food_id"));
				foodItem.setFoodState(rs.getInt("state"));
				foodItem.setPrice(rs.getDouble("price"));
				foodItem.setNum(rs.getInt("num"));
				foodItemsList.add(foodItem);
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
		rsFoodItemPage.setTotalRows(getTotleRows(sql2));
		rsFoodItemPage.setPageNo(foodItemPage.getPageNo());
		rsFoodItemPage.setResultList(foodItemsList);
		return rsFoodItemPage;
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

}
