package com.lzjuhuenng.RestaurantSystem.entery;

public class Table {
	private int id;
	private String table_no; 
	private int capacity;
	private int state;
	private int mincapacity;
	private int maxcapacity;
	
	
	public Table() {
		// TODO Auto-generated constructor stub
	}

	public int getMincapacity() {
		return mincapacity;
	}

	public void setMincapacity(int mincapacity) {
		this.mincapacity = mincapacity;
	}

	public int getMaxcapacity() {
		return maxcapacity;
	}

	public void setMaxcapacity(int maxcapacity) {
		this.maxcapacity = maxcapacity;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTable_no() {
		return table_no;
	}

	public void setTable_no(String table_no) {
		this.table_no = table_no;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
