<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String from = request.getParameter("from");
%>
<!--banner-->
<div id="banner">
	<img src="img/banner.jpg" alt="banner" class="img-thumbnail">
</div>
<!--导航条-->
<nav class="navbar navbar-default" id="nav">
	<div class="container-fluid">
		<!-- Collect the nav links -->
		<ul class="nav navbar-nav">
			<li <c:if test="${param.from=='index'}">class="active"</c:if>><a
				href="${pageContext.request.contextPath}/problemServlet">主页</a></li>
			<li <c:if test="${param.from=='unresolved'}">class="active"</c:if>><a
				href="${pageContext.request.contextPath}/problemServlet?flag=unresolved">未处理问题</a></li>
			<li <c:if test="${param.from=='resolved'}">class="active"</c:if>><a
				href="${pageContext.request.contextPath}/problemServlet?flag=resolved">已处理问题</a></li>
			<li <c:if test="${param.from=='recommend'}">class="active"</c:if>><a
				href="${pageContext.request.contextPath}/problemServlet?flag=recommend">推荐问题</a></li>

			<c:if test="${not empty sessionScope.session_login_info}">
				<li <c:if test="${param.from=='question'}">class="active"</c:if>><a
					href="${pageContext.request.contextPath}/problemServlet?flag=preQuestion">我要提问</a></li>
			</c:if>

			<c:if test="${empty sessionScope.session_login_info}">
				<li <c:if test="${param.from=='register'}">class="active"</c:if>><a
					href="${pageContext.request.contextPath}/userServlet?flag=preRegister">我要注册</a></li>
			</c:if>

			<c:if test="${not empty sessionScope.session_login_info}">
				<li <c:if test="${param.from=='userCenter'}">class="active"</c:if>><a
					href="user_center.html">用户中心</a></li>
			</c:if>
			
			<c:if test="${not empty sessionScope.session_login_info}">
			<li><a
					href="${pageContext.request.contextPath}/userServlet?flag=logout">退出登录</a></li>
			</c:if>
			
		</ul>
		<!-- /.navbar-collapse -->
		<div class="pull-right" id="login-info">
			<c:if test="${empty sessionScope.session_login_info}">
			您还没有登录系统!
		</c:if>
			<c:if test="${not empty sessionScope.session_login_info}">当前登录的用户为:${sessionScope.session_login_info.alias }</c:if>
		</div>
	</div>
	<!-- /.container-fluid -->
</nav>