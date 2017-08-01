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
					<c:choose>
						<c:when test="${status.index+1 == 1}">
							<tr
								style="text-align: center; background-color: #FFD700; font-size: 18px;">
								<td><img src="/semi_team1/rs/img/gold.png"></td>
								<td style="padding: 36px; font-weight: bold;">${vo.id }</td>
								<td style="padding: 36px;">${vo.m_nick }</td>
								<td style="padding: 36px;">${vo.exp }</td>
								<td style="padding: 36px;">${vo.grade }</td>
								<td><img src="/semi_team1/rs/img/${vo.grade }.png"
									style="width: 80px; height: 80px;"></td>
						</c:when>
						<c:when test="${status.index+1 == 2}">
							<tr
								style="text-align: center; background-color: #C0C0C0; font-size: 17px;">
								<td><img src="/semi_team1/rs/img/silver.png"></td>
								<td style="padding: 35px; font-weight: bold;">${vo.id }</td>
								<td style="padding: 35px;">${vo.m_nick }</td>
								<td style="padding: 35px;">${vo.exp }</td>
								<td style="padding: 35px;">${vo.grade }</td>
								<td><img src="/semi_team1/rs/img/${vo.grade }.png"
									style="width: 75px; height: 75px;"></td>
						</c:when>
						<c:when test="${status.index+1 == 3}">
							<tr
								style="text-align: center; background-color: #CD7F32; font-size: 16px;">
								<td><img src="/semi_team1/rs/img/bronze.png"></td>
								<td style="padding: 35px; font-weight: bold;">${vo.id }</td>
								<td style="padding: 35px;">${vo.m_nick }</td>
								<td style="padding: 35px;">${vo.exp }</td>
								<td style="padding: 35px;">${vo.grade }</td>
								<td><img src="/semi_team1/rs/img/${vo.grade }.png"
									style="width: 70px; height: 70px;"></td>
						</c:when>

						<c:otherwise>
							<tr style="text-align: center; font-size: 15px;">
								<td style="padding: 24px; font-weight: bold;">${status.index+1}</td>
								<td style="padding: 24px; font-weight: bold;">${vo.id }</td>
								<td style="padding: 24px;">${vo.m_nick }</td>
								<td style="padding: 24px;">${vo.exp }</td>
								<td style="padding: 24px;">${vo.grade }</td>
								<td><img src="/semi_team1/rs/img/${vo.grade }.png"
									style="width: 65px; height: 65px;"></td>
						</c:otherwise>

					</c:choose>



				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>