<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
			<table class="table table-bordered">
				<tr style="text-align: center; font-weight: bold; font-size: 15px;">
					<td>순위</td>
					<td>아이디</td>
					<td>닉네임</td>
					<td>경험치</td>
					<td>등급</td>
					<td></td>
				</tr>
				<c:forEach var="vo" items="${requestScope.list }" varStatus="status">
					<tr style="text-align: center;">
						<td style="padding: 20px;">${status.index+1}</td>
						<td style="padding: 20px; font-weight: bold;">${vo.id }</td>
						<td style="padding: 20px;">${vo.m_nick }</td>
						<td style="padding: 20px;">${vo.exp }</td>
						<td style="padding: 20px;">${vo.grade }</td>
						<td><img src="/semi_team1/rs/img/${vo.grade }.png"
							style="width: 50px; height: 50px;"></td>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>