<%@page import="com.lzjuhuenng.restaurantSystem.entery.Page"%>
<%@page import="com.lzjuhuenng.restaurantSystem.service.IFoodTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.restaurantSystem.service.impl.FoodServiceImpl"%>
<%@ page import="com.lzjuhuenng.restaurantSystem.service.impl.FoodTypeServiceImpl"%>        
<%@ page import="com.lzjuhuenng.restaurantSystem.service.IFoodService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lzjuhuenng.restaurantSystem.entery.Food"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/RestaurantSystem/css/style.css" media="all" />
	<link rel="stylesheet" href="/RestaurantSystem/css/pagination.css" />
</head>
<%
	
	Page<Food> foodPage = (Page<Food>)request.getAttribute("foodPage");
	IFoodService foodService = new FoodServiceImpl();
	IFoodTypeService foodtypeService = new FoodTypeServiceImpl();
	List<Food> foodList = foodService.queryAllFoodFormDB(request);
	Map<Integer,String> foodtypeMap = foodtypeService.getAllFoodTypeMap();
	Set<Integer> foodtypeIdSet = foodtypeMap.keySet(); 
	Food food = foodList.get(foodList.size()-1);
	foodList.remove(foodList.size()-1);
	for(Food f : foodList){
		System.out.println(f);
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
		<p><img src="images/uiface2.png" alt="" height="40" width="40" /> Welcome back ${sessionScope.account.username }</p>
	</div>
	<div class="buttons">
		<button class="ico-font">&#9206;</button>
		<span class="button">
			<a href="#">
				message<span class="pip">6</span>
			</a> 
		</span>
		<span class="button blue"><a href="index.html">Logout</a></span>
	</div>
</section>
<!--黑头部-->
</div>
<nav style="height: 450px;">
	<ul>
		<li>
			<a href="admin_operate_food_type.jsp"><span class="icon">&#128196;</span> 菜品种类管理 </a>	
		</li>
		<li>
			<a href="#"><span class="icon">&#127748;</span> 菜品管理 <span class="pip">7</span></a>
			<ul class="submenu">
				<li><a href="admin_add_food.jsp">添加菜品信息</a></li>
				<li><a href="admin_query_food.jsp">菜品信息管理</a></li>
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#59160;</span> 餐桌管理 <span class="pip">12</span></a>
			<ul class="submenu">
				<li><a href="admin_add_table.jsp">添加餐桌信息</a></li>
				<li><a href="admin_query_table.jsp">餐桌信息管理</a></li>
			</ul>
		</li>
		<li><a href="#"><span class="icon">&#128202;</span> 报表统计 </a></li>
		<li>
			<a href="#"><span class="icon">&#128101;</span> 用户管理 <span class="pip">3</span></a>
			<ul class="submenu">
				<li><a href="admin_add_account.jsp">添加用户信息</a></li>
				<li><a href="admin_query_account.jsp">用户信息管理</a></li>
			</ul>
		</li>
	</ul>
</nav>
<section class="content" style="margin-top: 10px;">
	<section class="widget" style="height: 600px">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">菜品信息管理</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
			<form method="get" action="admin_query_food.jsp">
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
						for(Food liFood : foodList){
							if(liFood.getFood_state()!=0){
					%>
						<tr>
							<td><%=index++ %></td>
							<td><%=liFood.getFood_name() %></td>
							<td><%=foodtypeMap.get(liFood.getFood_type())%></td>
							<td><%=liFood.getPrice() %></td>
							<td><a href="/RestaurantSystem/servlet/FoodOperateServlet?action=update&id=<%=liFood.getId()%>">修改</a></td>
							<td><a href="/RestaurantSystem/servlet/FoodOperateServlet?action=del&id=<%=liFood.getId()%>">删除</a></td>
							<td><a href="/RestaurantSystem/servlet/FoodOperateServlet?action=updateState&id=<%=liFood.getId()%>"><%=liFood.getFood_state()==2?"下架":"上架" %></a></td>
						</tr>
						<%
							}
						}
					
						%>
					</tbody>
				</table>
			<div class="text-center" style="position:absolute;bottom:-150px ;padding-left: 270px;padding-right: 270px;">
			<ul class="pagination">
				<li class="disabled"><a href="#">首页</a></li>
				<li class="disabled"><a href="#">上一页</a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">6</a></li>
				<li><a href="#">下一页</a></li>
				<li><a href="#">尾页</a></li>
			</ul>
		</div>
		</div>
		
	</section>
</section>
<script src="/RestaurantSystem/js/jquery.min.js"></script>
<script src="/RestaurantSystem/js/jquery.wysiwyg.js"></script>
<script src="/RestaurantSystem/js/custom.js"></script>
<script src="/RestaurantSystem/js/cycle.js"></script>
<script src="/RestaurantSystem/js/jquery.checkbox.min.js"></script>
<script src="/RestaurantSystem/js/flot.js"></script>
<script src="/RestaurantSystem/js/flot.resize.js"></script>
<script src="/RestaurantSystem/js/flot-time.js"></script>
<script src="/RestaurantSystem/js/flot-pie.js"></script>
<script src="/RestaurantSystem/js/flot-graphs.js"></script>
<script src="/RestaurantSystem/js/cycle.js"></script>
<script src="/RestaurantSystem/js/jquery.tablesorter.min.js"></script>
<script type="text/javascript">
// Feature slider for graphs
$('.cycle').cycle({
	fx: "scrollHorz",
	timeout: 0,
    slideResize: 0,
    prev:    '.left-btn', 
    next:    '.right-btn'
});
</script>
</body>
</html>