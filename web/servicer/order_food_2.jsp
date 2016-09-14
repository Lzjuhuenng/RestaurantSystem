<%@page import="com.lzjuhuenng.RestaurantSystem.service.IFoodTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.impl.FoodTypeServiceImpl"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.service.IFoodService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Food"%>
<%@ page import="com.lzjuhuenng.RestaurantSystem.entery.Page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XXXX 点餐系统</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
	<link rel="stylesheet" href="/css/pagination.css" />
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
			<a href="#"><span class="icon"></span> &nbsp;&nbsp;&nbsp;&nbsp; </a>
		</li>
		<li>
			<a href="#"><span class="icon">&#128196;</span> 点餐 </a>
		</li>
		<li>
			<a href="#"><span class="icon">&#127748;</span> 订单管理 </a>
			<ul class="submenu">
				
			</ul>
		</li>
		<li>
			<a href="#"><span class="icon">&#59160;</span> 结账</a>
			<ul class="submenu">
				
			</ul>
		</li>
		<li><a href="#"><span class="icon">&#128202;</span> 已完成订单 </a></li>
		<li>
	</ul>
</nav>
<section class="content" style="margin-top: 25px;">
    <section class="widget" style="height: 630px">
		<header>
			<hgroup>
				<div style="padding-top: 5px;">
					<font size="5" color="#F7FfFf">点餐</font>					
				</div>
			</hgroup>
		</header>
		<div class="content" style="padding-top: 5px;">
		<form method="post" action="/servlet/OrderServlet">
		<input type="hidden" value="query" name="action"> 
				<div style="margin-left: 5%;height: 40px;padding-top: 0;margin-bottom: 5px;">
					<table width="100%">
						<tr>
							<td width="9%" align="right">
							菜品名称：
							</td>
							<td width="18%">
								<input type="text" value="<%=food.getFood_name()==null?"":food.getFood_name() %>" id="foodname"  name="foodname"/>
							</td>
							<td width="9%" align="right">
								菜品种类：
							</td>
							<td width="18%">
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
							<td width="9%" align="right">菜品价格：</td>
							<td width="4%">
								<input type="text" value="<%=food.getMin_price()>0?food.getMin_price(): "" %>" id="minprice" name="minprice" />
							</td>
							<td width="1%">—</td>
							<td width="4%">
								<input type="text" value="<%=food.getMax_price()>0?food.getMax_price(): "" %>" id="maxprice" name="maxprice"/>
							</td>
							<td style="padding-left:10px ;padding-right: 10px;">
								<input type="submit" value="查询" style="background-color: #0272bd;color: #f7f7f7;min-width: 0px;" />								
							</td>
							<td style="padding-left:10px ;padding-right: 30px;">
							<a href="/servlet/OrderServlet?action=querySelect"><input type="button" value="已点菜品" style="background-color: #eaa032;color: #f7f7f7;min-width: 0px;"/></a>
							</td>
						</tr>
					</table>
				</div>
			</form>
			<table id="myTable" border="0" width="100">
				<thead style="text-align: center;">
					<tr>
						<th width="19%" style="text-align: center;padding-left:15px;padding-right:15px;width: 20%;">序号</th>
						<th width="20%" style="text-align: center;padding-left:15px;padding-right:15px;min-width: 0px;">菜品名称</th>
						<th width="20%" style="text-align: center;padding-left:15px;padding-right:15px;">菜品种类</th>
						<th width="19%" style="text-align: center;padding-left:15px;padding-right:15px;">菜品价格</th>
						<th width="1%" style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;min-width: 0px;"> </th>
						<th width="10%" style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;min-width: 0px;">数量</th>
						<th width="1%" style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;min-width: 0px;"> </th>
						<th width="10%" style="background-image: none;text-align: center;padding-left: 15px;padding-right:15px;">选中</th>
					</tr>
				</thead>
					<tbody style="text-align: center;">
					<%	
						int index =1;
						String type = "";
						for(Food liFood : foodPage.getResultList()){
							if(liFood.getFood_state()!=0){
					%>
						<tr style="padding-top: 0px;padding-bottom: 0px;">
							<td ><%=index++ %></td>
							<td><%=liFood.getFood_name() %></td>
							<td><%=foodtypeMap.get(liFood.getFood_type())%></td>
							<td><%=liFood.getPrice() %></td>
							<td style="text-align: right;"><span id="sub" onclick="sub<%=index%>();">-</span></td>
							<td  style="padding-top: 0px;padding-bottom: 0px;"><input type="text" id="number<%=index%>" name="number" value="1"  style="margin-bottom: 0px;padding-top: 6px;padding-bottom: 6px;"/></td>
							<td style="text-align: left ;"><span id="add" onclick="add<%=index%>();">+</span></td>
							<td  style="padding-top: 0px;padding-bottom: 0px;"><input type="button" value="选中" onclick="selflog_show<%=index%>()"  style="margin-bottom: 0px;padding-top: 6px;padding-bottom: 6px;background-color: #0272bd;color: #f7f7f7;min-width: 0px;"></td>
						</tr>
						<script type="text/javascript">
							function selflog_show<%=index%>()
							{ 
							   var num =  document.getElementById("number<%=index%>").value; 
							   window.location.href='/servlet/OrderServlet?action=additem&foodId=<%=liFood.getId()%>&num='+num;
							}
						  function add<%=index%>()
					      {
					         var num = parseInt(document.getElementById("number<%=index%>").value);
					         if(num<100)
					         {
					            document.getElementById("number<%=index%>").value = ++num;
					         }
					      }
					      function sub<%=index %>()
					      {
					         var num = parseInt(document.getElementById('number<%=index%>').value);
					         if(num>1)
					         {
					            document.getElementById('number<%=index%>').value = --num;
					         }
					      }
						</script>
						<%
							}
						}

						%>
					</tbody>
				</table>
				<div class="text-center" style="position:absolute;bottom:-250px ;padding-left: 270px;padding-right: 270px;">
					<ul class="pagination">
				
						<li <%=foodPage.getPageNo()==0?"class='disabled'":"" %> ><a href="/servlet/OrderServlet?action=query<%=urlParam %>&pageNo=0">首页</a></li>
						<li <%=foodPage.getPageNo()==0?"class='disabled'":"" %> ><a href="/servlet/OrderServlet?action=query<%=urlParam %>&pageNo=<%=foodPage.getPageNo()-1 %>">上一页</a></li>
						<% 
						for(int i = foodPage.getPageNo()-2,j=0;j < 6 &&i<=foodPage.getTotalPageNo();i++ ){
							if(i<0 ){
								continue;
							}
							j++;
				
						%>
						<li <%=foodPage.getPageNo()==i?"class='active'":"" %> ><a href='/servlet/OrderServlet?action=query<%=urlParam %>&pageNo=<%=i %>'><%=i+1 %></a></li>
						<% 
						}
						%>
						<li <%=foodPage.getTotalPageNo()==foodPage.getPageNo()?"class='disabled'":"" %> ><a href="/servlet/OrderServlet?action=query<%=urlParam %>&pageNo=<%=foodPage.getPageNo()+1 %>">下一页</a></li>
						<li <%=foodPage.getTotalPageNo()==foodPage.getPageNo()?"class='disabled'":"" %> ><a href="/servlet/OrderServlet?action=query<%=urlParam %>&pageNo=<%=foodPage.getTotalPageNo() %>">尾页</a></li>
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