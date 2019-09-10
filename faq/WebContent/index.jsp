<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>主页</title>

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
			<jsp:param value="index" name="from" />
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
				<!--推荐问题-->
				<div class="problem-title">
					推荐问题 <img src="img/more.gif" alt="more">
				</div>
				<!--推荐问题-->
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>问题列表（FAQ List）</th>
							<th>发帖时间</th>
							<th>发帖人</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="detail.html">在Struts1.2中上传图片怎么不能正常显示？</a></td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
					</tbody>
				</table>
				<!--未处理问题-->
				<div class="problem-title">
					未处理问题 <img src="img/more.gif" alt="more">
				</div>
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
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
					</tbody>
				</table>
				<!--已经处理问题-->
				<div class="problem-title">
					已经处理问题<img src="img/more.gif" alt="more">
				</div>
				<!--已经处理问题-->
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>问题列表（FAQ List）</th>
							<th>发帖时间</th>
							<th>发帖人</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
					</tbody>
				</table>
				<!--本周最有价值问题-->
				<div class="problem-title">
					本周最有价值问题<img src="img/more.gif" alt="more">
				</div>
				<!--本周最有价值问题-->
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>问题列表（FAQ List）</th>
							<th>发帖时间</th>
							<th>发帖人</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
						<tr>
							<td>在Struts1.2中上传图片怎么不能正常显示？</td>
							<td>2009-1-3 14:24</td>
							<td>张三</td>
						</tr>
					</tbody>
				</table>
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