<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Account,java.util.*"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.AccountTypeServiceImpl"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.service.IAccountTypeService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
	<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
	<!--[if lt IE 9]><link rel="stylesheet" href="css/lt-ie-9.css" media="all" /><![endif]-->
</head>
<%
Account ac = (Account)session.getAttribute("account");
Account acc = (Account)request.getAttribute("Account");
IAccountTypeService accTypeService = new AccountTypeServiceImpl();
Map<Integer,String> accTypeMap = accTypeService.getAccountTypeMap();
Set<Integer> roleSet = accTypeMap.keySet();
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
			<a href="admin_operate_food_type.jsp"><span class="icon">&#128196;</span> 菜品种类管理 </a>	
		</li>
		<li>
			<a href="#"><span class="icon">&#127748;</span> 菜品管理 </a>
			<ul class="submenu">
				<li><a href="admin_add_food.jsp">添加菜品信息</a></li>
				<li><a href="admin_query_food.jsp">菜品信息管理</a></li>
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#59160;</span> 餐桌管理 </a>
			<ul class="submenu">
				<li><a href="admin_add_table.jsp">添加餐桌信息</a></li>
				<li><a href="admin_query_table.jsp">餐桌信息管理</a></li>
			</ul>
		</li>
		<li><a href="#"><span class="icon">&#128202;</span> 报表统计 </a>
			<ul class="submenu">
				<li><a href="/admin/admin_form1.jsp">近七天营业情况</a></li>
				<li><a href="/admin/admin_form2.jsp">近十五天营业情况</a></li>
			</ul>
		</li>
		<li>
			<a href="users.html"><span class="icon">&#128101;</span> 用户管理 </a>
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
					<font size="5" color="#F7FfFf">添加账户信息</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
			<form method="post" action="/servlet/AccountOperateServlet">
				<div style="height: 40px;padding-top: 0;margin-bottom: 5px;width:50%;margin-left:12% ;margin-top: 7%;">
				<input type="hidden" value="updateAccount" name="action"/>
				<input type="hidden" value="<%=acc.getId() %>" name="id"/>
				<input type="hidden" value="<%=acc.getAccount() %>" name="account"/>
					<table width="100%" cellSpacing="10px">
						<tr>
							<td width="25%" style="padding-left: 20px;">
								<font size="3"> 用户名称：</font>
							</td>
							<td>
								<input type="text" value="<%=acc.getAccount() %>" disabled="disabled" />								
							</td>
						</tr>
						<tr>
							<td width="25%" style="padding-left: 20px;">
								<font size="3"> 用户密码：</font>
							</td>
							<td>
								<input type="text" value="<%=acc.getPassword() %>" name="password" />								
							</td>
						</tr>
						<tr>
							<td width="25%" style="padding-left: 20px;">
									<font size="3"> 用户姓名：</font>
							</td>
							<td>
								<input type="text" value="<%=acc.getUsername() %>" name="username" />								
							</td>
						</tr>
						<tr>
							<td width="25%" style="padding-left: 20px;">
								<font size="3"> 用户角色：</font>
							</td>
							<td>
								<select name="role" style="width: 100%;padding: 10px;margin: 0 0 15px 0;border: 1px solid #e3e3e3;">
								
								<option value="<%=acc.getRole()%>"><%=accTypeMap.get(acc.getRole()) %></option>
								<%
								for(int role : roleSet){
									if(role >= ac.getRole()||role == acc.getRole()){
										continue;
									}
								
								%>
									<option value="<%=role%>"><%=accTypeMap.get(role) %></option>
								<%
								}
								%>
								</select>
							</td>
						</tr>
						</table>
						<table width="100%" cellSpacing="10px" style="margin-top:20px;">
						<tr>
							<td width="50%" align="right">
								<div style="margin-left: 60%;margin-right: 5%;">
								<input type="submit" value="提交" style="padding: 10px 15px;color: #fff;margin: 0 0 0 5px;background-color: #1483CA;"/>
								</div>
							</td>
							<td width="50%" align="left">
								<div style="margin-right: 60%;margin-left: 5%;">
									<input type="reset" value="重置" style="padding: 10px 15px;color: #fff;margin: 0 0 0 5px;background-color: #1483CA;"/>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
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
