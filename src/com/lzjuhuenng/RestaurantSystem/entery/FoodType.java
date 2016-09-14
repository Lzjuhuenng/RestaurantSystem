package com.lzjuhuenng.RestaurantSystem.entery;

public class FoodType {
	/**
	 * �����ݿ��е����
	 */
	private int id;
	/**
	 * ��Ʒ����
	 */
	private String type;
	/**
	 * ��Ʒ�����״̬��
	 * 0 ���¼�
	 * 1 ���ϼ�
	 */
	private int state;
	
	public FoodType() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
