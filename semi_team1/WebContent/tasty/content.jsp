<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>

	<!-- 메인페이지 -->

	<div class="col-sm-9 col-sm-offset-1 col-md-8 col-md-offset-1 main">

		<div
			style="width: 640px; word-break: break-all; word-wrap: break-word;">

			<hr>
			<h4 style="margin-left: 10px; font-weight: bold; color: black;">Best
				Of Best 맛집</h4>
			<hr>


			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
					<li data-target="#myCarousel" data-slide-to="3"></li>
					<li data-target="#myCarousel" data-slide-to="4"></li>
				</ol>



				<div class="carousel-inner">
					<div class="item active">
						<a href="/semi_team1/tasty/select?num=${requestScope.first.num}">${requestScope.first.tag }</a>
						<div class="carousel-caption">
							<h3>${requestScope.first.title }</h3>
							<h4>1위 평점 : ${requestScope.first.up}</h4>
						</div>
					</div>
					<c:forEach var="vo" items="${requestScope.bob }" varStatus="status"
						begin="1" end="4" step="1">

						<div class="item">
							<a href="/semi_team1/tasty/select?num=${bob[status.index].num}">${bob[status.index].tag }</a>
							<div class="carousel-caption">
								<h3>${bob[status.index].title }</h3>
								<h4>${status.index+1}위평점:${bob[status.index].up}</h4>
							</div>
						</div>
					</c:forEach>

				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>


		</div>

		<hr>
		<h4
			style="margin-left: 10px; margin-top: 10px; font-weight: bold; color: black;">지역별
			베스트 맛집</h4>
		<hr>

		<div
			style="width: 1000px; word-break: break-all; word-wrap: break-word;">
			<div class="col-xs-6 col-lg-4">
				<table class="table">
					<tr>
						<td colspan="5" style="font-size: 18px; font-weight: bold"><a
							href="/semi_team1/tasty/list?s_num=32">서울 맛집</a></td>
					</tr>
					<c:forEach var="vo" items="${requestScope.s1 }" varStatus="status">
						<tr class="">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 303px;"><a
								href="/semi_team1/tasty/select?num=${vo.num}">${vo.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-xs-6 col-lg-4">
				<table class="table">
					<tr>
						<td colspan="5" style="font-size: 18px; font-weight: bold"><a
							href="/semi_team1/tasty/list?s_num=33">경기도 맛집</a></td>
					</tr>
					<c:forEach var="vo" items="${requestScope.s2 }" varStatus="status">
						<tr class="">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 303px;"><a
								href="/semi_team1/tasty/select?num=${vo.num}">${vo.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="col-xs-6 col-lg-4">
				<table class="table">
					<tr>
						<td colspan="5" style="font-size: 18px; font-weight: bold"><a
							href="/semi_team1/tasty/list?s_num=34">강원도 맛집</a></td>
					</tr>
					<c:forEach var="vo" items="${requestScope.s3 }" varStatus="status">
						<tr class="">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 303px;"><a
								href="/semi_team1/tasty/select?num=${vo.num}">${vo.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="col-xs-6 col-lg-4">
				<table class="table">
					<tr>
						<td colspan="5" style="font-size: 18px; font-weight: bold"><a
							href="/semi_team1/tasty/list?s_num=35">경상도 맛집</a></td>
					</tr>
					<c:forEach var="vo" items="${requestScope.s4 }" varStatus="status">
						<tr class="">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 303px;"><a
								href="/semi_team1/tasty/select?num=${vo.num}">${vo.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-xs-6 col-lg-4">
				<table class="table">
					<tr>
						<td colspan="5" style="font-size: 18px; font-weight: bold"><a
							href="/semi_team1/tasty/list?s_num=36">전라도 맛집</a></td>
					</tr>
					<c:forEach var="vo" items="${requestScope.s5 }" varStatus="status">
						<tr class="">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 303px;"><a
								href="/semi_team1/tasty/select?num=${vo.num}">${vo.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="col-xs-6 col-lg-4">
				<table class="table">
					<tr>
						<td colspan="5" style="font-size: 18px; font-weight: bold"><a
							href="/semi_team1/tasty/list?s_num=37">제주도 맛집</a></td>
					</tr>
					<c:forEach var="vo" items="${requestScope.s6 }" varStatus="status">
						<tr class="">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 303px;"><a
								href="/semi_team1/tasty/select?num=${vo.num}">${vo.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>