package com.lzjuhuenng.RestaurantSystem.dao;

import java.util.List;

import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.entery.Table;

public interface ITableDao {
	public List<Table> queryTable(Table table);
	public boolean insertTable(Table table);
	public boolean deleteTable(Table Table);
	public boolean updateTable(Table table);
	public boolean executeUpdateTable(String sql);
	public Page<Table> queryTableByPage(Page<Table> tablePage);
}
