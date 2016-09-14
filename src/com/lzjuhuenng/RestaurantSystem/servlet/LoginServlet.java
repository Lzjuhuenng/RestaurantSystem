package com.lzjuhuenng.RestaurantSystem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjuhuenng.RestaurantSystem.service.IAccountService;
import com.lzjuhuenng.RestaurantSystem.service.impl.AccountServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAccountService accService = new AccountServiceImpl(); 
		if(accService.Login(request)>2){
			response.sendRedirect("/admin/admin_operate_food_type.jsp");
		}else if(accService.Login(request) == 2){
			response.sendRedirect("/servlet/OrderServlet?action=order");
		}else  if(accService.Login(request) == 1){
			response.sendRedirect("/servlet/OrderServlet?action=accept");
		}else{
			request.setAttribute("message", "�û������������");//��ֹ����֪���û������ڣ�������¼
			request.getRequestDispatcher("/index.jsp").forward(request, response);//�ַ����󣬸÷�ʽ��ת������ı������URL��ַ������Ŀ��ҳ�洫�ݲ���
		}
	}

}