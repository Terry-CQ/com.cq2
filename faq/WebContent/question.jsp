<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.yueqian.faq.common.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>发布问题</title>

<link href="./bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet">
<link href="./bootstrap-3.3.4-dist/css/bootstrap-theme.css"
	rel="stylesheet">
<!--[if lt IE 9]>
       <script src="./forIE8/html5shiv.js"></script>
       <script src="./forIE8/respond.js"></script>
    <![endif]-->

<link rel="stylesheet" href="css/main.css">
</head>

<body>

	<div class="container">
		<!-- 导入nav_comp.jsp -->
		<jsp:include page="comp/nav_comp.jsp">
			<jsp:param value="question" name="from" />
		</jsp:include>
		<!--主体部分-->
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="comp/login_comp.jsp"></jsp:include>
				<jsp:include page="comp/problem_comp.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<!--发布问题-->
				<div class="problem-title">发布问题</div>
				<!--表单-->
				<form action="${pageContext.request.contextPath}/problemServlet" method="POST" class="form-horizontal" id="questionForm" enctype="multipart/form-data">
				<input type="hidden" name="flag" value="addQuestion"/>
					<div class="form-group">
						<label for="category" class="col-md-2 control-label">问题类别:</label>
						<div class="col-md-4">
							<select name="category" id="category" class="form-control">
								<option value="0">请选择问题类别</option>

								<c:forEach items="${categoryList}" var="c">
									<option value="${c.categoryId}">${c.categoryName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="title" class="col-md-2 control-label">问题标题:</label>
						<div class="col-md-8">
							<input type="text" name="title" id="title" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">难度等级:</label>
						<div class="col-md-8">
							<div class="radio">
								<c:forEach items="${DifficultyLevel.values()}" var="d">
									<label> <input type="radio" name="difficultyLevel"
										value="${d.value}" <c:if test="${d.value==1}">checked</c:if>>${d.name}
									</label>
								</c:forEach>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="score" class="col-md-2 control-label">悬赏分数:</label>
						<div class="col-md-2">
							<select name="score" id="score" class="form-control">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="detail" class="col-md-2 control-label">详细内容:</label>
						<div class="col-md-10">
							<textarea name="detail" id="detail" cols="30" rows="5"
								class="form-control"></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="picture" class="col-md-2 control-label">相关图片:</label>
						<div class="col-md-8">
							<input type="file" name="picture" id="picture">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-3 col-md-offset-3"></div>
						<input type="button" value="发布问题" class="btn btn-primary"
							id="btnQuestion">
					</div>
				</form>
			</div>
		</div>
		<!--页脚-->
		<div id="footer">
			<span>地址：西安市长安区西安邮电大学长思公寓六号楼</span> <br> <span>电话：18729362639</span>
		</div>
	</div>


	<script src="./jquery/jquery-1.11.2.js"></script>
	<script src="./bootstrap-3.3.4-dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="./js/common.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnQuestion").click(function() {
				var msg = checkUserInput([ {
					id : '#title',
					name : '标题',
					minLen : 3
				}, {
					id : '#detail',
					name : '问题内容',
					minLen : 1
				} ]);
				if (msg && msg.length > 0) {
					alert(msg.join('\n'));
				} else {
					$("#questionForm").get(0).submit();
				}
			});
		});
	</script>
</body>

</html>