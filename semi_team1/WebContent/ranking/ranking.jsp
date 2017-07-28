<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
			<table class="table table-bordered">
				<tr>
					<td>순위</td>
					<td>아이디</td>
					<td>닉네임</td>
					<td>경험치</td>
					<td>등급</td>
					<td>사진</td>		
				</tr>
				<c:forEach var="vo" items="${requestScope.list }" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${vo.id }</td>
						<td>${vo.m_nick }</td>
						<td>${vo.exp }</td>
						<td>${vo.grade }</td>
						<td></td>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>