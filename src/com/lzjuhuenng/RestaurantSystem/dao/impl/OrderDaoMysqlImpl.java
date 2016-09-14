package com.lzjuhuenng.RestaurantSystem.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lzjuhuenng.RestaurantSystem.dao.IOrderDao;
import com.lzjuhuenng.RestaurantSystem.entery.Order;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.util.DBUtil;

public class OrderDaoMysqlImpl implements IOrderDao {

	public OrderDaoMysqlImpl() {
		// TODO Auto-generated constructor stub
	}
	private Connection conn = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private Order order = null;
	private List<Order> orderList = null; 
	public List<Order> queryOrder(Order order){
		orderList = new ArrayList<Order>();
		String sql = "SELECT id,order_no,data,account_id,table_id,state,totlemoney FROM orders LEFT JOIN (select order_id,SUM(num*price)as totlemoney FROM orderitem GROUP BY order_id) item ON id=order_id where 1=1 ";
		if(order.getState()>0){
			sql += " and state ="+order.getState();
		}
		if(order.getId()>0){
			sql += " and id ="+order.getId();
		}
		System.out.println(sql);
		try{
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				order = new Order();
				System.out.println(order);
				order.setId(rs.getInt("id"));
				order.setOrderNO(rs.getString("order_no"));
				order.setData(rs.getString("data"));
				order.setAccountId(rs.getInt("account_id"));
				order.setTableId(rs.getInt("table_id"));
				order.setState(rs.getInt("state"));
				order.setTotleMoney(rs.getDouble("totlemoney"));
				orderList.add(order);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderList;
	}
	public int getOrderIdByOrderNo(String orderNo){
		String sql = "select * from orders where order_no='"+orderNo+"'";
		try{
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			System.out.println(sql);
			rs = stat.executeQuery(sql);
			if(rs.next()){
				return rs.getInt("id");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return  -1;
	}
	public boolean insertOrder(Order order){
		String  sql = "insert into orders (order_no,data,account_id,table_id,state)"
				+ "values('"+order.getOrderNO()+"',NOW(),"+order.getAccountId()+","+order.getTableId()+","+order.getState()+")";
		System.out.println(sql);
		return executeUpdate(sql);
	}
	
	public boolean deleteOrder(Order order){
		String  sql = "delete from  orders where id = "+order.getId();
		System.out.println(sql);
		return executeUpdate(sql);
	}
	
	public boolean updateOrder(Order order){
		String  sql = "update orders set order_no ='"+order.getOrderNO()+"',data='"+order.getData()+"',account_id="+order.getAccountId()+","
				+ "table_id="+order.getTableId()+",state="+order.getState()+" where id = " + order.getId();
		System.out.println(sql);
		return executeUpdate(sql);
	}
	
	
	
	
	
	public boolean executeUpdate(String sql){
		try{
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			int effectedRows = stat.executeUpdate(sql);
			if(effectedRows > 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Page<Order> queryOrderByPage(Page<Order> orderPage){
		Page<Order> rsOrderPage = new Page<Order>();
		orderList = new ArrayList<Order>();
		String sql = "SELECT id,order_no,data,account_id,table_id,state,totlemoney FROM orders LEFT JOIN (select order_id,SUM(num*price)as totlemoney FROM orderitem GROUP BY order_id) item ON id=order_id where 1=1 ";
		String sql2 = "SELECT count(*) as rows  FROM orders LEFT JOIN (select order_id,SUM(num*price)as totlemoney FROM orderitem GROUP BY order_id) item ON id=order_id where 1=1 ";
		if(orderPage.getQueryAttr().getState()>0){
			sql += " and state ="+orderPage.getQueryAttr().getState();
		}
		if(orderPage.getQueryAttr().getId()>0){
			sql += " and id ="+orderPage.getQueryAttr().getId();
		}
		sql += " ORDER BY id ";
		sql += " limit " + orderPage.getStartRowNo() + ", " + orderPage.getPageSize();
		System.out.println(sql);
		try{
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				order = new Order();
				System.out.println(order);
				order.setId(rs.getInt("id"));
				order.setOrderNO(rs.getString("order_no"));
				order.setData(rs.getString("data"));
				order.setAccountId(rs.getInt("account_id"));
				order.setTableId(rs.getInt("table_id"));
				order.setState(rs.getInt("state"));
				order.setTotleMoney(rs.getDouble("totlemoney"));
				orderList.add(order);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatment(stat);
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		rsOrderPage.setTotalRows(getTotleRows(sql2));
		rsOrderPage.setPageNo(orderPage.getPageNo());
		rsOrderPage.setResultList(orderList);
		return rsOrderPage;
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
