package com.lzjuhuenng.RestaurantSystem.entery;

public class FoodItem {
	private  int id;
	private  int  foodId;
	private  int foodState;
	private  int OrderId;
	private double price;
	private int num;

	public FoodItem() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getFoodState() {
		return foodState;
	}
	public void setFoodState(int foodState) {
		this.foodState = foodState;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	
}
