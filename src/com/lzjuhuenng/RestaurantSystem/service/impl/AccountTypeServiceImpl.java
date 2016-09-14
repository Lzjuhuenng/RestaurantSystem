package com.lzjuhuenng.RestaurantSystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.lzjuhuenng.RestaurantSystem.dao.IAccountTypeDao;
import com.lzjuhuenng.RestaurantSystem.dao.impl.AccountTypeDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.entery.AccountType;
import com.lzjuhuenng.RestaurantSystem.service.IAccountTypeService;

public class AccountTypeServiceImpl implements IAccountTypeService {

	public AccountTypeServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	IAccountTypeDao accountTypeDao = new AccountTypeDaoMysqlImpl();
	public Map<Integer, String> getAccountTypeMap(){
		Map<Integer, String> accountTypeMap = new HashMap<Integer,String>();
		List<AccountType> accountTypeList = accountTypeDao.queryAccountType();
		for(AccountType accountType : accountTypeList){
			accountTypeMap.put(accountType.getId(), accountType.getAcconutType());
		}
		return accountTypeMap;
	}
}
