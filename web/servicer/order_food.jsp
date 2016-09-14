<%@page import="com.lzjuhuenng.RestaurantSystem.service.ITableService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.TableServiceImpl"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Table"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX 点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
</head>
<%	
	List<Table> tableList = (List<Table>) request.getAttribute("tableList");
	Table table = tableList.get(tableList.size()-1);
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
					<font size="5" color="#F7FfFf">点餐</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
			<form method="post" action="/servlet/OrderServlet">
				<div style="margin-left: 40%;height: 40px;padding-top: 0;margin-bottom: 5px;">
					<input type="hidden"  name="action" value="order"/>
					<table width="100%">
						<tr>
							<td width="13%" align="right">餐桌编号：</td>
							<td width="35%" style="padding-left: 2px;padding-right: 20px;">
								<input type="text"  name="tableno" />
							</td>
							<td width="13%" align="right">餐桌容量：</td>
							<td width="8%" style="padding-left: 2px;">
								<input type="text"  value="<%=table.getMincapacity() == 0?"":table.getMincapacity() %>" name="mincapacity"/>
							</td>
							<td width="1%">—</td>
							<td width="8%" style="padding-left: 0px;margin-right: 20px;">
								<input type="text"  value="<%=table.getMaxcapacity() == 0?"":table.getMaxcapacity() %>" name="maxcapacity"/>
							</td>
							<td style="padding-left: 5%;">
								<input type="submit" value="查询" />								
							</td>
						</tr>
					</table>
				</div>
			</form>
			<table id="myTable" border="0" width="100" >
				<thead style="text-align: center;">
					<tr>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">序号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">餐桌编号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">餐桌容量</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">操作</th>
					</tr>
				</thead>
					<tbody style="text-align: center;">
						<%	
							int index = 1;
							for(Table Ltable : tableList){
								if(Ltable.getState()==1){								
							
						%>
						<tr>
							<td><%=index++ %></td>
							<td><%=Ltable.getTable_no() %></td>
							<td><%=Ltable.getCapacity() %></td>
							<td><a href="/servlet/OrderServlet?action=generate&tableId=<%=Ltable.getId()%>">选中</a></td>
						</tr>
						<%
								}
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