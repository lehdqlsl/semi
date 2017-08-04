<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>

	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>

	<!-- 메인페이지 -->

	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div
			style="width: 1000px; word-break: break-all; word-wrap: break-word; margin: auto;">
			<div
				style="margin: auto; width: 1000px; break-all; word-wrap: break-word; height: 35px; background-color: #428bca; margin-bottom: 0px; padding: 1px;">
				<h5 style="margin-left: 10px; font-weight: bold; color: white;">Best
					Of Best 맛집</h5>
			</div>


			<table class="table">
				<c:forEach var="vo" items="${requestScope.bob }" varStatus="status">
					<tr class="">
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 303px;"><a
							href="/semi_team1/tasty/select?num=${vo.num}">${vo.title}</a></td>
					</tr>
				</c:forEach>
			</table>

			<!-- 케러셀 -->


		</div>



		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word; height: 35px; background-color: #428bca; margin-bottom: 0px; padding: 1px;">
			<h5 style="margin-left: 10px; font-weight: bold; color: white;">지역별
				맛집</h5>
		</div>
		<div
			style="width: 1000px; word-break: break-all; word-wrap: break-word; margin: auto;">
			<div class="col-xs-6 col-lg-4">
				<table class="table">
					<tr>
						<th style="font-size: 18px; width: 250px;">서울 맛집</th>
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
						<th style="font-size: 18px; width: 303px;">경기도 맛집</th>
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
						<th style="font-size: 18px; width: 303px;">강원도 맛집</th>
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
						<th style="font-size: 18px; width: 250px;">경상도 맛집</th>
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
						<th style="font-size: 18px; width: 303px;">전라도 맛집</th>
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
						<th style="font-size: 18px; width: 303px;">제주도 맛집</th>
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