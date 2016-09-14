package com.lzjuhuenng.RestaurantSystem.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharsetFilter
 */
@WebFilter("/CharsetFilter")
public class CharsetFilter implements Filter {
	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public CharsetFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		 request.setCharacterEncoding("utf-8");//过滤器修改编�?
		 String charset = config.getInitParameter("charset");
		 if (charset == null) {
		 charset = "utf-8";
		 }
		 request.setCharacterEncoding(charset);
		 //		  request.setCharacterEncoding(config.getInitParameter("charset"));
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		config = fConfig;
	}

}
