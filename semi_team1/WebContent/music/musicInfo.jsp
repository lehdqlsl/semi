<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1>음악 정보 페이지입니다.</h1>
		<c:choose>
			<c:when test="${sessionScope.m_nick=='admin' }">
				<input type="button" value="음악 등록" class="btn btn-xs btn-default" onclick="location.href='/semi_team1/index.jsp?page=music/musicInsert.jsp'">
			</c:when>
		</c:choose>
	</div>
</body>
</html>