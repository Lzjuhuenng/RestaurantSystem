package com.lzjuhuenng.RestaurantSystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjuhuenng.RestaurantSystem.entery.Food;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.service.IFoodService;
import com.lzjuhuenng.RestaurantSystem.service.impl.FoodServiceImpl;

/**
 * Servlet implementation class FoodOperateServlet
 */
@WebServlet("/FoodOperateServlet")
public class FoodOperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IFoodService foodService = new  FoodServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodOperateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Page<Food> foodPage;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		switch(action){
		
		case "update":
				Food food = foodService.queryAllFoodFormDB(request).get(0);
				request.setAttribute("Food", food);
				request.getRequestDispatcher("/admin/admin_update_food.jsp").forward(request, response);
			break ;
		case "updateFood":
			foodService.updateFood(request);
			response.sendRedirect("/RestaurantSystem/servlet/FoodOperateServlet?action=query");
			return  ;
		case "add" :
			if(foodService.addFoodIntoDB(request)){
				System.out.println("��ӳɹ�");
			}
			response.sendRedirect("/RestaurantSystem/servlet/FoodOperateServlet?action=query");
			return;
		case "del" :
			if(foodService.hiddenFoodFromDB(request)){
				System.out.println("���سɹ�");
			}
			response.sendRedirect("/RestaurantSystem/servlet/FoodOperateServlet?action=query");
			return;
		case "updateState" :
			if(foodService.updateFoodState(request)){
				System.out.println("���سɹ�");
			}
			response.sendRedirect("/RestaurantSystem/servlet/FoodOperateServlet?action=query");
			return;			
		case "query":
			foodPage = foodService.queryFoodFormDBByPage(request);
			request.setAttribute("foodPage",foodPage);
			request.getRequestDispatcher("/admin/query_food.jsp").forward(request, response);
			return;
		}
		
		
	}

}
