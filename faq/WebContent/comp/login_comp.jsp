<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.session_login_info }">
	<!--登录图片-->
	<div class="login-img-bg">
		<img src="./img/userlogin.gif" alt="login">
	</div>
	<!--登录框-->
	<form action="${pageContext.request.contextPath}/userServlet"
		method="POST">
		<table class="table table-bordered table-condensed">
			<tr>
				<td>注册号</td>
				<td><input type="text" name="account1" id="account1"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="pwd1" id="pwd1"></td>
			</tr>
			<tr>
				<td>验证码</td>
				<td><input type="text" name="verifyCode" id="verifyCode"
					size="6"> <span>${msg}</span></td>
			</tr>
			<tr>
				<td></td>
				<td><img alt="验证码"
					src="${pageContext.request.contextPath}/userServlet?flag=verifyCode"
					id="imgVerify"> <span style="color: green;">点击刷新</span></td>
			</tr>
			<tr>
				<td colspan="2" class="align_right">
					<button class="btn btn-primary" type="submit">用户登录</button>
				</td>
			</tr>
		</table>
		<input type="hidden" name="flag" value="login" />
	</form>
	<!--注册图片-->
	<div class="user-reg-bg">
		<span>新用户注册</span>
	</div>
</c:if>
<!--我要提问-->
<div class="question-bg">
	<img src="./img/question.gif" alt="qusetion">
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#imgVerify").click(
				function() {
					$(this).prop(
							'src',
							"${pageContext.request.contextPath}/userServlet?flag=verifyCode&t="
									+ new Date().getTime());
				});
	});
</script>