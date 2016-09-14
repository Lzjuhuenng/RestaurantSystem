package com.lzjuhuenng.RestaurantSystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {

	public OrderUtil() {
		// TODO Auto-generated constructor stub
	}
	private static long orderNum = 0l;  
    private static String date ;
	public static  String getOrderNo() {  
        String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());  
        if(date==null||!date.equals(str)){  
            date = str;  
            orderNum  = 0l;  
        }  
        orderNum ++;  
        long orderNo = Long.parseLong((date)) * 10000;  
        orderNo += orderNum;;  
        System.out.println(orderNo);
       return orderNo+"";  
    }
	
	
	
}
