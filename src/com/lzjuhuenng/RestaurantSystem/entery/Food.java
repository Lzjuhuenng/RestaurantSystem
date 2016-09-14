package com.lzjuhuenng.RestaurantSystem.entery;

public class Food {
	/**
	 * ��ƷID
	 */
	private int id;
	/**
	 * ��Ʒ����
	 */
	private String food_name;
	/**
	 *��Ʒ���� 
	 */
	private int food_type;
	/**
	 * ��Ʒ�۸�
	 */
	private double price;
	
	private double max_price;
	private double min_price;
	
	public double getMax_price() {
		return max_price;
	}
	public void setMax_price(double max_price) {
		this.max_price = max_price;
	}
	public double getMin_price() {
		return min_price;
	}
	public void setMin_price(double min_price) {
		this.min_price = min_price;
	}
	/**
	 * ��Ʒ״̬
	 * 0 ����ɾ��
	 * 1 �� �¼�
	 * 2 �� �ϼ�
	 */
	private int food_state;
	/**
	 * �޲ι��췽��
	 */
	public Food() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public int getFood_type() {
		return food_type;
	}
	public void setFood_type(int food_type) {
		this.food_type = food_type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getFood_state() {
		return food_state;
	}
	public void setFood_state(int food_state) {
		this.food_state = food_state;
	}
	
	
}
