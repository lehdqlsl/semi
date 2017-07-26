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

<div id="reply">
	<h1>신고 댓글 조회</h1>
	<table id="replylist" border="1" width="500">
		<tr>
			<td>댓글번호</td><td>닉네임</td><td>글내용</td><td>신고상태</td>	
			</tr>
		<c:forEach var="vo"  items="${requestScope.list }">
			<tr>
				<td>${vo.r_num }</td>
				<td>${vo.nick }</td>
				<td>${vo.content }</td>
				<td>${vo.report }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<!--  페이징 -->
	<div id="paging2">
	<!-- 이전 -->
	<c:choose>
		<c:when test="${startPage>10 }">
			<a href="/semi_team1/reportlist.do?pageNum=${startPage-1 }">[이전]</a>
		</c:when>
		<c:otherwise>
			[ ◁ ]
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i"  begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="/semi_team1/reportlist.do?pageNum=${i }"><span style="color:blue">[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="/semi_team1/reportlist.do?pageNum=${i }"><span style="color:#aaa">[${i }]</span></a>
			</c:otherwise>			
		</c:choose>
	</c:forEach>
	<!--  다음 -->
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="/semi_team1/reportlist.do?pageNum=${endPage+1 }">[다음]</a>
		</c:when>
		<c:otherwise>
			[ ▷ ]
		</c:otherwise>
	</c:choose>
	</div>
</div>

</body>
</html>