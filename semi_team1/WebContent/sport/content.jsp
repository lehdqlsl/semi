<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
			<table class="table">
				<tr class="danger">
					<th style="font-size: 18px;" colspan="2"><a
						href="/semi_team1/list?s_num=20">스포츠뉴스</a></th>
				</tr>

				<c:forEach var="vo" items="${requestScope.sportsmain1 }"
					varStatus="status" begin="0" end="10" step="2">
					<tr class="danger">
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${sportsmain1[status.index].num}">${sportsmain1[status.index].title_name}
						</a></td>
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${sportsmain1[status.index+1].num}">${sportsmain1[status.index+1].title_name}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">

			<table class="table">
				<tr class="warning">
					<th style="font-size: 18px;" colspan="2"><a
						href="/semi_team1/list?s_num=21">자유게시판</a></th>
				</tr>

				<c:forEach var="vo" items="${requestScope.sportsmain2 }"
					varStatus="status" begin="0" end="10" step="2">
					<tr class="warning">
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${sportsmain2[status.index].num}">${sportsmain2[status.index].title_name}
						</a></td>
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${sportsmain2[status.index+1].num}">${sportsmain2[status.index+1].title_name}</a></td>
					</tr>
				</c:forEach>
			</table>



			<div
				style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">

				<table class="table">
					<tr class="success">
						<th style="font-size: 18px;" colspan="2"><a
							href="/semi_team1/list?s_num=23">중계일정</a></th>
					</tr>

					<c:forEach var="vo" items="${requestScope.sportsmain3 }"
						varStatus="status" begin="0" end="10" step="2">
						<tr class="success">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
								href="/semi_team1/select?num=${sportsmain3[status.index].num}">${sportsmain3[status.index].title_name}
							</a></td>
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
								href="/semi_team1/select?num=${sportsmain3[status.index+1].num}">${sportsmain3[status.index+1].title_name}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>