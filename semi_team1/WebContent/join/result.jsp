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
<c:choose>
	<c:when test="${result=='success'}">
		<h1>회원 가입 성공!!</h1>
	</c:when>
	<c:when test="${result=='fail'}">
		<h1>회원 가입 실패..</h1>
	</c:when>
</c:choose>
</body>
</html>