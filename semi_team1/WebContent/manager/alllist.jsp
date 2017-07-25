<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#wrap{width:80%;;margin:auto;}
	#memList{width:80%;margin:auto;}
	#paging{width:10%;margin:auto;}
	#title{width:250px;margin:auto;}
</style>
</head>
<body>
<div id="wrap">
	<h1 id="title">관리자페이지</h1>
	<br>
	</div>
	<table id="memList" border="1" width="500">
		<tr>
			<td>회원번호</td><td>아이디</td><td>닉네임</td><td>이메일</td><td>회원등급</td><td>회원경험치</td><td>가입날짜</td><td>상태</td>
		</tr>
		<c:forEach var="vo"  items="${requestScope.list }">
			<tr>
				<td>${vo.num }</td>
				<td>${vo.id }</td>
				<td>${vo.m_nick }</td>
				<td>${vo.m_mail }</td>
				<td>${vo.grade }</td>
				<td>${vo.exp }</td>
				<td>${vo.reg_date }</td>
				<td>${vo.stop}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<!--  페이징 -->
	<div id="paging">
	<!-- 이전 -->
	<c:choose>
		<c:when test="${startPage>5 }">
			<a href="/semi_team1/list.do?pageNum=${startPage-1 }">[이전]</a>
		</c:when>
		<c:otherwise>
			[ ◁ ]
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i"  begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="/semi_team1/list.do?pageNum=${i }"><span style="color:blue">[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="/semi_team1/list.do?pageNum=${i }"><span style="color:#aaa">[${i }]</span></a>
			</c:otherwise>			
		</c:choose>
	</c:forEach>
	<!--  다음 -->
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="/board/list.do?pageNum=${endPage+1 }">[다음]</a>
		</c:when>
		<c:otherwise>
			[ ▷ ]
		</c:otherwise>
	</c:choose>
	</div>
</div>
</body>
</html>