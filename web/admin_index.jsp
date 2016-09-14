<%@page import="org.jfree.chart.title.TextTitle"%>

<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@page import="org.jfree.data.time.TimeSeries" %>
<%@page import="org.jfree.data.time.TimeSeriesCollection" %>
<%@page import="org.jfree.data.time.Day" %>
<%@page import="org.jfree.data.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/RestaurantSystem/css/style.css" media="all" />
</head>
<body>
<div class="testing">
<header class="main">
	<h1><strong>XXXX</strong> 点餐系统</h1>
</header>
<!-- 上面蓝头部-->
<section class="user">
	<div class="profile-img">
		<p><img src="images/uiface2.png" alt="" height="40" width="40" /> 欢迎回来 ${sessionScope.account.username} </p>
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
<nav>
	<ul>
		<li>
			<a href="/RestaurantSystem/admin/admin_operate_food_type.jsp"><span class="icon">&#128196;</span> 菜品种类管理 </a>
		</li>
		<li>
			<a href="#"><span class="icon">&#127748;</span> 菜品管理 <span class="pip">7</span></a>
			<ul class="submenu">
				<li><a href="/RestaurantSystem/admin/admin_add_food.jsp">添加菜品信息</a></li>
				<li><a href="/RestaurantSystem/servlet/FoodOperateServlet?action=query">菜品信息管理</a></li>
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#59160;</span> 餐桌管理 <span class="pip">12</span></a>
			<ul class="submenu">
				<li><a href="/RestaurantSystem/admin/admin_add_table.jsp">添加餐桌信息</a></li>
				<li><a href="/RestaurantSystem/servlet/TableOperateServlet?action=query">餐桌信息管理</a></li>
			</ul>
		</li>
		<li><a href="statistics.html"><span class="icon">&#128202;</span> 报表统计 </a></li>
		<li>
			<a href="users.html"><span class="icon">&#128101;</span> 用户管理 <span class="pip">3</span></a>
			<ul class="submenu">
				<li><a href="/RestaurantSystem/admin/admin_add_account.jsp">添加用户信息</a></li>
				<li><a href="/RestaurantSystem/servlet/AccountOperateServlet?action=query">用户信息管理</a></li>
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
			timeSeries.add(new Day(11,9,2016), 110);
			timeSeries.add(new Day(10,9,2016), 11);
			timeSeries.add(new Day(9,9,2016), 11);
			timeSeries.add(new Day(8,9,2016), 11);
			timeSeries.add(new Day(7,9,2016), 11);
			timeSeries.add(new Day(6,9,2016), 11);
			timeSeries.add(new Day(5,9,2016), 11);
			timeSeries.add(new Day(4,9,2016), 11);
			timeSeries.add(new Day(3,9,2016), 11);
			timeSeries.add(new Day(2,9,2016), 11);
			lineDataset.addSeries(timeSeries);
			JFreeChart chart = ChartFactory.createTimeSeriesChart("营业额", "日期", "金额", lineDataset, true, false, false);
			TextTitle subTitle = new TextTitle("2016年9月");
			chart.addSubtitle(subTitle);
			
			chart.setTitle(new TextTitle("张海每月考试成绩"));
			chart.setAntiAlias(true);
			String filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, session);
			String graphURL = request.getContextPath()+"/Display?filename="+filename;
			
			%>
			<img src='<%=graphURL %>'>
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