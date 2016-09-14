package com.lzjuhuenng.RestaurantSystem.entery;

import java.util.List;

public class Page<T> {

	//ҳ��
	private int pageNo;
	
	//ÿҳ��ʾ��Ϣ����
	private int pageSize = 10;
	
	private int totalPageNo;
	
	private int totalRows;
	
	private int startRowNo;
	
	private T queryAttr;
	
	private List<T> resultList;

	
	
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalPageNo
	 */
	public int getTotalPageNo() {
		
		totalPageNo = totalRows%pageSize == 0 ? totalRows/pageSize-1 : totalRows/pageSize  ;
		
		return totalPageNo;
	}

	/**
	 * @param totalPageNo the totalPageNo to set
	 */
	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return the startRowNo
	 */
	public int getStartRowNo() {
		startRowNo = pageNo * pageSize;
		return startRowNo;
	}

	/**
	 * @param startRowNo the startRowNo to set
	 */
	public void setStartRowNo(int startRowNo) {
		this.startRowNo = startRowNo;
	}

	/**
	 * @return the queryAttr
	 */
	public T getQueryAttr() {
		return queryAttr;
	}

	/**
	 * @param queryAttr the queryAttr to set
	 */
	public void setQueryAttr(T queryAttr) {
		this.queryAttr = queryAttr;
	}

	/**
	 * @return the resultList
	 */
	public List<T> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList the resultList to set
	 */
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
}
