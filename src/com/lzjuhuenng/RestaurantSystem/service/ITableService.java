package com.lzjuhuenng.RestaurantSystem.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.entery.Table;

public interface ITableService {
	public List<Table> queryTableFromDB(HttpServletRequest request);
	public boolean delTableFromDB(HttpServletRequest request);
	public boolean insertTableIntoDB(HttpServletRequest request);
	public boolean updateTableInformation(HttpServletRequest request);
	public boolean updateTableState(HttpServletRequest request);
	public Map<Integer, String> getTableMap(HttpServletRequest request);
	public Page<Table> queryTableFromDBByPage(HttpServletRequest request);
}
