package com.lzjuhuenng.RestaurantSystem.entery;

import java.util.HashMap;
import java.util.Map;

public class ChartParam {
	private String param1;
	private String param2;
	private String param3;
	
	private Map<String, String> mapParam = new HashMap<String,String>();
	public ChartParam() {
		// TODO Auto-generated constructor stub
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public Map<String, String> getMapParam() {
		return mapParam;
	}
	public void setMapParam(Map<String, String> mapParam) {
		this.mapParam = mapParam;
	}
	
	
	
}
