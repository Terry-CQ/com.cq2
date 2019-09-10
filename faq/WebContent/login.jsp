<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet" href="./css/login.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript">
	$(function() {
		$(".btn").click(function() {
			//输入检查

			//输入有效
			$("form").get(0).submit();
		});
	});
</script>
</head>

<body>
	<div class="login">
		<div class="login-screen">
			<div class="app-title">
				<h1>Login</h1>
			</div>

			<div class="login-form">
				<form action="${pageContext.request.contextPath}/userServlet" method="post">
					<div class="control-group">
						<input type="text" class="login-field" value=""
							placeholder="username" id="login-name" name="account"> <label
							class="login-field-icon fui-user" for="login-name"></label>
					</div>

					<div class="control-group">
						<input type="password" class="login-field" value=""
							placeholder="password" id="login-pass" name="pwd"> <label
							class="login-field-icon fui-lock" for="login-pass"></label>
					</div>
					<input type="hidden" name="flag" value="login"/>
					<a class="btn btn-primary btn-large btn-block"
						href="javascript:void(0)">login</a> 
					<div>${msg}</div>
					<a class="login-link"
						href="#">Lost your password?</a>
				</form>
			</div>
		</div>
	</div>
</body>

</html>