package com.lzjuhuenng.RestaurantSystem.dao;

import java.util.Map;

import com.lzjuhuenng.RestaurantSystem.entery.ChartParam;

public interface IChartDao {
	public Map<String, String> getDayIncomeMap(ChartParam chartParam);
	public Map<String, String> getMonthIncomeMap(ChartParam chartParam);
	public Map<String, String> getYearIncomeMap(ChartParam chartParam);
}
