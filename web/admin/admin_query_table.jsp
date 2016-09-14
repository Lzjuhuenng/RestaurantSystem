<%@page import="com.lzjuhuenng.restaurantSystem.service.IFoodTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.restaurantSystem.service.impl.TableServiceImpl"%>
<%@ page import="com.lzjuhuenng.restaurantSystem.service.ITableService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lzjuhuenng.restaurantSystem.entery.Table"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/RestaurantSystem/css/style.css" media="all" />
	<link rel="stylesheet" href="/RestaurantSystem/css/pagination.css" />
</head>
<%
	ITableService tableService = new TableServiceImpl();
	List<Table> tableList = tableService.queryTableFromDB(request);
	Table ptable = tableList.get(tableList.size()-1);
	tableList.remove(tableList.size()-1);

%>
<body>
<div class="testing">
<header class="main">
	<h1><strong>XXXX</strong> 点餐系统</h1>
</header>
<!-- 上面蓝头部-->
<section class="user">
	<div class="profile-img">
		<p><img src="images/uiface2.png" alt="" height="40" width="40" />欢迎回来 ${sessionScope.account.username }</p>
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
	<section class="widget" style="height: 500px">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">餐桌信息管理</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
			<form method="get" action="admin_query_table.jsp">
				<div style="margin-left: 40%;height: 40px;padding-top: 0;margin-bottom: 5px;">
					<table width="100%">
						<tr>
							<td width="13%" align="right">餐桌编号：</td>
							<td width="35%" style="padding-left: 2px;padding-right: 20px;">
								<input type="text" value="<%=ptable.getTable_no()!=null?ptable.getTable_no():"" %>" name="tableno"  />
							</td>
							<td width="13%" align="right">餐桌容量：</td>
							<td width="8%" style="padding-left: 2px;">
								<input type="text" value="<%=ptable.getMincapacity()>0?ptable.getMincapacity():"" %>" name="mincapacity" />
							</td>
							<td width="1%">—</td>
							<td width="8%" style="padding-left: 0px;margin-right: 20px;">
								<input type="text" value="<%=ptable.getMaxcapacity()>0?ptable.getMaxcapacity():"" %>" name="maxcapacity" />
							</td>
							<td style="padding-left: 5%;">
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
						<th style="text-align: center;padding-left:15px;padding-right:15px;">餐桌编号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">餐桌容量</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">修改</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">删除</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">操作</th>
					</tr>
				</thead>
					<tbody style="text-align: center;">
					
					<%	
						int index = 1; 
						for(Table table : tableList){
					%>
						<tr>
							<td><%=index++ %></td>
							<td><%=table.getTable_no() %></td>
							<td><%=table.getCapacity() %></td>
							<td><a href="/RestaurantSystem/servlet/TableOperateServlet?action=update&id=<%=table.getId()%>">修改</a></td>
							<td><a href="/RestaurantSystem/servlet/TableOperateServlet?action=del&id=<%=table.getId()%>">删除</a></td>
							<td><a href="/RestaurantSystem/servlet/TableOperateServlet?action=updateState&id=<%=table.getId()%>"><%=table.getState()==1?"不可使用":"可使用" %></a></td>
						</tr>
					<%
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