package com.lzjuhuenng.RestaurantSystem.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzjuhuenng.RestaurantSystem.entery.Food;
import com.lzjuhuenng.RestaurantSystem.entery.FoodItem;
import com.lzjuhuenng.RestaurantSystem.entery.Order;
import com.lzjuhuenng.RestaurantSystem.entery.OrderItems;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.entery.Table;
import com.lzjuhuenng.RestaurantSystem.service.IFoodService;
import com.lzjuhuenng.RestaurantSystem.service.IOredrService;
import com.lzjuhuenng.RestaurantSystem.service.ITableService;
import com.lzjuhuenng.RestaurantSystem.service.impl.FoodServiceImpl;
import com.lzjuhuenng.RestaurantSystem.service.impl.OrderServiceImpl;
import com.lzjuhuenng.RestaurantSystem.service.impl.TableServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	IOredrService orderService = new OrderServiceImpl();
	IFoodService foodService = new FoodServiceImpl();
	List<Order> orderList = null;
	List<Table> tableList = null;
	List<Food> foodList = null; 
	Page<Food> foodPage = null;
	ITableService tableService = new TableServiceImpl();
	List<FoodItem> foodItemsList = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String action = request.getParameter("action");
			System.out.println(action);
			switch(action){
			// ���ҳ�� ���� ����Ա���� 
				case "order":
					tableList = tableService.queryTableFromDB(request);
					System.out.println(tableList);
					request.setAttribute("tableList", tableList);
					request.getRequestDispatcher("/servicer/order_food.jsp").forward(request, response);
					return ;
					//�������� ���� ����Ա����
				case "generate":
					HttpSession session = request.getSession();
					int id = 0;
					int tableId =0 ;
					if(request.getParameter("tableId")!=null &&!"".equals(request.getParameter("tableId"))){
						tableId = Integer.parseInt(request.getParameter("tableId"));
					}
					if(session.getAttribute("orderItems") == null){
						
						id = orderService.generateOrder(request);
						if(id == -1){
							request.setAttribute("message", "��������ʧ��");
							request.getRequestDispatcher("error.jsp").forward(request, response);
							return;
						}
						OrderItems orderItems  = new OrderItems();
						orderItems.setOrderId(id);
						if(tableId != 0){
						orderItems.setTableId(tableId);
						}
						session.setAttribute("orderItems", orderItems);
					}
					request.setAttribute("state", 2);
					foodPage = foodService.queryFoodFormDBByPage(request);
					request.setAttribute("foodPage", foodPage);
					request.getRequestDispatcher("/servicer/order_food_2.jsp").forward(request, response);
					return  ;
				//��Ӷ����� ���� ����Ա����
				case "additem": 
					System.out.println("addItem");
					orderService.addItem(request);
				//�����㵥  ���� ����Ա����
				case "continue":
				//�鿴�˵�  ���� ����Ա����
				case "query":
					request.setAttribute("state", 2);
					foodPage = foodService.queryFoodFormDBByPage(request);
					request.setAttribute("foodPage", foodPage);
					request.getRequestDispatcher("/servicer/order_food_2.jsp").forward(request, response);
					return  ;
					//�鿴�ѵ�  ���� ����Ա����
				case "querySelect":
					request.getRequestDispatcher("/servicer/order_food_3.jsp").forward(request, response);
					return ;
					//�ύ���� ���� ����Ա���� 
				case "submit":
					orderService.submitOrder(request);
					response.sendRedirect("/RestaurantSystem/servlet/OrderServlet?action=order");
					return ;
				
	
					//�˶����� ���� ������
				case "unsubscribe"://�˶�����
					orderService.unsubscribeOrder(request);
				//���붩������ҳ��
				case "operateOrder":
					request.setAttribute("state", "query");
					orderList = orderService.queryOrderFormDB(request);
					request.setAttribute("orderList", orderList);
					request.getRequestDispatcher("/servicer/operate_order.jsp").forward(request, response);
					return;
					//�˶���Ʒ  ��������Ա���� 
				case "unsubscribeFoodItem":
					orderService.unsubscribeFoodItem(request);
					//�鿴����ϸ��  ���� ����Ա����
				case "orderDetails":
					foodItemsList = orderService.getOrderDetails(request);
					request.setAttribute("foodItemsList", foodItemsList);
					request.getRequestDispatcher("/servicer/orderitems.jsp").forward(request, response);
					return ;	
			
					
					
					//�������ҳ��  ��������Ա����
				case "settle" :
					request.setAttribute("state", "settle");
					orderList = orderService.queryOrderFormDB(request);
					request.setAttribute("orderList", orderList);
					request.getRequestDispatcher("/servicer/settle_accounts.jsp").forward(request, response);
					return;
					//���˶���  ���� ����Ա����
				case "settleAccount":
					if(orderService.settleAccounts(request)){
						request.setAttribute("state", "settle");
						orderList = orderService.queryOrderFormDB(request);
						request.setAttribute("orderList", orderList);
						request.getRequestDispatcher("/servicer/settle_accounts.jsp").forward(request, response);
						return;
					}
					return;
				//�鿴��ɶ���  ��������Ա����
				case "completed" :
					request.setAttribute("state", "completed");
					Page<Order> orderPage = orderService.queryOrderFormDBByPage(request);
					request.setAttribute("orderPage", orderPage);
					request.getRequestDispatcher("/servicer/completed_order.jsp").forward(request, response);
					return ;
					
					//����ɶ���ϸ�� ���� ����Ա����
				case "completedOrderDetails":
					foodItemsList = orderService.getOrderDetails(request);
					request.setAttribute("foodItemsList", foodItemsList);
					request.getRequestDispatcher("/servicer/completed_orderitems.jsp").forward(request, response);
					return ;	
			
				//�ӵ�ҳ��  ���� ��ʦ����
				case "accept" :
					request.setAttribute("state", "accept");
					orderList = orderService.queryOrderFormDB(request);
					request.setAttribute("orderList", orderList);
					request.getRequestDispatcher("/cook/accept_order.jsp").forward(request, response);
					return;
				//�ӵ�����  ���� ��ʦ����
				case "acceptOrder":
					foodItemsList = orderService.getOrderDetails(request);
					request.setAttribute("foodItemsList", foodItemsList);
					request.getRequestDispatcher("/cook/orderitem_operate.jsp").forward(request, response);
					return ;
					
				case "operateFoodState":
					if(orderService.operateFoodItemState(request)){
						foodItemsList = orderService.getOrderDetails(request);
						request.setAttribute("foodItemsList", foodItemsList);
						request.getRequestDispatcher("/cook/orderitem_operate.jsp").forward(request, response);
					}
					return ;
				//ȷ�϶���   	:   ��ʦ����
				case "orderConfirm":
					if(orderService.orderConfirm(request)){
						response.sendRedirect("/RestaurantSystem/servlet/OrderServlet?action=accept");
						return;
					}
					return;
				case "comlpetedOrder":
					request.setAttribute("state", "completed");
					orderList = orderService.queryOrderFormDB(request);
					request.setAttribute("orderList", orderList);
					request.getRequestDispatcher("/cook/completed_order.jsp").forward(request, response);
					return;
				case "cookCompletedOrderDetails":
					foodItemsList = orderService.getOrderDetails(request);
					request.setAttribute("foodItemsList", foodItemsList);
					request.getRequestDispatcher("/cook/completed_orderitems.jsp").forward(request, response);
					return ;	
				case "cookUpdateState" :
					if(foodService.updateFoodState(request)){
						System.out.println("���سɹ�");
					}
					response.sendRedirect("/RestaurantSystem/servlet/OrderServlet?action=cookQuery");
					return;			
				case "cookQuery":
					foodPage = foodService.queryFoodFormDBByPage(request);
					request.setAttribute("foodPage",foodPage);
					request.getRequestDispatcher("/cook/query_food.jsp").forward(request, response);
					return;
			}
	}

}
