<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.FoodType,java.util.*" %>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService" %>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodTypeServiceImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>菜品种类管理</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
</head>
<%
	IFoodTypeService foodtypeService = new FoodTypeServiceImpl();
	List<FoodType> foodtypeList = foodtypeService.getAllFoodType();
%>
<body>
<div class="testing">
<header class="main">
	<h1><strong>XXXX</strong> 点餐系统</h1>
</header>
<!-- 上面蓝头部-->
<section class="user">
	<div class="profile-img">
		<p><img src="/RestaurantSystem/images/uiface2.png" alt="" height="40" width="40" /> 欢迎回来  ${sessionScope.account.username}</p>
	</div>
	<div class="buttons" style="padding: 15px;" >
		
		<span class="button blue" ><a href="/servlet/LoginOutServlet">Logout</a></span>
	</div>
</section>
<!--黑头部-->
</div>
<nav style="height: 700px;">
	<ul>
		<li>
			<a href="/admin/admin_operate_food_type.jsp"><span class="icon">&#128196;</span> 菜品种类管理 </a>
		</li>
		<li>
			<a href="#"><span class="icon">&#127748;</span> 菜品管理 </a>
			<ul class="submenu">
				<li><a href="/admin/admin_add_food.jsp">添加菜品信息</a></li>
				<li><a href="/servlet/FoodOperateServlet?action=query">菜品信息管理</a></li>
				
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#59160;</span> 餐桌管理</a>
			<ul class="submenu">
			<li><a href="/admin/admin_add_table.jsp">添加餐桌信息</a></li>
				<li><a href="/servlet/TableOperateServlet?action=query">餐桌信息管理</a></li>
			</ul>
		</li>
		<li><a href="#"><span class="icon">&#128202;</span> 报表统计 </a>
			<ul class="submenu">
				<li><a href="/admin/admin_form1.jsp">近七天营业情况</a></li>
				<li><a href="/admin/admin_form2.jsp">近十五天营业情况</a></li>
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#128101;</span> 用户管理</a>
			<ul class="submenu">
			<li><a href="/admin/admin_add_account.jsp">添加用户信息</a></li>
				<li><a href="/servlet/AccountOperateServlet?action=query">用户信息管理</a></li>
			</ul>
		</li>
	</ul>
</nav>
<section class="content" style="margin-top: 10px;">
	<section class="widget" style="height: 700px">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">菜品种类管理</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
			<form method="get" action="/servlet/FoodTypeOperateServlet">
				<div style="margin-left: 58%;height: 40px;padding-top: 0;margin-bottom: 5px;">
					<table width="100%" cellSpacing="10px">
						<tr>
							<td width="80%" style="padding-left: 20px;">
								<input type="hidden" name="action" value="add"/>
								<input type="text" name="foodtype" />
							</td>
							<td>
								<input type="submit" value="添加" />								
							</td>
						</tr>
					</table>
				</div>
			</form>
			<table id="myTable" border="0" width="100">
				<thead style="text-align: center;">
					<tr>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">序号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">菜品种类</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">删除</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">操作</th>
					</tr>
				</thead>
					<tbody style="text-align: center;">
					<%  int index = 1;
						for(FoodType foodtype : foodtypeList){
					%>
						<tr>
							<td><%=index++ %></td>
							<td><%=foodtype.getType()%></td>
							<td><a href="/servlet/FoodTypeOperateServlet?action=del&id=<%=foodtype.getId() %>"> 删除</a></td>
							<td><a href="/servlet/FoodTypeOperateServlet?action=updateState&id=<%=foodtype.getId()%>"><%=foodtype.getState()== 1?"上架":"下架"%></td>
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