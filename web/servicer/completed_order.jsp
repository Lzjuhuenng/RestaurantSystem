<%@page import="com.lzjuhuenng.RestaurantSystem.service.impl.TableServiceImpl"%>
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
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Page"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.OrderItems"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX 点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
	<link rel="stylesheet" href="/css/pagination.css" />
</head>
<%	
	ITableService tableService = new TableServiceImpl();
	Map<Integer,String> tableMap = tableService.getTableMap(request);
	Page<Order> orderPage = (Page<Order>) request.getAttribute("orderPage");
	String urlParam  = "";
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
			<a href="/servlet/OrderServlet?action=operateOrder"><span class="icon">&#127748;</span> 订单管理 </a>
			<ul class="submenu">
				
			</ul>
		</li>
		<li>
			<a href="/servlet/OrderServlet?action=settle"><span class="icon">&#59160;</span> 结账</a>
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
    <section class="widget" style="height:630px;">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">已完成订单</font>					
				</div>
			</hgroup>
		</header>
		<div class="content">
			<table id="myTable" border="0" width="100">
				<thead style="text-align: center;">
					<tr>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">序号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">订单号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">桌号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">时间</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">金额</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">详情</th>
					</tr>
				</thead>
					<tbody style="text-align: center;">
					<%
						int index = 1;
						if(orderPage!=null &&orderPage.getResultList()!= null){
							for(Order order : orderPage.getResultList()){
								request.setAttribute("order"+order.getId(), order);
							
					%>
						<tr>
							<td><%=index++ %></td>
							<td><%=order.getOrderNO() %></td>
							<td><%=tableMap.get(order.getTableId()) %></td>
							<td><%=order.getData() %></td>
							<td><%=order.getTotleMoney() %></td>
							<td><a href="/servlet/OrderServlet?action=completedOrderDetails&orderId=<%=order.getId()%>">详情</a></td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
			</table>
			<div class="text-center" style="position:absolute;bottom:-200px ;padding-left: 270px;padding-right: 270px;">
			<ul class="pagination">
				
				<li <%=orderPage.getPageNo()==0?"class='disabled'":"" %> ><a href="/servlet/AccountOperateServlet?action=query<%=urlParam %>&pageNo=0">首页</a></li>
				<li <%=orderPage.getPageNo()==0?"class='disabled'":"" %> ><a href="/servlet/AccountOperateServlet?action=query<%=urlParam %>&pageNo=<%=orderPage.getPageNo()-1 %>">上一页</a></li>
				<% 
					for(int i = orderPage.getPageNo()-2,j=0;j < 6 &&i<=orderPage.getTotalPageNo();i++,j++ ){
						if(i<0 ){
							continue;
						}
				
				%>
				<li <%=orderPage.getPageNo()==i?"class='active'":"" %> ><a href='/servlet/AccountOperateServlet?action=query<%=urlParam %>&pageNo=<%=i %>'><%=i+1 %></a></li>
				
				<% 
				}
				%>
				<li <%=orderPage.getTotalPageNo()==orderPage.getPageNo()?"class='disabled'":"" %> ><a href="/servlet/AccountOperateServlet?action=query<%=urlParam %>&pageNo=<%=orderPage.getPageNo()+1 %>">下一页</a></li>
				<li <%=orderPage.getTotalPageNo()==orderPage.getPageNo()?"class='disabled'":"" %> ><a href="/servlet/AccountOperateServlet?action=query<%=urlParam %>&pageNo=<%=orderPage.getTotalPageNo() %>">尾页</a></li>
			</ul>
		</div>
		</div>
	</section>
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