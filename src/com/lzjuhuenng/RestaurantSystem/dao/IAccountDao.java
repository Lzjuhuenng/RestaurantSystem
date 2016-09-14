package com.lzjuhuenng.RestaurantSystem.dao;

import java.util.List;

import com.lzjuhuenng.RestaurantSystem.entery.Account;
import com.lzjuhuenng.RestaurantSystem.entery.Page;

public interface IAccountDao {
	public Account getAccount(String account);
	public List<Account> queryAccount(Account account) ;
	public boolean insertAccount(Account account);
	public boolean deleteAccount(Account account) ;
	public boolean updateAccount(Account account);
	public boolean executeUpdate(String sql);
	public Page<Account> queryAccountByPage(Page<Account> accPage);
}
