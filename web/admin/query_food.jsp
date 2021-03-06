﻿<%@page import="com.lzjuhuenng.RestaurantSystem.entery.Page"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodTypeServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.IFoodService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Food"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX点餐系统</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="robots" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
	<link rel="stylesheet" href="/css/pagination.css" />
	<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
	<!--[if lt IE 9]><link rel="stylesheet" href="css/lt-ie-9.css" media="all" /><![endif]-->
</head>
<%
	Page<Food> foodPage = (Page<Food>) request.getAttribute("foodPage");
	IFoodTypeService foodtypeService = new FoodTypeServiceImpl();
	Map<Integer,String> foodtypeMap = foodtypeService.getAllFoodTypeMap();
	Set<Integer> foodtypeIdSet = foodtypeMap.keySet(); 
	Food food = foodPage.getResultList().get(foodPage.getResultList().size()-1);
	foodPage.getResultList().remove(foodPage.getResultList().size()-1);
	
	String urlParam ="";
	if(request.getParameter("foodname")!=null&&!"".equals(request.getParameter("foodname"))){
		urlParam += "&foodname="+request.getParameter("foodname");
	}
	if(request.getParameter("foodtype")!=null&&!"".equals(request.getParameter("foodtype"))){
		urlParam += "&foodtype="+request.getParameter("foodtype");
	}
	if(request.getParameter("minprice")!=null&&!"".equals(request.getParameter("minprice"))){
		urlParam += "&minprice="+request.getParameter("minprice");
	}
	if(request.getParameter("maxprice")!=null&&!"".equals(request.getParameter("maxprice"))){
		urlParam += "&maxprice="+request.getParameter("maxprice");
	}
%>
<body>
<div class="testing">
<header class="main">
	<h1><strong>XXXX</strong>点餐系统</h1>
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
<nav style="height: 450px;">
	<ul>
		<li>
			<a href="/admin/admin_operate_food_type.jsp"><span class="icon">&#128196;</span> 菜品种类管理 </a>
		</li>
		<li>
			<a href="#"><span class="icon">&#127748;</span> 菜品管理</a>
			<ul class="submenu">
				<li><a href="/admin/admin_add_food.jsp">添加菜品信息</a></li>
				<li><a href="/servlet/FoodOperateServlet?action=query">菜品信息管理</a></li>
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#59160;</span> 餐桌管理 </a>
			<ul class="submenu">
				<li><a href="/admin/admin_add_table.jsp">添加餐桌信息</a></li>
				<li><a href="/admin/admin_query_table.jsp">餐桌信息管理</a></li>
			</ul>
		</li>
		<li><a href="#"><span class="icon">&#128202;</span> 报表统计 </a>
			<ul class="submenu">
				<li><a href="/admin/admin_form1.jsp">近七天营业情况</a></li>
				<li><a href="/admin/admin_form2.jsp">近十五天营业情况</a></li>
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#128101;</span> 用户管理 </a>
			<ul class="submenu">
				<li><a href="/admin/admin_add_account.jsp">添加用户信息</a></li>
				<li><a href="/servlet/AccountOperateServlet?action=query">用户信息管理</a></li>
			</ul>
		</li>
	</ul>
</nav>
<section class="content" style="margin-top: 10px;">
	<section class="widget" style="height: 650px">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">菜品信息管理</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
			<form method="get" action="/servlet/FoodOperateServlet">
			<input type="hidden" name="action" value="query">
				<div style="margin-left: 10%;height: 40px;padding-top: 0;margin-bottom: 5px;">
					<table width="100%">
						<tr>
							<td width="10%" align="right">
							菜品名称：
							</td>
							<td width="20%">
								<input type="text" value="<%=food.getFood_name()==null?"":food.getFood_name() %>" id="foodname"  name="foodname"/>
							</td>
							<td width="10%" align="right">
								菜品种类：
							</td>
							<td width="20%">
								<select id="foodtype" name="foodtype" style="width: 100%;padding: 10px;margin: 0 0 15px 0;border: 1px solid #e3e3e3;">
									<%
									if(food.getFood_type()!= 0){
									%>
									<option value="<%=food.getFood_type() %>"><%=foodtypeMap.get(food.getFood_type()) %></option>
									<%
									}
									%>
									<option> </option>
									<% 
										for(Integer foodtypeIDInteger : foodtypeIdSet){
											if(foodtypeIDInteger == food.getFood_type()){
												continue;
											}
									%>
									<option value="<%=foodtypeIDInteger%>"><%=foodtypeMap.get(foodtypeIDInteger) %></option>
									<% 	
										}
									
									%>
								</select>
							</td >
							<td width="10%" align="right">菜品价格：</td>
							<td width="5%">
								<input type="text" value="<%=food.getMin_price()>0?food.getMin_price(): "" %>" id="minprice" name="minprice" />
							</td>
							<td width="1%">—</td>
							<td width="5%">
								<input type="text" value="<%=food.getMax_price()>0?food.getMax_price(): "" %>" id="maxprice" name="maxprice"/>
							</td>
							<td style="padding-left:8% ;">
								<input type="submit" value="查询" />								
							</td>
						</tr>
					</table>
				</div>
			</form>
			<table id="myTable" border="0" width="100">
				<thead style="text-align: center;">
					<tr>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">序号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">菜品名称</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">菜品种类</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">菜品价格</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">修改</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">删除</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">操作</th>
					</tr>
				</thead>
					<tbody style="text-align: center;">
					<%	
						int index =1;
						String type = "";
						for(Food liFood : foodPage.getResultList()){
							if(liFood.getFood_state()!=0){
					%>
						<tr>
							<td><%=index++ %></td>
							<td><%=liFood.getFood_name() %></td>
							<td><%=foodtypeMap.get(liFood.getFood_type())%></td>
							<td><%=liFood.getPrice() %></td>
							<td><a href="/servlet/FoodOperateServlet?action=update&id=<%=liFood.getId()%>">修改</a></td>
							<td><a href="/servlet/FoodOperateServlet?action=del&id=<%=liFood.getId()%>">删除</a></td>
							<td><a href="/servlet/FoodOperateServlet?action=updateState&id=<%=liFood.getId()%>"><%=liFood.getFood_state()==2?"下架":"上架" %></a></td>
						</tr>
						<%
							}
						}
					
						%>
					</tbody>
				</table>
			<div class="text-center" style="position:absolute;bottom:-220px ;padding-left: 270px;padding-right: 270px;">
			<ul class="pagination">
				
				<li <%=foodPage.getPageNo()==0?"class='disabled'":"" %> ><a href="/servlet/FoodOperateServlet?action=query<%=urlParam %>&pageNo=0">首页</a></li>
				<li <%=foodPage.getPageNo()==0?"class='disabled'":"" %> ><a href="/servlet/FoodOperateServlet?action=query<%=urlParam %>&pageNo=<%=foodPage.getPageNo()-1 %>">上一页</a></li>
				<% 
					for(int i = foodPage.getPageNo()-2,j=0;j < 6 &&i<=foodPage.getTotalPageNo();i++,j++ ){
						if(i<0 ){
							continue;
						}
				
				%>
				<li <%=foodPage.getPageNo()==i?"class='active'":"" %> ><a href='/servlet/FoodOperateServlet?action=query<%=urlParam %>&pageNo=<%=i %>'><%=i+1 %></a></li>
				
				<% 
				}
				%>
				<li <%=foodPage.getTotalPageNo()==foodPage.getPageNo()?"class='disabled'":"" %> ><a href="/servlet/FoodOperateServlet?action=query<%=urlParam %>&pageNo=<%=foodPage.getPageNo()+1 %>">下一页</a></li>
				<li <%=foodPage.getTotalPageNo()==foodPage.getPageNo()?"class='disabled'":"" %> ><a href="/servlet/FoodOperateServlet?action=query<%=urlParam %>&pageNo=<%=foodPage.getTotalPageNo() %>">尾页</a></li>
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