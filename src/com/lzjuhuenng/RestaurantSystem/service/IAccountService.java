package com.lzjuhuenng.RestaurantSystem.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lzjuhuenng.RestaurantSystem.entery.Account;
import com.lzjuhuenng.RestaurantSystem.entery.Page;

public interface IAccountService {
	public int Login(HttpServletRequest request);
	public boolean addAccountIntoDB(HttpServletRequest request);
	public boolean delAccountFromDB(HttpServletRequest request);
	public boolean updateAccount(HttpServletRequest request);
	public List<Account> queryAccountFromDB(HttpServletRequest request);
	public Page<Account> queryAccountFromDBByPage(HttpServletRequest request);
}
