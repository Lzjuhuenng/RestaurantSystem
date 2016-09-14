<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.AccountServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.AccountTypeServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.IAccountService"%>
<%@ page import="java.util.*"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.service.IAccountTypeService"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Account"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
	<link rel="stylesheet" href="/css/pagination.css" />
</head>
<%	
	Account ac = (Account)session.getAttribute("account");
	IAccountService accService = new AccountServiceImpl();
	IAccountTypeService accTypeService = new AccountTypeServiceImpl();
	List<Account> accList = accService.queryAccountFromDB(request);
	Account acc = accList.get(accList.size()-1);
	accList.remove(accList.size()-1);
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
		<p><img src="images/uiface2.png" alt="" height="40" width="40" /> 欢迎回来 ${sessionScope.account.username }</p>
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
	<section class="widget" style="height: 630px">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">用户账号管理</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
			<form method="get" action="admin_query_account.jsp">
				<div style="margin-left: 10%;height: 40px;padding-top: 0;margin-bottom: 5px;">
						<table width="100%">
						<tr>
							<td width="10%" align="right">
							帐号名称：
							</td>
							<td width="20%">
								<input type="text"  value="<%=acc.getAccount()==null?"":acc.getAccount() %>" name="account"/>
							</td>
							<td width="10%" align="right">
							用户姓名：
							</td>
							<td width="20%">
								<input type="text" value="<%=acc.getUsername()==null?"":acc.getUsername() %>" name="username" />
							</td>
							<td width="10%" align="right">
								用户角色：
							</td>
							<td width="20%">
								<select name="role" style="width: 100%;padding: 10px;margin: 0 0 15px 0;border: 1px solid #e3e3e3;">
									<%
									if(acc.getRole()>0){
									%>
									<option value="<%=acc.getRole() %>"><%=accTypeMap.get(acc.getRole()) %></option>
									<%
									}
									%>
									<option></option>
									<%
									for(int role : roleSet){
										if(role == acc.getRole() || role >= ac.getRole()){
											continue;
										}
									%>
									<option value="<%=role %>"><%=accTypeMap.get(role) %></option>
									<%	
									}
									%>
								</select>
							</td >
							
							<td style="padding-left:5% ;">
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
						<th style="text-align: center;padding-left:15px;padding-right:15px;">用户账号</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">用户姓名</th>
						<th style="text-align: center;padding-left:15px;padding-right:15px;">用户角色</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">修改</th>
						<th style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">删除</th>
					</tr>
				</thead>
					<tbody style="text-align: center;">
					
					<%
					
					int index = 1;
					for(Account acco : accList){
						if(acco.getRole() >= ac.getRole()){
							continue;
						}
					%>
					<tr>
							<td><%=index++ %></td>
							<td><%=acco.getAccount() %></td>
							<td><%=acco.getUsername() %></td>
							<td><%=accTypeMap.get(acco.getRole())%></td>
							<td><a href="/RestaurantSystem/servlet/AccountOperateServlet?action=update&id=<%=acco.getId()%>"">修改</a></td>
							<td><a href="/RestaurantSystem/servlet/AccountOperateServlet?action=del&id=<%=acco.getId()%>">删除</a></td>
						</tr>
					<%	
					}
					
					%>
					</tbody>
				</table>
				<div class="text-center" style="position:absolute;bottom:-180px ;padding-left: 270px;padding-right: 270px;">
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