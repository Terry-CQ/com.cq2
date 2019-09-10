<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty common_statistics_info}">
	<!--问题统计-->
	<div class="problem-bg">
		<span>问题统计</span>
	</div>
	<!--问题统计-->
	<ul class="list-unstyled">
		<li>一共提问:${common_statistics_info.questionCount}个</li>
		<li>已经解决问题:${common_statistics_info.resolved}个</li>
		<li>尚待解决问题:${common_statistics_info.unresolved}个</li>
		<li>提问最多的用户:${common_statistics_info.mostQuestionUser.alias}</li>
		<li>解决问题最多的用户:${common_statistics_info.mostReplyUser.alias}</li>
	</ul>

	<c:if test="${not empty common_statistics_info.categoryList}">
		<!--问题分类-->
		<div class="problem-bg">
			<span>问题分类</span>
		</div>
		<!--问题分类-->
		<ul class="list-unstyled">
			<c:forEach items="${common_statistics_info.categoryList}" var="ca">
				<li>${ca.categoryName}</li>
			</c:forEach>
		</ul>
	</c:if>

	<c:if test="${not empty common_statistics_info.userScores}">
		<!--用户积分排行榜-->
		<div class="problem-bg">
			<span>用户积分排行榜</span>
		</div>
		<!--用户积分排行榜-->
		<table class="table table-condensed table-bordered">
			<c:forEach items="${common_statistics_info.userScores}" var="u">
				<tr>
					<td>${u.alias}</td>
					<td>${u.score}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</c:if>