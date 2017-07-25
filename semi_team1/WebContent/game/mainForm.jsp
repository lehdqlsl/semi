<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 사이드바 -->
	<div class="col-sm-3 col-md-2 sidebar">
		<ul class="nav nav-sidebar">
			<li class="active"><a href="/semi_team1/list?f_num=${param.f_num }&s_num=1">Overview <span
					class="sr-only">(current)</span></a></li>
			<li><a href="#">Reports</a></li>
			<li><a href="#">Analytics</a></li>
			<li><a href="#">Export</a></li>
		</ul>
		<ul class="nav nav-sidebar">
			<li><a href="">Nav item</a></li>
			<li><a href="">Nav item again</a></li>
			<li><a href="">One more nav</a></li>
			<li><a href="">Another nav item</a></li>
			<li><a href="">More navigation</a></li>
		</ul>
		<ul class="nav nav-sidebar">
			<li><a href="">Nav item again</a></li>
			<li><a href="">One more nav</a></li>
			<li><a href="">Another nav item</a></li>
		</ul>
	</div>

	<!-- 메인페이지 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1>게임페이지</h1>

		<!-- 예시 -->
		<div class="col-xs-6 col-lg-4">
			<h2>베스트 게시글</h2>
			<table>
				<tr>
					<td>베스트1</td>
				</tr>
				<tr>
					<td>베스트2</td>
				</tr>
				<tr>
					<td>베스트3</td>
				</tr>
				<tr>
					<td>베스트4</td>
				</tr>
			</table>
		</div>

		<div class="col-xs-6 col-lg-4">
			<h2>베스트 게시글</h2>
			<table>
				<tr>
					<td>베스트1</td>
				</tr>
				<tr>
					<td>베스트2</td>
				</tr>
				<tr>
					<td>베스트3</td>
				</tr>
				<tr>
					<td>베스트4</td>
				</tr>
			</table>
		</div>

		<div class="col-xs-6 col-lg-4">
			<h2>베스트 게시글</h2>
			<table>
				<tr>
					<td>베스트1</td>
				</tr>
				<tr>
					<td>베스트2</td>
				</tr>
				<tr>
					<td>베스트3</td>
				</tr>
				<tr>
					<td>베스트4</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>