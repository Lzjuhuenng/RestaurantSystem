<%@page import="java.util.Map.Entry"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.entery.ChartParam"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.service.impl.ChartServiceImpl"%>
<%@page import="com.lzjuhuenng.RestaurantSystem.service.IChartService"%>
<%@page import="org.jfree.chart.title.TextTitle"%>

<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@page import="org.jfree.data.time.TimeSeries" %>
<%@page import="org.jfree.data.time.TimeSeriesCollection" %>
<%@page import="org.jfree.data.time.Day" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
</head>
<%
	IChartService chartService = new ChartServiceImpl();
	ChartParam chartParam = chartService.getSevenDayIncome();
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
		<li>
		</li>
	</ul>
</nav>
<section class="content" style="margin-top: 25px;">
    <section class="widget">
		<header>
			<span class="icon">&#128200;</span>
			<hgroup>
				<h1>近期营业概况</h1>
			</hgroup>
			<aside>
				<button class="left-btn">&#59229;</button><button class="right-btn">&#59230;</button>
			</aside>
		</header>
		<div class="content">
			<%
			TimeSeriesCollection lineDataset = new TimeSeriesCollection();
			TimeSeries timeSeries = new TimeSeries("营业额",Day.class);
			Map<String,String> map =  chartParam .getMapParam();
			Set<Entry<String,String>> entrySet = map.entrySet();
			for(Entry<String,String> entry : entrySet){
				String data = entry.getKey();
				String money = entry.getValue();
				String[] strs = data.split("-");
			timeSeries.add(new Day(Integer.parseInt(strs[2]),Integer.parseInt(strs[1]),Integer.parseInt(strs[0])), Double.parseDouble(money));
				
				
			}
			lineDataset.addSeries(timeSeries);
			JFreeChart chart = ChartFactory.createTimeSeriesChart("营业额", "日期", "金额", lineDataset, true, false, false);
			
			
			chart.setTitle(new TextTitle("近七天营业额"));
			chart.setAntiAlias(true);
			String filename = ServletUtilities.saveChartAsPNG(chart, 870, 400, session);
			String graphURL = request.getContextPath()+"/Display?filename="+filename;
			%>
			<img src='<%=graphURL %>'>
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