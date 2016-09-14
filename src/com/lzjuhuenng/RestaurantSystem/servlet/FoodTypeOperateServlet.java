package com.lzjuhuenng.RestaurantSystem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService;
import com.lzjuhuenng.RestaurantSystem.service.impl.FoodTypeServiceImpl;

/**
 * Servlet implementation class FoodTypeOperateServlet
 */
@WebServlet("/FoodTypeOperateServlet")
public class FoodTypeOperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodTypeOperateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		IFoodTypeService foodTypeService = new  FoodTypeServiceImpl();
		System.out.println(action);
		int id;
		switch (action){
		case "add" :
			String foodtype = request.getParameter("foodtype");
			if(foodTypeService.addFoodTypeToDB(foodtype)){
				System.out.println("��ӳɹ�");
				response.sendRedirect("/RestaurantSystem/admin/admin_operate_food_type.jsp");
				return ;
			}else{
				System.out.println("���ʧ��");
			}
			break;
		case "del" :
			 id =Integer.parseInt(request.getParameter("id"));
			if(foodTypeService.delFoodTypeFromDB(id)){
				System.out.println("ɾ���ɹ�");
			}
			response.sendRedirect("/RestaurantSystem/admin/admin_operate_food_type.jsp");
			return ;
		case "updateState" :
			 id =Integer.parseInt(request.getParameter("id"));
			if(foodTypeService.updateFoodTypeState(id)){
				System.out.println("״̬���ĳɹ�");
			}
			response.sendRedirect("/RestaurantSystem/admin/admin_operate_food_type.jsp");
			return ;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
