package com.lzjuhuenng.RestaurantSystem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.entery.Table;
import com.lzjuhuenng.RestaurantSystem.service.ITableService;
import com.lzjuhuenng.RestaurantSystem.service.impl.TableServiceImpl;

/**
 * Servlet implementation class TableOperateServlet
 */
@WebServlet("/TableOperateServlet")
public class TableOperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableOperateServlet() {
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
	private ITableService tableService = new TableServiceImpl();
	private Page<Table> tablePage;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		switch(action){
			case "query":
			tablePage = tableService.queryTableFromDBByPage(request); 
			request.setAttribute("tablePage", tablePage);
			request.getRequestDispatcher("/admin/query_table.jsp").forward(request, response);
			return;
			case "add" :
				if(tableService.insertTableIntoDB(request)){
					
				}
				response.sendRedirect("/RestaurantSystem/servlet/TableOperateServlet?action=query");
				return ;
			case "del" :
				if(tableService.delTableFromDB(request)){
					
				}
				response.sendRedirect("/RestaurantSystem/servlet/TableOperateServlet?action=query");
				return ;
			case "update" :
				Table  table = tableService.queryTableFromDB(request).get(0);
				request.setAttribute("Table", table);
				request.getRequestDispatcher("/admin/admin_update_table.jsp").forward(request, response);	
				return; 
			case "updateTable":
				if(tableService.updateTableInformation(request)){
					
				}
				response.sendRedirect("/RestaurantSystem/servlet/TableOperateServlet?action=query");
				return ;
			case "updateState" :
				if(tableService.updateTableState(request)){
					
				}
				response.sendRedirect("/RestaurantSystem/servlet/TableOperateServlet?action=query");
				return ;
		}
	}

}
