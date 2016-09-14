package com.lzjuhuenng.RestaurantSystem.entery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderItems {
	private int orderId;
	private int tableId;
	public List<FoodItem> foodList = new ArrayList<FoodItem>();
	public OrderItems() {
		// TODO Auto-generated constructor stub
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	//���
	public void addItem(FoodItem foodItem){
		boolean boo = true; 
		for(FoodItem liFoodItem : foodList){
			if(foodItem.getFoodId() == liFoodItem.getFoodId()){
				liFoodItem.setNum(foodItem.getNum()+liFoodItem.getNum());
				boo = false;
			}
		}
		if(boo){
			foodList.add(foodItem);
		}
	}
	public void removeItem(FoodItem foodItem){
		for (Iterator<FoodItem> it = foodList.iterator(); it.hasNext();) {
			FoodItem liFoodItem = (FoodItem) it.next();
			if (foodItem.getFoodId() == liFoodItem.getFoodId()){
				it.remove();
				}
			}
	}
}
