package com.lzjuhuenng.RestaurantSystem.entery;

public class Order {
	private int  id;
	private String orderNO;
	private String data;
	private int accountId;
	private int tableId;
	private int State;
	private double totleMoney;


	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}
	
	
	public double getTotleMoney() {
		return totleMoney;
	}

	public void setTotleMoney(double totleMoney) {
		this.totleMoney = totleMoney;
	}
	

}
