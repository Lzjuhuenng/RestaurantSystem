<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="robots" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="/css/style.css" media="all" />
	<title>XXXX 酒店点餐系统</title>
</head>
<script type="text/javascript">
	function validateAccount(){
		var usernameObj = document.getElementById("account");
		username = usernameObj.value;
		if(username == ""){
			alert("请输入帐号！");
			usernameObj.focus();
			return false;
		}
		return true;
	}
	function validatePassword(){
		var passwordObj = document.getElementById("password");
		password = passwordObj.value;
		if(password == ""){
			alert("请输入密码！");
			passwordObj.focus();
			return false;
		}
		return true;
	}
</script>
<body class="login">
	<section>
		<h1><strong>XXXX</strong>点餐系统</h1>
		<form method="post" action="servlet/LoginServlet" onsubmit="return validateAccount() && validatePassword()">
			<input type="text" value="Account"  id="account" name="account"/>
			<input type="password" value="Password" id="password" name="password">
			<input type="submit" value="Login" style="background-color: #0272bd;font-weight: bold;color: #ffffff;text-shadow: 1px 1px 0 rgba(0, 0, 0, 0.2);">
		</form>
		<p><a href="#">Forgot your password?</a></p>
	</section>
<script src="/js/jquery.min.js"></script>
<script type="text/javascript">
// Page load delay by Curtis Henson - http://curtishenson.com/articles/quick-tip-delay-page-loading-with-jquery/
/*
$(function(){
	$('.login button').click(function(e){ 
		// Get the url of the link 
		var toLoad = $(this).attr('href');  
 
		// Do some stuff 
		$(this).addClass("loading"); 
 
			// Stop doing stuff  
			// Wait 700ms before loading the url 
			setTimeout(function(){window.location = toLoad}, 10000);	  
 
		// Don't let the link do its natural thing 
		e.preventDefault
	});
	
	$('input').each(function() {

       var default_value = this.value;

       $(this).focus(function(){
            if(this.value == default_value) {
                this.value = '';
            }
       });

       $(this).blur(function(){
            if(this.value == '') {
                this.value = default_value;
            }
       	});

	});
}); */
</script>
</body>
</html>