package com.lzjuhuenng.RestaurantSystem.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class OrderFoodFilter
 */
@WebFilter("/OrderFoodFilter")
public class OrderFoodFilter implements Filter {
	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public OrderFoodFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
 		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String queryPaths = config.getInitParameter("queryPaths");
		if (session.getAttribute("orderItems") != null) {
			if (queryPaths != null) {
				String[] strArray = queryPaths.split(";");
				for (int i = 0; i < strArray.length; i++) {

					if (strArray[i] == null || "".equals(strArray[i]))
						continue; 
					String url = "";
					if(request.getRequestURI()!=null&&!"".equals(request.getRequestURI())){
						url += request.getRequestURI();
					}
					if( request.getParameterMap().get("action")  !=null &&!"".equals((String) request.getParameterMap().get("action")[0])){
						url += (String) request.getParameterMap().get("action")[0];
					}
					System.out.println(url);
					if (url.indexOf(strArray[i]) != -1) {
						System.out.println("<-------------------->");
						chain.doFilter(req, resp);
						return;
					}
				}
			}
		}else{
			System.out.println("-------------------->>");
			chain.doFilter(req, resp);
			return;
		}
		
			System.out.println("--------------------");
			response.sendRedirect("/servlet/OrderServlet?action=query");
			return;
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		config = fConfig;
	}

}
