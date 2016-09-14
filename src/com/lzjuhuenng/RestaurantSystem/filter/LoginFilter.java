package com.lzjuhuenng.RestaurantSystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	private FilterConfig config;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();

		// request.setCharacterEncoding("utf-8");//过滤器修改编�?
		// String charset = config.getInitParameter("charset");
		// if (charset == null) {
		// charset = "utf-8";
		// }
		// request.setCharacterEncoding(charset);
		// // request.setCharacterEncoding(config.getInitParameter("charset"));
		String noLoginPaths = config.getInitParameter("noLoginPaths");

		if (noLoginPaths != null) {
			String[] strArray = noLoginPaths.split(";");
			for (int i = 0; i < strArray.length; i++) {

				if (strArray[i] == null || "".equals(strArray[i]))
					continue; // 避免出现�? �?直放�?
				if (request.getRequestURI().indexOf(strArray[i]) != -1) {
					System.out.print("1234");
					arg2.doFilter(arg0, arg1);
					return;
				}

			}
		}
//		if (request.getRequestURI().indexOf("login.jsp") != -1) {
//			arg2.doFilter(arg0, arg1);
//			return;
//		}

		if (session.getAttribute("account") != null) {
			arg2.doFilter(arg0, arg1);
		} else {
			response.sendRedirect("/index.jsp");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}
