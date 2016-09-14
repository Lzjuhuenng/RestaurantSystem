package com.lzjuhuenng.RestaurantSystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lzjuhuenng.RestaurantSystem.dao.ITableDao;
import com.lzjuhuenng.RestaurantSystem.dao.impl.TableDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.entery.Table;
import com.lzjuhuenng.RestaurantSystem.service.ITableService;

public class TableServiceImpl implements ITableService {

	public TableServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private ITableDao tableDao = new TableDaoMysqlImpl(); 
	private Table table = null;
	private List<Table> tableList = null;
	
	public List<Table> queryTableFromDB(HttpServletRequest request){
		table = new Table();
		String id = request.getParameter("id");
		String table_no = request.getParameter("tableno");
		String mincapacity = request.getParameter("mincapacity");
		String maxcapacity = request.getParameter("maxcapacity");
		if(id !=null && !"".equals(id) ){
			table.setId((Integer.parseInt(id)));
		}
		if(table_no !=null && !"".equals(table_no) ){
			table.setTable_no(table_no);
		}
		if(mincapacity != null && !"".equals(mincapacity)){
			table.setMincapacity(Integer.parseInt(mincapacity));
		}
		if(maxcapacity != null &&! "".equals(maxcapacity)){
			table.setMaxcapacity(Integer.parseInt(maxcapacity));
		}
		tableList = tableDao.queryTable(table);
		tableList.add(table);
		return tableList;
	}
	
	public boolean insertTableIntoDB(HttpServletRequest request){
		table = new Table();
		table.setTable_no(request.getParameter("table_no"));
		table.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		table.setState(0);
		return tableDao.insertTable(table);
	}
	public boolean delTableFromDB(HttpServletRequest request){
		table = new Table();
		table.setId(Integer.parseInt(request.getParameter("id")));
		return tableDao.deleteTable(table);
	}
	
	public boolean updateTableInformation(HttpServletRequest request){
		table = new Table();
		table.setId(Integer.parseInt(request.getParameter("id")));
		table.setTable_no(request.getParameter("table_no"));
		table.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		table.setState(Integer.parseInt(request.getParameter("state")));
		return tableDao.updateTable(table);
	}
	public boolean updateTableState(HttpServletRequest request){
		table = new Table();
		table.setId(Integer.parseInt(request.getParameter("id")));
		table = tableDao.queryTable(table).get(0);
		if(table.getState() == 0){
			table.setState(1);
		}else{
			table.setState(0);
		}
		return tableDao.updateTable(table);
	}
	
	public Map<Integer, String> getTableMap(HttpServletRequest request){
		Map<Integer, String> tableMap = new HashMap<Integer,String>();
		table = new Table();
		tableList = tableDao.queryTable(table);
		for(Table table : tableList){
			tableMap.put(table.getId(), table.getTable_no());
		}
		return tableMap;
	}
	
	public Page<Table> queryTableFromDBByPage(HttpServletRequest request){
		Page<Table> tablePage = new Page<Table>(); 
		table = new Table();
		String id = request.getParameter("id");
		String table_no = request.getParameter("tableno");
		String mincapacity = request.getParameter("mincapacity");
		String maxcapacity = request.getParameter("maxcapacity");
		if(id !=null && !"".equals(id) ){
			table.setId((Integer.parseInt(id)));
		}
		if(table_no !=null && !"".equals(table_no) ){
			table.setTable_no(table_no);
		}
		if(mincapacity != null && !"".equals(mincapacity)){
			table.setMincapacity(Integer.parseInt(mincapacity));
		}
		if(maxcapacity != null &&! "".equals(maxcapacity)){
			table.setMaxcapacity(Integer.parseInt(maxcapacity));
		}
		if(request.getParameter("pageNo")!=null &&! "".equals(request.getParameter("pageNo"))){
			tablePage.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		tablePage.setQueryAttr(table);
		tablePage = tableDao.queryTableByPage(tablePage);
		tablePage.getResultList().add(table);
		return tablePage;
	}
}
