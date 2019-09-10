<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>推荐问题</title>

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
		<jsp:include page="comp/nav_comp.jsp">
			<jsp:param value="recommend" name="from" />
		</jsp:include>
		<!--主体部分-->
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="comp/login_comp.jsp"></jsp:include>
				<jsp:include page="comp/problem_comp.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<!--未处理问题列表-->
				<div class="problem-title">[首页]>>[推荐问题列表]</div>
				<!--未处理问题-->
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>问题列表（FAQ List）</th>
							<th>发帖时间</th>
							<th>发帖人</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty pageInfo.list }">

							<c:forEach items="${pageInfo.list}" var="p">
								<tr>
									<td><a
										href="${pageContext.request.contextPath}/problemServlet?flag=detail&probId=${p.problemId}">${p.title}</a></td>
									<td><fmt:formatDate value="${p.submitTime}"
											pattern="yyyy-MM-dd HH:mm" /></td>
									<td>${p.submitUser.alias }</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<!--页码-->
				<ul class="pagination">
					<li
						<c:if test="${pageInfo.currentPageNo==1}">class="disabled"</c:if>>
						<a
						href="${pageContext.request.contextPath}/problemServlet?flag=recommend&current=${pageInfo.currentPageNo}&dir=pre">&laquo;</a>
					</li>

					<c:forEach begin="1" end="${pageInfo.pageCount }" var="pi">
						<li
							<c:if test="${pi==pageInfo.currentPageNo }">class="active"</c:if>><a
							href="${pageContext.request.contextPath}/problemServlet?flag=recommend&pageNo=${pi}">${pi}</a></li>
					</c:forEach>

					<li
						<c:if test="${pageInfo.currentPageNo==pageInfo.pageCount}">class="disabled"</c:if>>
						<a
						href="${pageContext.request.contextPath}/problemServlet?flag=recommend&current=${pageInfo.currentPageNo}&dir=next">&raquo;</a>
					</li>
				</ul>
			</div>
		</div>
		<!--页脚-->
		<div id="footer">
			<span>地址：西安市长安区西安邮电大学长思公寓六号楼</span> <br> <span>电话：18729362639</span>
		</div>
	</div>


	<script src="./jquery/jquery-1.11.2.js"></script>
	<script src="./bootstrap-3.3.4-dist/js/bootstrap.js"></script>
</body>

</html>