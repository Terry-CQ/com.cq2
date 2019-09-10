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
<title>问题详细</title>

<link href="./bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet">
<link href="./bootstrap-3.3.4-dist/css/bootstrap-theme.css"
	rel="stylesheet">
<!--[if lt IE 9]>
       <script src="./forIE8/html5shiv.js"></script>
       <script src="./forIE8/respond.js"></script>
    <![endif]-->

<link rel="stylesheet" href="css/main.css">

<style>
div.detail-title {
	height: 32px;
	background-color: #ccc;
	border: 1px solid black;
	line-height: 32px;
	font-size: 16px;
	font-family: '微软雅黑', '黑体', '宋体', Arial, Helvetica, sans-serif;
	margin-top: 20px;
}

div.detail-content {
	border: 1px solid black;
	font-family: '微软雅黑', '黑体', '宋体', Arial, Helvetica, sans-serif;
	padding: 5px 5px 5px 10px;
	border-top: 0px;
}

.picture {
	width: 700px;
}
</style>
</head>

<body>

	<div class="container">
		<jsp:include page="comp/nav_comp.jsp"></jsp:include>
		<!--主体部分-->
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="comp/login_comp.jsp"></jsp:include>
				<jsp:include page="comp/problem_comp.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<!--浏览问题与回复-->
				<div class="problem-title">浏览问题与回复</div>

				<c:if test="${not empty detail}">
					<!--问题描述-->
					<div class="detail-title">问题描述</div>

					<div class="detail-content">
						<h3>[标题]${detail.title}</h3>
						<h5>[难度等级]${detail.level.name}</h5>
						<h5>[悬赏分数]${detail.score}分</h5>
						<p>[问题内容]${detail.content}</p>
						<c:if test="${not empty detail.fileId}">
							<img class="picture"
								src="${pageContext.request.contextPath}/problemServlet?flag=picture&fileId=${detail.fileId}"
								alt="problem">
						</c:if>
						<p>
							[提问者]${detail.submitUser.alias} [提问时间]
							<fmt:formatDate value="${detail.submitTime}"
								pattern="yyyy-MM-dd HH:mm" />
						</p>
					</div>

					<c:if test="${not empty detail.bestReply }">
						<!--最佳回复-->
						<div class="detail-title" style="background-color: #FB5846;">最佳回复</div>
						<div class="detail-content" style="border-color: #FB5846;">
							<p>[问题回复]${detail.bestReply.content }</p>
							<img class="picture"
								src="${pageContext.request.contextPath}/replyServlet?flag=picture&fileId=${detail.bestReply.picId}"
								alt="huifu">
							<p>
								[回答者]${detail.bestReply.replyUser.alias} [回答时间]
								<fmt:formatDate value="${detail.bestReply.replyTime}"
									pattern="yyyy-MM-dd HH:mm" />
							</p>
						</div>
					</c:if>

					<c:if test="${not empty detail.replies }">
						<c:forEach items="${detail.replies}" var="rp">
							<!--回复-->
							<div class="detail-title">回复</div>
							<div class="detail-content">
								<p>【问题回复】${rp.content}</p>
								<img class="picture"
									src="${pageContext.request.contextPath}/replyServlet?flag=picture&fileId=${rp.picId}"
									alt="huifu">
								<p>
									[回答者]${rp.replyUser.alias} [回答时间]
									<fmt:formatDate value="${rp.replyTime}"
										pattern="yyyy-MM-dd HH:mm" />
								</p>

								<c:if
									test="${sessionScope.session_login_info!=null && sessionScope.session_login_info.userId==detail.submitUserId && detail.bestReply==null}">
									<div style="height: 32px;">
										<button class="btn btn-info" style="float: right;"
											type="button" onclick="acceptReply(${rp.replyId});">采纳为最佳</button>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</c:if>

					<c:if test="${not empty sessionScope.session_login_info}">
						<!--我来回复-->
						<div class="detail-title" style="background-color: #A9A9CC;">我来回复</div>
						<div class="detail-content">
							<form action="${pageContext.request.contextPath}/replyServlet"
								id="formReply" method="POST" class="form-horizontal"
								enctype="multipart/form-data">
								<div class="form-group">
									<label for="replyContent" class="col-md-2 control-label">回复内容:</label>
									<div class="col-md-10">
										<textarea name="replyContent" id="replyContent" cols="30"
											rows="6" class="form-control"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">图片:</label>
									<div class="col-md-10">
										<input type="file" name="picture" id="picture">
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-4 col-md-offset-2">
										<button class="btn btn-primary" id="btnReply">提交</button>
									</div>
								</div>
								<input type="hidden" name="flag" value="reply" /> <input
									type="hidden" name="probId" value="${detail.problemId }" />
							</form>
							<c:choose>
								<c:when test="${msgCode=='replySuccess'}">回复成功!</c:when>
								<c:when test="${msgCode=='replyFailure'}">回复失败!</c:when>
							</c:choose>
						</div>
					</c:if>
				</c:if>

				<!--返回-->
				<div class="detail-title">
					<a href="#">返回问题列表</a> <a href="#">返回主页</a>
				</div>
			</div>
		</div>
		<!--页脚-->
		<div id="footer">
			<span>地址：西安市长安区西安邮电大学长思公寓六号楼</span> <br> <span>电话：18729362639</span>
		</div>
	</div>


	<script src="./jquery/jquery-1.11.2.js"></script>
	<script src="./bootstrap-3.3.4-dist/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnReply").click(function() {
				var msg = checkUserInput([ {
					id : "#replyContent",
					name : '回复内容',
					minLen : 1
				} ]);
				if (msg.length > 0) {
					alert(msg.join("\n"));
				} else {
					$("#formReply").get(0).submit();
				}
			});
		});
		
		function acceptReply(replyId){
			window.location.href="${pageContext.request.contextPath}/replyServlet?flag=accept&replyId="+replyId;
		}
	</script>
</body>

</html>