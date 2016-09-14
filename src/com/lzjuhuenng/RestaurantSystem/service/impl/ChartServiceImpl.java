package com.lzjuhuenng.RestaurantSystem.service.impl;

import java.util.Calendar;
import java.util.Date;



import com.lzjuhuenng.RestaurantSystem.dao.IChartDao;
import com.lzjuhuenng.RestaurantSystem.dao.impl.ChartDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.entery.ChartParam;
import com.lzjuhuenng.RestaurantSystem.service.IChartService;

public class ChartServiceImpl  implements IChartService{
	
	private IChartDao chartDao = new ChartDaoMysqlImpl();  
	private ChartParam chartParam;
	public ChartServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	//Map<String,String>

  public ChartParam  getSevenDayIncome(){
	  Calendar cal = Calendar.getInstance();
	  chartParam = new ChartParam();
	  cal.setTime(new Date());
	  int year = cal.get(Calendar.YEAR);//��ȡ��
	  int month = cal.get(Calendar.MONTH);//��ȡ�·�
	  int day = cal.get(Calendar.DAY_OF_MONTH);//
	  for(int i=0,j=0;j<7;j++,i++){
		  if(day-i<0){
			  cal.add(Calendar.MONTH, -1);
			  day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			  System.out.println(day);
			  month -= 1;
			  i=0;
		  }
		  if(day-1-i<0){
			  cal.add(Calendar.MONTH, -1);
			  day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			  month -= 1;
		  }
		  String param1 = year+"";
		  String param2 = year+"";
		  if(month+1<10){
			  param1 += "-0"+(month+1);
			  param2 += "-0"+(month+1);
		  }else{
			  param1 += "-"+(month+1);
			  param2 += "-"+(month+1);
		  }
		  
		  if(day-1-i<10){
			  param1 += "-0"+(day-1-i);
		  }else{
			  param1 += "-"+(day-1-i);
		  }
		  if(day+1-i<10){
			  param2 += "-0"+(day+1-i);
		  }else{
			  param2 += "-"+(day+1-i);
		  }
		  

		  ChartParam chartParam1 = new ChartParam();
		  chartParam1.setParam1(param1);
		  chartParam1.setParam2(param2);
		  chartParam.getMapParam().putAll(chartDao.getDayIncomeMap(chartParam1));
	  }
	 return  chartParam;
  }
  
  

	 public ChartParam  getFifteemDayIncome(){
		  Calendar cal = Calendar.getInstance();
		  chartParam = new ChartParam();
		  cal.setTime(new Date());
		  int year = cal.get(Calendar.YEAR);//��ȡ��
		  int month = cal.get(Calendar.MONTH);//��ȡ�·�
		  int day = cal.get(Calendar.DAY_OF_MONTH);//
		  for(int i=0,j=0;j<15;j++,i++){
			  if(day-1-i<0){
				  cal.add(Calendar.MONTH, -1);
				  day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				  month -= 1;
				  i=0;
			  }
			  String param1 = year+"";
			  String param2 = year+"";
			  if(month+1<10){
				  param1 += "-0"+(month+1);
				  param2 += "-0"+(month+1);
			  }else{
				  param1 += "-"+(month+1);
				  param2 += "-"+(month+1);
			  }
			  
			  if(day-1-i<10){
				  param1 += "-0"+(day-1-i);
			  }else{
				  param1 += "-"+(day-1-i);
			  }
			  if(day+1-i<10){
				  param2 += "-0"+(day+1-i);
			  }else{
				  param2 += "-"+(day+1-i);
			  }
			  
			  
			  ChartParam chartParam1 = new ChartParam();
			  chartParam1.setParam1(param1);
			  chartParam1.setParam2(param2);
			  chartParam.getMapParam().putAll(chartDao.getDayIncomeMap(chartParam1));
		  }
		  
		  return chartParam;
	  }
  
}
