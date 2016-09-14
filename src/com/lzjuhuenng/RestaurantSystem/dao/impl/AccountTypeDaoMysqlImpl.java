package com.lzjuhuenng.RestaurantSystem.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.lzjuhuenng.RestaurantSystem.dao.IAccountTypeDao;
import com.lzjuhuenng.RestaurantSystem.entery.AccountType;
import com.lzjuhuenng.RestaurantSystem.util.DBUtil;

public class AccountTypeDaoMysqlImpl implements IAccountTypeDao {

	public AccountTypeDaoMysqlImpl() {
		// TODO Auto-generated constructor stub
	}
	public List<AccountType> queryAccountType(){
		String  sql = "select  * from account_type";
		System.out.println(sql);
		List<AccountType> accountTypeList = new ArrayList<AccountType>();
		AccountType accountType = null;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				accountType = new AccountType();
				accountType.setId(rs.getInt("id"));
				accountType.setAcconutType(rs.getString("account_type"));
				accountTypeList.add(accountType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return accountTypeList;
	}
}
