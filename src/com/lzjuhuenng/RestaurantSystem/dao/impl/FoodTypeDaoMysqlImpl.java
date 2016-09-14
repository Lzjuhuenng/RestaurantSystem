package com.lzjuhuenng.RestaurantSystem.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lzjuhuenng.RestaurantSystem.dao.IFoodTypeDao;
import com.lzjuhuenng.RestaurantSystem.entery.FoodType;
import com.lzjuhuenng.RestaurantSystem.util.DBUtil;


public class FoodTypeDaoMysqlImpl implements IFoodTypeDao{

	public FoodTypeDaoMysqlImpl() {
	 }
	private List<FoodType> foodtypeList = null;
	private Connection conn= null;
	private Statement stat = null;
	private ResultSet rs = null;
	private FoodType foodtype = null;
	/**
	 * �����ݿ��в�ѯ������Ʒ����ķ���
	 * @return
	 */
	public List<FoodType> queryFoodType(){
		String sql="select * from food_type"; 
		foodtypeList = new ArrayList<FoodType>();
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				foodtype = new FoodType();
				foodtype.setId(rs.getInt("id"));
				foodtype.setType(rs.getString("type"));
				foodtype.setState(rs.getInt("food_type_state"));
				foodtypeList.add(foodtype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return foodtypeList;
	}
	/**
	 * ����Idֵ���FoodType��ʵ��
	 * @param id
	 * @return
	 */
	public FoodType queryFoodTypeById(int id){
		String sql="select * from food_type where id = "+id; 
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()){
				foodtype = new FoodType();
				foodtype.setId(rs.getInt("id"));
				foodtype.setType(rs.getString("type"));
				foodtype.setState(rs.getInt("food_type_state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return foodtype;
	}
	
	
	
	/**
	 * �����ݿ��в�����Ϣ����
	 * @param foodtype
	 * @return
	 */
	public boolean insertFoodType(FoodType foodtype){
		String sql = "insert into food_type (type,food_type_state) values ('"+foodtype.getType()+"', "+foodtype.getState()+")";
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			int effectedRows = stat.executeUpdate(sql);
			if(effectedRows > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * ɾ�����ݿ�����Ϣ�ķ���
	 * @param id
	 * @return
	 */
	public boolean delFoodType(int  id){
		String sql = "delete from  food_type where id = "+id;
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			int effectedRows = stat.executeUpdate(sql);
			if(effectedRows > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * �������ݿ��е��ֶ���Ϣ
	 * @param foodtype
	 * @return
	 */
	public boolean updateFoodType(FoodType foodtype){
		String sql = "update food_type set type='"+foodtype.getType() +"',food_type_state ="+foodtype.getState()+" where id = "+foodtype.getId();
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			int effectedRows = stat.executeUpdate(sql);
			if(effectedRows > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
