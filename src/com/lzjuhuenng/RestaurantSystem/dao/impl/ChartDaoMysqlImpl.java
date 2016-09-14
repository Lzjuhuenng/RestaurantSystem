package com.lzjuhuenng.RestaurantSystem.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.lzjuhuenng.RestaurantSystem.dao.IChartDao;
import com.lzjuhuenng.RestaurantSystem.entery.ChartParam;
import com.lzjuhuenng.RestaurantSystem.util.DBUtil;

public class ChartDaoMysqlImpl implements IChartDao{

	public ChartDaoMysqlImpl() {
		// TODO Auto-generated constructor stub
	}
	private Connection conn = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private Map<String, String> map;
	
	
	public Map<String, String> getDayIncomeMap(ChartParam chartParam){
		 map = new HashMap<String,String>();
		String sql ="SELECT DATE_FORMAT( data, '%Y-%m-%d') data,SUM(totlemoney) totlemoney "
				+ "FROM (SELECT id,order_no,data,account_id,table_id,totlemoney "
				+ "FROM orders LEFT JOIN (select order_id,SUM(num*price)as totlemoney "
				+ "FROM orderitem GROUP BY order_id) item ON id=order_id) forms WHERE DATE_FORMAT( data, '%Y-%m-%d')>'"
				+ chartParam.getParam1()+"' AND DATE_FORMAT( data, '%Y-%m-%d')<'"
						+ chartParam.getParam2()+"'";
		System.out.println(sql);
		try{
			conn =DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()){
				map.put(rs.getString("data"),String.valueOf(rs.getDouble("totlemoney")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	public Map<String, String> getMonthIncomeMap(ChartParam chartParam){
	 map = new HashMap<String,String>();
		String sql ="SELECT DATE_FORMAT( data, '%Y-%m') data,SUM(totlemoney) as totlemoney "
				+ "FROM (SELECT id,order_no,data,account_id,table_id,totlemoney "
				+ "FROM orders LEFT JOIN (select order_id,SUM(num*price)as totlemoney "
				+ "FROM orderitem GROUP BY order_id) item ON id=order_id) forms WHERE DATE_FORMAT( data, '%Y-%m')>'"
				+ chartParam.getParam1()+"' AND DATE_FORMAT( data, '%Y-%m')<'"
						+ chartParam.getParam2()+"'";
				try{
					rs = executeQuery(sql);
					if(rs.next()){
						map.put(rs.getString("data"),String.valueOf(rs.getDouble("totlemoney")));
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					try {
						DBUtil.closeResultSet(rs);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return map;
	}
	public Map<String, String> getYearIncomeMap(ChartParam chartParam){
		 map = new HashMap<String,String>();
		String sql ="SELECT DATE_FORMAT( data, '%Y') data,SUM(totlemoney) totlemoney"
				+ "FROM (SELECT id,order_no,data,account_id,table_id,totlemoney "
				+ "FROM orders LEFT JOIN (select order_id,SUM(num*price)as totlemoney "
				+ "FROM orderitem GROUP BY order_id) item ON id=order_id) forms WHERE DATE_FORMAT( data, '%Y')>'"
				+ chartParam.getParam1()+"' AND DATE_FORMAT( data, '%Y')<'"
						+ chartParam.getParam2()+"'";
		System.out.println(sql);
		try{
			rs = executeQuery(sql);
			if(rs.next()){
				
				map.put(rs.getString("data"),String.valueOf(rs.getDouble("totlemoney")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public ResultSet executeQuery(String sql){
		try{
			conn =DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			return rs;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
