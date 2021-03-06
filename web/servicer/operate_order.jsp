﻿<%@page import="com.lzjuhuenng.RestaurantSystem.service.impl.TableServiceImpl"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.service.ITableService"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.entery.Order"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodTypeServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.IFoodService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Order"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.OrderItems"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX 点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
</head>
<%	
	ITableService tableService = new TableServiceImpl();
	Map<Integer,String> tableMap = tableService.getTableMap(request);
	List<Order> orderList = (List<Order>) request.getAttribute("orderList");
	
%>
<body>
<div class="testing">
<header class="main">
	<h1><strong>XXXX</strong> 点餐系统</h1>
</header>
<!-- 上面蓝头部-->
<section class="user">
	<div class="profile-img">
		<p><img src="/images/uiface2.png" alt="" height="40" width="40" /> 欢迎回来  ${sessionScope.account.username}</p>
	</div>
	<div class="buttons" style="padding: 15px;" >
		
		<span class="button blue" ><a href="/servlet/LoginOutServlet">Logout</a></span>
	</div>
</section>
<!--黑头部-->
</div>
<nav>
	<ul>
		<li>
			<a href="/servlet/OrderServlet?action=order"><span class="icon">&#128196;</span> 点餐 </a>
		</li>
		<li>
			<a href="/servlet/OrderServlet?action=operateOrder"><span class="icon">&#127748;</span> 订单管理</a>
			<ul class="submenu">
				
			</ul>
		</li>
		<li>
			<a href="/servlet/OrderServlet?action=settle"><span class="icon">&#59160;</span> 结账 </a>
			<ul class="submenu">
				
			</ul>
		</li>
		<li><a href="/servlet/OrderServlet?action=completed"><span class="icon">&#128202;</span> 已完成订单 </a></li>
		<li>
		<li>
		</li>
	</ul>
</nav>
<section class="content" style="margin-top: 25px;">
    <section class="widget">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">在处理订单</font>					
				</div>
			</hgroup>
		</header>
		<div class="content">
			<table id="myTable" border="0" width="100">
				<thead style="text-align: center;">
					<tr>
						<th width="5%" style="text-align: center;padding-left:15px;padding-right:15px;">序号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">订单号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">桌号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">时间</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">状态</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">详情</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">退订</th>
					</tr>
				</thead>
					<tbody style="text-align: center;">
					<%
						int index = 1;
						if(orderList!= null){
							for(Order order : orderList){
								String state;
								 if(order.getState() == 1){
									state = "已提交给厨师";
								}else{
									state = "厨师已接单";
								}
							
					%>
						<tr>
							<td><%=index++ %></td>
							<td><%=order.getOrderNO() %></td>
							<td><%=tableMap.get(order.getTableId()) %></td>
							<td><%=order.getData() %></td>
							<td><%=state %></td>
							<td><a href="/servlet/OrderServlet?action=orderDetails&orderId=<%=order.getId()%>">详情</a></td>
							<td><a href="/servlet/OrderServlet?action=unsubscribe&orderId=<%=order.getId()%>">退订</a></td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
				</table>
			</div>
	</section>
	
	
	<div class="widget-container">
		<div style="height:80px">
		Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a> - More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
		</div>
	</div>
</section>
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery.wysiwyg.js"></script>
<script src="/js/custom.js"></script>
<script src="/js/cycle.js"></script>
<script src="/js/jquery.checkbox.min.js"></script>
<script src="/js/flot.js"></script>
<script src="/js/flot.resize.js"></script>
<script src="/js/flot-time.js"></script>
<script src="/js/flot-pie.js"></script>
<script src="/js/flot-graphs.js"></script>
<script src="/js/cycle.js"></script>
<script src="/js/jquery.tablesorter.min.js"></script>
</body>
</html>