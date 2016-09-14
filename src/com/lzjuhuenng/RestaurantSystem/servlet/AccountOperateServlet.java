package com.lzjuhuenng.RestaurantSystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjuhuenng.RestaurantSystem.entery.Account;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.service.IAccountService;
import com.lzjuhuenng.RestaurantSystem.service.impl.AccountServiceImpl;

/**
 * Servlet implementation class AccountOperateServlet
 */
@WebServlet("/AccountOperateServlet")
public class AccountOperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountOperateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private IAccountService accService =  new AccountServiceImpl();
	private Page<Account> accPage;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		switch(action){
		case "query":
			accPage = accService.queryAccountFromDBByPage(request); 
			request.setAttribute("accPage", accPage);
			request.getRequestDispatcher("/admin/query_account.jsp").forward(request, response);
			return;
		case "add":
			if(accService.addAccountIntoDB(request)){
				System.out.println("���Account�ɹ���"); 
			}
			response.sendRedirect("/RestaurantSystem/servlet/AccountOperateServlet?action=query");
			return ;
		case "del":
			if(accService.delAccountFromDB(request)){
				System.out.println("ɾ��Account�ɹ���");
			}
			response.sendRedirect("/RestaurantSystem/servlet/AccountOperateServlet?action=query");
			return ;
		case "update":
			Account acc =accService.queryAccountFromDB(request).get(0);
			request.setAttribute("Account", acc);
			request.getRequestDispatcher("/admin/admin_update_account.jsp").forward(request, response);
			return ;
		case "updateAccount":
			if(accService.updateAccount(request)){
				System.out.println("����Account�ɹ���");
			}
			response.sendRedirect("/RestaurantSystem/servlet/AccountOperateServlet?action=query");
			return ;
		}
	}

}
