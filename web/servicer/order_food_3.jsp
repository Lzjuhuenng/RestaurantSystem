<%@page import="com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodTypeServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.IFoodService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.FoodItem"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.OrderItems"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX 点餐系统</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="robots" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />


</head>

  
<%
	IFoodService foodService = new FoodServiceImpl();
	OrderItems orderItems =(OrderItems) session.getAttribute("orderItems");
	Map<Integer,String> foodMap = foodService.getFoodMap();
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
			<a href="#"><span class="icon"></span> &nbsp;&nbsp;&nbsp;&nbsp; </a>
		</li>
		<li>
			<a href="#"><span class="icon">&#128196;</span> 点餐 </a>
		</li>
		<li>
			<a href="#"><span class="icon">&#127748;</span> 订单管理  </a>
			<ul class="submenu">
				
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#59160;</span> 结账 </a>
			<ul class="submenu">
				
			</ul>
		</li>
		<li><a href="#"><span class="icon">&#128202;</span> 已完成订单 </a></li>
		<li>
	</ul>
</nav>
<section class="content" style="margin-top: 25px;">
    <section class="widget">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">点餐</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
				<div style="margin-left: 80%;height: 40px;padding-top: 0;margin-bottom: 5px;">
				
					<table width="100%">
					<tr>
					<td style="padding-right: 10px;">
						<a href="/servlet/OrderServlet?action=submit">
							<input type="button" value="提交" style="min-width:0px ;background-color: #e63b3b;color: #f7f7f7;"/>
						</a>
					</td>
					<td style="padding-right: 10px;">
						<a href="/servlet/OrderServlet?action=continue">
							<input type="button" value="继续点餐" style="min-width:0px ;background-color: #ff4700;color: #f7f7f7;"/>
						</a>
					</td>
					</tr>
					</table>
				</div>
			<table id="myTable" border="0" width="100">
				<thead style="text-align: center;">
					<tr>
						<th style="text-align: center;padding-left:15px;padding-right:15px;width: 20%;">序号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;min-width: 0px;">菜品名称</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">菜品数量</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">菜品价格</th>
						<!-- <th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">删除</th> -->
					</tr>
				</thead>
					<tbody style="text-align: center;">
					<%
						int index=1;
						for(FoodItem foodItem : orderItems.foodList){
					
					%>
						<tr>
							<td ><%=index++ %></td>
							<td><%=foodMap.get(foodItem.getFoodId()) %></td>
							<td><%=foodItem.getNum()%></td>
							<td><%=foodItem.getPrice() %></td>
							<!--  <td>删除</td> -->
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
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