<%@page import="com.lzjuhuenng.RestaurantSystem.entery.Page"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.TableServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.ITableService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Table"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/RestaurantSystem/css/style.css" media="all" />
	<link rel="stylesheet" href="/RestaurantSystem/css/pagination.css" />
</head>
<%	

	Page<Table> tablePage = (Page<Table>) request.getAttribute("tablePage");
	Table ptable = tablePage.getResultList().get(tablePage.getResultList().size()-1);
	tablePage.getResultList().remove(tablePage.getResultList().size()-1);
	String urlParam ="";
	if(request.getParameter("tableno")!=null&&!"".equals(request.getParameter("tableno"))){
		urlParam +="&tableno="+request.getParameter("tableno");
	}
	if(request.getParameter("mincapacity")!=null&&!"".equals(request.getParameter("mincapacity"))){
		urlParam +="&mincapacity="+request.getParameter("mincapacity");
	}
	if(request.getParameter("maxcapacity")!=null&&"".equals(request.getParameter("maxcapacity"))){
		urlParam +="&maxcapacity="+request.getParameter("maxcapacity");		
	}
	
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
	<section class="widget" style="height: 630px">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">餐桌信息管理</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
			<form method="get" action="/servlet/TableOperateServlet">
				<input type="hidden" name="action" value="query"> 
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
						for(Table table : tablePage.getResultList()){
					%>
						<tr>
							<td><%=index++ %></td>
							<td><%=table.getTable_no() %></td>
							<td><%=table.getCapacity() %></td>
							<td><a href="/servlet/TableOperateServlet?action=update&id=<%=table.getId()%>">修改</a></td>
							<td><a href="/servlet/TableOperateServlet?action=del&id=<%=table.getId()%>">删除</a></td>
							<td><a href="/servlet/TableOperateServlet?action=updateState&id=<%=table.getId()%>"><%=table.getState()==1?"不可使用":"可使用" %></a></td>
						</tr>
					<%
						}
					%>
					</tbody>
				</table>
			<div class="text-center" style="position:absolute;bottom:-180px ;padding-left: 270px;padding-right: 270px;">
			<ul class="pagination">
				
				<li <%=tablePage.getPageNo()==0?"class='disabled'":"" %> >
					<a href="/servlet/TableOperateServlet?action=query<%=urlParam %>&pageNo=0">
						首页
					</a>
				</li>
				<li <%=tablePage.getPageNo()==0?"class='disabled'":"" %> >
					<a href="/servlet/TableOperateServlet?action=query<%=urlParam %>&pageNo=<%=tablePage.getPageNo()-1 %>">
					上一页
					</a>
				</li>
				<% 
					for(int i = tablePage.getPageNo()-2,j=0;j < 6 &&i<=tablePage.getTotalPageNo();i++,j++ ){
						if(i<0 ){
							continue;
						}
				
				%>
				<li <%=tablePage.getPageNo()==i?"class='active'":"" %> >
					<a href='/servlet/TableOperateServlet?action=query<%=urlParam %>&pageNo=<%=i %>'>
						<%=i+1 %>
					</a>
				</li>
				
				<% 
				}
				%>
				<li <%=tablePage.getTotalPageNo()==tablePage.getPageNo()?"class='disabled'":"" %> >
					<a href="/servlet/TableOperateServlet?action=query<%=urlParam %>&pageNo=<%=tablePage.getPageNo()+1 %>">
					下一页
					</a>
				</li>
				<li <%=tablePage.getTotalPageNo()==tablePage.getPageNo()?"class='disabled'":"" %> >
					<a href="/servlet/TableOperateServlet?action=query<%=urlParam %>&pageNo=<%=tablePage.getTotalPageNo() %>">
						尾页
					</a>
				</li>
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