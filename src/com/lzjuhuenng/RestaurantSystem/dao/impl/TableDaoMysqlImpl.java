package com.lzjuhuenng.RestaurantSystem.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lzjuhuenng.RestaurantSystem.dao.ITableDao;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.entery.Table;
import com.lzjuhuenng.RestaurantSystem.util.DBUtil;

public class TableDaoMysqlImpl implements ITableDao {

	public TableDaoMysqlImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private List<Table> tableList = null;
	private Connection conn = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private Table table = null;

	public List<Table> queryTable(Table table) {
		String sql = "select * from `tables` where 1=1";
		if(table.getId()>0){
			sql += " and id = "+table.getId();
		}
		if (table.getTable_no() != null) {
			sql +=  " and table_no like '%" + table.getTable_no() + "%'";
		}
		if (table.getMincapacity() > 0) {
			sql +=  " and capacity >=" + table.getMincapacity();
		}
		if (table.getMaxcapacity() > 0) {
			sql +=  " and capacity <=" + table.getMaxcapacity();
		}
		System.out.println(sql);
		try {
			// ����Ԥ�Ȳ�ѯ�ܲ������������¼
			tableList = new ArrayList<Table>();
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				table = new Table();
				table.setId(rs.getInt("id"));
				table.setTable_no(rs.getString("table_no"));
				table.setCapacity(rs.getInt("capacity"));
				table.setState(rs.getInt("state"));
				tableList.add(table);
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
		return tableList;
	}
	/**
	 * �����ݿ��в���food��Ϣ
	 * @param food
	 * @return
	 */
	public boolean insertTable(Table table) {
		String sql = "insert into `tables` (table_no,capacity,state) values ('"+table.getTable_no()+ "',"
				+ table.getCapacity()+ "," + table.getState()+")";
		System.out.println(sql);
		return this.executeUpdateTable(sql);
	}
	/**
	 * ɾ�����ݿ���food��Ϣ
	 * @param food
	 * @return
	 */
	public boolean deleteTable(Table table) {
		String sql = "delete from `tables` where  id=" + table.getId();
		System.out.println(sql);
		return this.executeUpdateTable(sql);
	}
	/**
	 * �������ݿ���food��Ϣ
	 * @param food
	 * @return
	 */
	public boolean updateTable(Table table) {
		String sql = "update `tables` set table_no ='" + table.getTable_no() + "',capacity =" + table.getCapacity() + ", state="
				+ table.getState() +  " where id = "+ table.getId();
		System.out.println(sql);
		return this.executeUpdateTable(sql);
	}
	/**
	 * �ṩ�������������ݿⷽ��
	 * @param sql
	 * @return
	 */
	public boolean executeUpdateTable(String sql) {
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
	
	
	
	public Page<Table> queryTableByPage(Page<Table> tablePage) {
		Page<Table> rsTablePage = new Page<Table>();
		String sql = "select * from `tables` where 1=1";
		String sql2 = "select count(*) as rows  from `tables` where 1=1";
		if(tablePage.getQueryAttr().getId()>0){
			sql2 += " and id = "+tablePage.getQueryAttr().getId();
			sql += " and id = "+tablePage.getQueryAttr().getId();
		}
		if (tablePage.getQueryAttr().getTable_no() != null) {
			sql2 +=  " and table_no like '%" + tablePage.getQueryAttr().getTable_no() + "%'";
			sql +=  " and table_no like '%" + tablePage.getQueryAttr().getTable_no() + "%'";
		}
		if (tablePage.getQueryAttr().getMincapacity() > 0) {
			sql2 +=  " and capacity >=" + tablePage.getQueryAttr().getMincapacity();
			sql +=  " and capacity >=" + tablePage.getQueryAttr().getMincapacity();
		}
		if (tablePage.getQueryAttr().getMaxcapacity() > 0) {
			sql2 +=  " and capacity <=" + tablePage.getQueryAttr().getMaxcapacity();
			sql +=  " and capacity <=" + tablePage.getQueryAttr().getMaxcapacity();
		}
		sql += " limit " + tablePage.getStartRowNo() + ", " +tablePage.getPageSize();
		System.out.println(sql);
		try {
			// ����Ԥ�Ȳ�ѯ�ܲ������������¼
			tableList = new ArrayList<Table>(15);
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				table = new Table();
				table.setId(rs.getInt("id"));
				table.setTable_no(rs.getString("table_no"));
				table.setCapacity(rs.getInt("capacity"));
				table.setState(rs.getInt("state"));
				tableList.add(table);
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
		rsTablePage.setTotalRows(getTotleRows(sql2));
		rsTablePage.setPageNo(tablePage.getPageNo());
		rsTablePage.setResultList(tableList);
		return rsTablePage;
	}
	private  int getTotleRows(String sql){
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
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
