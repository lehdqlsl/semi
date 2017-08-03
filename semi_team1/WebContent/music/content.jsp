<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>

	<!-- 메인페이지 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1>음악페이지</h1>

		<!-- 베스트 게시글 -->
		<div
			style="width: 1000px; word-break: break-all; word-wrap: break-word; margin: auto;">
			<div style="float: left;">
				<table class="table">
					<tr class="active">
						<th style="text-align: center; font-size: 18px;" colspan="2">게시판
							<span style="font-weight: bold; color: red"></span>BEST
						</th>
					</tr>

					<c:forEach var="vo" items="${requestScope.list }"
						varStatus="status" begin="0" end="6" step="2">
						<tr class="active">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 425px;"><a
								href="/semi_team1/select?num=${list[status.index].num}"><img src="/semi_team1/upload/${list[status.index].title_name}">
							</a></td>
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 425px;"><a
								href="/semi_team1/select?num=${list[status.index+1].num}">${list[status.index+1].title_name}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		<div
		style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
		<table class="table table-bordered" width="1000px" height="200px">
			<tr>
				<c:forEach var="vo" items="${requestScope.list }">
					<td align="center"><a
						href="/semi_team1/select?num=${vo.num}">${vo.tag }</a>
					</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="vo" items="${requestScope.list }">
					<td align="center">
						제목 : ${vo.title_name }
					</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="vo" items="${requestScope.list }">
					<td align="center">
						작성자 : ${vo.writer }
					</td>
				</c:forEach>
			</tr>
		</table>
	</div>
	</div>
</body>
</html>