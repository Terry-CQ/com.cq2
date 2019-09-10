<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yueqian.faq.common.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>用户注册</title>

<link href="./bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet">
<link href="./bootstrap-3.3.4-dist/css/bootstrap-theme.css"
	rel="stylesheet">
<link rel="stylesheet" href="./datepicker/bootstrap-datepicker3.min.css">
<!--[if lt IE 9]>
       <script src="./forIE8/html5shiv.js"></script>
       <script src="./forIE8/respond.js"></script>
    <![endif]-->

<link rel="stylesheet" href="css/main.css">
<style>
.base-info-title {
	background-color: #ccc;
	font-size: 18px;
	text-align: center;
	line-height: 32px;
	height: 32px;
	border: 1px solid lightgray;
	margin-top: 15px;
}

table td, table {
	border: 1px solid lightgray;
}

table {
	border-collapse: collapse;
	width: 100%;
}

table td {
	padding: 5px;
}

table td>span {
	color: red;
}
</style>

</head>

<body>
	<div class="container">
		<jsp:include page="comp/nav_comp.jsp">
			<jsp:param value="register" name="from" />
		</jsp:include>
		<!--主体部分-->
		<div class="row">
			<div class="col-md-3">
				<!-- 用户登录，我要注册，我要提问 -->
				<jsp:include page="comp/login_comp.jsp"></jsp:include>
				<!-- 问题统计，问题分类，用户积分排行 -->
				<jsp:include page="comp/problem_comp.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<!--用户注册-->
				<div class="problem-title">用户注册</div>
				<!--基本信息标题-->
				<div class="base-info-title">用户基本信息（必填项目）</div>
				<!--表单-->
				<form action="${pageContext.request.contextPath}/userServlet"
					method="POST" class="form-horizontal">
					<input type="hidden" name="flag" value="register" />
					<table>
						<col width="30%">
						<col width="70%">
						<tr>
							<td align="center">账号</td>
							<td><input type="text" name="account" id="account" size="30">
								<span>*(*为必填项目)</span> &nbsp;&nbsp;&nbsp;&nbsp;<span
								id="accountPrompt" style="color: green;"></span></td>
						</tr>

						<tr>
							<td align="center">昵称</td>
							<td><input type="text" name="name" id="name" size="30">
								<span>*</span></td>
						</tr>

						<tr>
							<td align="center">密码</td>
							<td><input type="text" name="pwd" id="pwd" size="30">
								<span>*</span></td>
						</tr>

						<tr>
							<td align="center">确认密码</td>
							<td><input type="text" name="confirmPwd" id="confirmPwd"
								size="30"> <span>*</span></td>
						</tr>

						<tr>
							<td align="center">QQ号码</td>
							<td><input type="text" name="qq" id="qq" size="30">
							</td>
						</tr>

						<tr>
							<td align="center">电子邮件</td>
							<td><input type="text" name="email" id="email" size="30">
								<span>*</span></td>
						</tr>

						<tr>
							<td align="center">手机号码</td>
							<td><input type="text" name="mobile" id="mobile" size="30">
								<span>*</span></td>
						</tr>
					</table>

					<!--高级信息标题-->
					<div class="base-info-title">用户高级信息（选填）</div>

					<table>
						<col width="30%">
						<col width="70%">
						<tr>
							<td align="center">毕业院校</td>
							<td><input type="text" name="college" id="college" size="30">
							</td>
						</tr>

						<tr>
							<td align="center">专业</td>
							<td><input type="text" name="major" id="major" size="30">
							</td>
						</tr>

						<tr>
							<td align="center">身份证号码</td>
							<td><input type="text" name="idcard" id="idcard" size="30">
							</td>
						</tr>

						<tr>
							<td align="center">出生日期</td>
							<td>
								<div style="width: 220px;">
									<div class="input-group date" id="dob"
										data-date-format="yyyy-mm-dd">
										<input class="form-control" size="16" type="text" name="dob"
											readonly>
										<div class="input-group-addon">
											<span class="add-on glyphicon glyphicon-calendar"></span>
										</div>
									</div>
								</div>
							</td>
						</tr>

						<tr>
							<td align="center">英文水平</td>
							<td><select name="english" id="english">
									<option value="0">请选择等级</option>
									<c:forEach items="${EnglishLevel.values()}" var="egl">
										<option value='${egl.value}'>${egl.name}</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td align="center">是否在职</td>
							<td><c:forEach items="${JobStatus.values()}" var="jbs">
									<label> <input type="radio" name="jobType"
										value="${jbs.value}">${jbs.name}
									</label>
								</c:forEach></td>
						</tr>

						<tr>
							<td align="center">工作单位</td>
							<td><input type="text" name="company" id="company" size="30">
							</td>
						</tr>
					</table>
					<div style="text-align: center; margin-top: 15px;">
						<input id="btnSubmit" type="button" value="确定注册"
							class="btn btn-primary" /> <input id="btnReset" type="button"
							value="取消重填" class="btn btn-primary">
					</div>
				</form>
				<div>
					<c:if test="${not empty register_msg }">
						<ul>
							<c:forEach items="${register_msg}" var="m">
								<li>${m}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
		<!--页脚-->
		<div id="footer">
			<span>地址：西安市西长安街599号智慧国际15层</span> <br> <span>电话：18966862266</span>
		</div>
	</div>


	<script src="./jquery/jquery-1.11.2.js"></script>
	<script src="./bootstrap-3.3.4-dist/js/bootstrap.js"></script>
	<script src="./datepicker/bootstrap-datepicker.min.js"></script>
	<script src="./datepicker/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript" src="./js/common.js"></script>
	<script type="text/javascript">
		var accountValid = true;
		$(function() {
			//初始化日期选择控件
			$("#dob").datepicker({
				language : 'zh-CN',
				autoclose : true
			});
			//给账号输入框绑定blur事件处理函数
			$("#account").blur(function() {
				var account = $(this).val();
				var accountPrompt = $("#accountPrompt");

				if (account) {
					$.get('${pageContext.request.contextPath}/userServlet', {
						flag : 'checkAccount',
						account : account
					}, function(result) {
						if (result && result.code === 0) {
							accountValid = result.data;
							if (result.data) {
								accountPrompt.html('账号可用!');
							} else {
								accountPrompt.html('对不起，账号已被别注册了!');
							}
						} else {
							if (result.msg) {
								alert(result.msg);
							}
						}
					}, 'json');
				} else {
					//
					accountPrompt.html('');
				}
			});
			//给提交按钮和重置按钮绑定click事件处理函数
			$("#btnSubmit").click(function() {
				if (!accountValid) {
					alert("账号已被使用，不能进行注册!");
					return;
				}
				var msg = checkUserInput([ {
					id : "#account",
					name : "账号",
					minLen : 3,
					maxLen : 20
				}, {
					id : '#name',
					name : '昵称',
					minLen : 3
				}, {
					id : '#pwd',
					name : '密码',
					minLen : 3
				}, {
					id : "#confirmPwd",
					name : '确认密码',
					minLen : 3,
					maxLen : 30
				}, {
					id : '#email',
					name : '邮箱',
					minLen : 3
				}, {
					id : '#mobile',
					name : '手机号码',
					minLen : 11,
					maxLen : 11
				} ]);
				if (msg.length > 0) {
					alert(msg.join("\n"));
				} else {
					//提交表单
					$("form").get(1).submit();
				}
			});
			$("#btnReset").click(function() {
				$("form").get(1).reset();
			});
		});

	</script>
</body>

</html>