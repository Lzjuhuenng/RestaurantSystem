package com.lzjuhuenng.RestaurantSystem.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lzjuhuenng.RestaurantSystem.dao.IAccountDao;
import com.lzjuhuenng.RestaurantSystem.entery.Account;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.util.DBUtil;


public class AccountDaoMysqlImpl implements IAccountDao {

	public AccountDaoMysqlImpl() {
		// TODO Auto-generated constructor stub
	}

	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	Account acc = null;
	List<Account> accountList = null;

	/**
	 * ͨ��account��ȡ�û��ķ�����ͬʱʶ���û���ɫ�Ƿ��Ѿ���ְ role 4����������Ա role 3������Ա role 2������Ա role
	 * 1����ʦ role 0: ��ְ
	 * 
	 * @param account
	 * @return ������ô�����᷵��һ��account��ʵ���࣬����ͨ���鿴ʵ�����е�����ȷ���Ƿ��ѯ�ɹ���
	 *         ���������ֱ�ӷ���null������Ҫ����l���β�ѯ
	 */
	public Account getAccount(String account) {
		String sql = "select * from account where role <>0  and  account ='" + account + "'";
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			acc = new Account();
			if (rs.next()) {
				acc.setId(rs.getInt("id"));
				acc.setAccount(rs.getString("account"));
				acc.setPassword(rs.getString("password"));
				acc.setUsername(rs.getString("username"));
				acc.setRole(rs.getInt("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acc;
	}

	public List<Account> queryAccount(Account account) {
		String sql = "select * from account where 1=1";
		if(account.getId() > 0){
			sql += " and id ="+account.getId();
		}
		if(account.getAccount() != null){
			sql += " and account like '%"+account.getAccount()+"%' ";
		}
		if(account.getUsername() != null){
			sql += " and username like '%"+account.getUsername()+"%' ";
		}
		if(account.getRole() > 0){
			sql +=" and role = "+account.getRole(); 
		}
		System.out.println(sql);
		List<Account> accountList = new ArrayList<Account>();
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				account = new Account();
				account.setId(rs.getInt("id"));
				account.setAccount(rs.getString("account"));
				account.setPassword(rs.getString("password"));
				account.setUsername(rs.getString("username"));
				account.setRole(rs.getInt("role"));
				accountList.add(account);
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
		return accountList;
	}

	public boolean insertAccount(Account account) {
		String sql = "insert into account (account,password,username,role) values('" + account.getAccount() + "','"
				+ account.getPassword() + "','" + account.getUsername() + "'," + account.getRole() + ")";
		System.out.println(sql);
		return executeUpdate(sql);
	}

	public boolean deleteAccount(Account account) {
		String sql = "delete from account where id =" + account.getId();
		System.out.println(sql);
		return executeUpdate(sql);
	}

	public boolean updateAccount(Account account) {
		String sql = "update account set account = '" + account.getAccount() + "',password ='" + account.getAccount()
				+ "',username = '" + account.getUsername() + "',role=" + account.getRole() + " where id = "
				+ account.getId();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public Page<Account> queryAccountByPage(Page<Account> accPage) {
		Page<Account> rsAccPage= new Page<Account>();
		accountList  = new ArrayList<Account>(15);
		String sql = "select * from account where 1=1";
		String sql2 =  "select count(*) as rows from account where 1=1";
		if(accPage.getQueryAttr().getId() > 0){
			sql2 += " and id ="+accPage.getQueryAttr().getId();
			sql += " and id ="+accPage.getQueryAttr().getId();
		}
		if(accPage.getQueryAttr().getAccount() != null){
			sql2 += " and account like '%"+accPage.getQueryAttr().getAccount()+"%' ";
			sql += " and account like '%"+accPage.getQueryAttr().getAccount()+"%' ";
		}
		if(accPage.getQueryAttr().getUsername() != null){
			sql2 += " and username like '%"+accPage.getQueryAttr().getUsername()+"%' ";
			sql += " and username like '%"+accPage.getQueryAttr().getUsername()+"%' ";
		}
		
		if(accPage.getQueryAttr().getUserRole() > 0){
			sql2 += " and role  < "+accPage.getQueryAttr().getUserRole();
			sql += " and role  < "+accPage.getQueryAttr().getUserRole();
		}
		
		if(accPage.getQueryAttr().getRole() > 0){
			sql2 +=" and role = "+accPage.getQueryAttr().getRole(); 
			sql +=" and role = "+accPage.getQueryAttr().getRole(); 
		}
		sql += " limit " + accPage.getStartRowNo() + ", " + accPage.getPageSize();
		System.out.println(sql);
		try {
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				acc = new Account();
				acc.setId(rs.getInt("id"));
				acc.setAccount(rs.getString("account"));
				acc.setPassword(rs.getString("password"));
				acc.setUsername(rs.getString("username"));
				acc.setRole(rs.getInt("role"));
				accountList.add(acc);
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
		rsAccPage.setTotalRows(getTotleRows(sql2));
		rsAccPage.setPageNo(accPage.getPageNo());
		rsAccPage.setResultList(accountList);
		return rsAccPage;
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
