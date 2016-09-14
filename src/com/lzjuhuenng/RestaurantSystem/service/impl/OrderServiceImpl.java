package com.lzjuhuenng.RestaurantSystem.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lzjuhuenng.RestaurantSystem.dao.IFoodDao;
import com.lzjuhuenng.RestaurantSystem.dao.IOrderDao;
import com.lzjuhuenng.RestaurantSystem.dao.IOrderItemsDao;
import com.lzjuhuenng.RestaurantSystem.dao.ITableDao;
import com.lzjuhuenng.RestaurantSystem.dao.impl.FoodDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.dao.impl.OrderDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.dao.impl.OrderItemsDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.dao.impl.TableDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.entery.Account;
import com.lzjuhuenng.RestaurantSystem.entery.Food;
import com.lzjuhuenng.RestaurantSystem.entery.FoodItem;
import com.lzjuhuenng.RestaurantSystem.entery.Order;
import com.lzjuhuenng.RestaurantSystem.entery.OrderItems;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.entery.Table;
import com.lzjuhuenng.RestaurantSystem.service.IOredrService;
import com.lzjuhuenng.RestaurantSystem.util.OrderUtil;

public class OrderServiceImpl implements IOredrService {

	public OrderServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private Order order;
	private Table table;
	private ITableDao tableDao = new TableDaoMysqlImpl();
	private IOrderDao orderDao = new  OrderDaoMysqlImpl();
	private IFoodDao foodDao = new FoodDaoMysqlImpl();
	private Food food = null;
	private List<Order> orderList = null;
	private OrderItems orderItems = null;
	private IOrderItemsDao orderItemsDao = new OrderItemsDaoMysqlImpl();
	public int   generateOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		order = new Order();
		table  = new Table();
		Account acc = (Account)session.getAttribute("account"); 
		String tableId = request.getParameter("tableId");
		String orderNo = new String(OrderUtil.getOrderNo());
		order.setOrderNO(orderNo);
		System.out.println(orderNo);
		order.setTableId(Integer.parseInt(tableId));
		order.setAccountId(acc.getId());
		order.setState(1);
		table.setId(Integer.parseInt(tableId));
		table = tableDao.queryTable(table).get(0);
		table.setState(0);
		tableDao.updateTable(table);
		if(orderDao.insertOrder(order)){
			return orderDao.getOrderIdByOrderNo(orderNo);
		}
		return -1;
	}
	
	public boolean deleteOrderFormDB(HttpServletRequest request){
		String orderId = request.getParameter("orderId");
		order = new Order();
		order.setId(Integer.parseInt(orderId));
		return orderDao.deleteOrder(order);
	}
	public void addItem(HttpServletRequest request){
		HttpSession session = request.getSession();
		OrderItems orderItems =  (OrderItems)session.getAttribute("orderItems");
		session.removeAttribute("orderItems");
		FoodItem foodItem = new FoodItem();
		int  foodId = Integer.parseInt(request.getParameter("foodId"));
		int num  = Integer.parseInt(request.getParameter("num"));
		food = new Food();
		food.setId(foodId);
		food = foodDao.queryFood(food).get(0);
		foodItem.setFoodId(food.getId());
		foodItem.setPrice(food.getPrice());
		foodItem.setNum(num);
		System.out.println("��ӳɹ���");
		orderItems.addItem(foodItem);
		session.setAttribute("orderItems", orderItems);
	}
	public void submitOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		orderItems = (OrderItems) session.getAttribute("orderItems");
		session.removeAttribute("orderItems");
		if(orderItems.foodList.size() != 0){
			for(FoodItem foodItem : orderItems.foodList){
				foodItem.setOrderId(orderItems.getOrderId());
				foodItem.setFoodState(1);
				orderItemsDao.insertOrderItem(foodItem);
			}
		}else{
			//����ύ����ʱ�б�Ϊ�գ��Զ������ݿ���ɾ������
			table.setId(orderItems.getTableId());
			table = tableDao.queryTable(table).get(0);
			table.setState(1);
			tableDao.updateTable(table);
			order = new Order();
			order.setId(orderItems.getOrderId());	
			orderDao.deleteOrder(order);
		}
	}

	
	
	/**
	 * ���ݲ�ͬ��״̬��ѯ����
	 * 1   �� ����Ա�ύ��û������ʦ����
	 * 2   ��  ��ʦ������ɵ����в�Ʒ��������--->��Ҫ����Ա���������ύ
	 * 3   �� ��ʦ�����������в�Ʒ��������
	 * 4  ��  ���������
	 * @param request
	 * @return
	 */
	public List<Order> queryOrderFormDB(HttpServletRequest request){
		order = new Order();
		String state = (String)request.getAttribute("state");
		switch(state){
			case "query":
				order.setState(2);
				orderList = orderDao.queryOrder(order);
				order.setState(3);
				if(orderDao.queryOrder(order)!=null){
					orderList.addAll(orderDao.queryOrder(order));	
				}
				order.setState(1);
				if(orderDao.queryOrder(order)!=null){
					orderList.addAll(orderDao.queryOrder(order));	
				}	
				return orderList;
			case "accept":
				order.setState(1);
				return orderDao.queryOrder(order);
			case "settle":
				order.setState(3);
				return orderDao.queryOrder(order);
			case "completed":
				order.setState(4);
				return orderDao.queryOrder(order);			
		}
	return null;
	}
	
	public Page<Order> queryOrderFormDBByPage(HttpServletRequest request){
		order = new Order();
		Page<Order> orderPage = new Page<Order>();
		String state = (String)request.getAttribute("state");
		switch(state){
			case "query":
				order.setState(2);
//				orderPage.setQueryAttr(order);
//				O
				orderList = orderDao.queryOrder(order);
				order.setState(3);
				if(orderDao.queryOrder(order)!=null){
					orderList.addAll(orderDao.queryOrder(order));	
				}
				order.setState(1);
				if(orderDao.queryOrder(order)!=null){
					orderList.addAll(orderDao.queryOrder(order));	
				}	
//				return orderList;
			case "accept":
				order.setState(1);
//				return orderDao.queryOrder(order);
			case "settle":
				order.setState(3);
//				return orderDao.queryOrder(order);
			case "completed":
				order.setState(4);
				orderPage.setQueryAttr(order);
				orderPage = orderDao.queryOrderByPage(orderPage);
				return orderPage;			
		}
	return null;
	}
	
	
	public  boolean unsubscribeOrder(HttpServletRequest request){
		String orderId =request.getParameter("orderId");
		order = new Order();
		table  = new Table();
		if(orderId != null){
			order.setId(Integer.parseInt(orderId));
		}
		orderList = orderDao.queryOrder(order);
		if(orderList != null){
			order = orderList.get(0);
			order.setState(0);
			table.setId(order.getTableId());
			table = tableDao.queryTable(table).get(0);
			table.setState(1);
			tableDao.updateTable(table);
			return orderDao.updateOrder(order);
		}
		return false;
	}
	
	public boolean settleAccounts(HttpServletRequest request){
		String orderId =request.getParameter("orderId");
		order = new Order();
		table  = new Table();
		if(orderId != null){
			order.setId(Integer.parseInt(orderId));
		}
		orderList = orderDao.queryOrder(order);
		if(orderList != null){
			order = orderList.get(0);
			order.setState(4);
			table.setId(order.getTableId());
			table = tableDao.queryTable(table).get(0);
			table.setState(1);
			tableDao.updateTable(table);
			return orderDao.updateOrder(order);
		}
		return false;
	}
	
	public List<FoodItem> getOrderDetails(HttpServletRequest request){
		FoodItem foodItem = new FoodItem();
		String orderId = request.getParameter("orderId");
		if(orderId != null){
			foodItem.setOrderId(Integer.parseInt(orderId));
		}
		return  orderItemsDao.queryOrderItems(foodItem);
	}
	
	public boolean orderConfirm(HttpServletRequest request){
		String orderId = request.getParameter("orderId");
		order = new Order();
		order.setId(Integer.parseInt(orderId));
		order = orderDao.queryOrder(order).get(0);
		order.setState(3);
		return orderDao.updateOrder(order);
	}
	
	public boolean operateFoodItemState(HttpServletRequest request){
		String foodItemId = request.getParameter("foodItemId");
		FoodItem foodItem = new FoodItem();
		foodItem.setId(Integer.parseInt(foodItemId));
		foodItem = orderItemsDao.queryOrderItems(foodItem).get(0);
		if(foodItem.getFoodState()==1){
			foodItem.setFoodState(0);
		}else{
			foodItem.setFoodState(1);
		}
		return orderItemsDao.updateOrderItem(foodItem);
	}
	
	public boolean unsubscribeFoodItem(HttpServletRequest request){
		String foodItemId = request.getParameter("foodItemId");
		FoodItem foodItem = new FoodItem();
		foodItem.setId(Integer.parseInt(foodItemId));
		return  orderItemsDao.deleteOrderItem(foodItem);
	}
}
