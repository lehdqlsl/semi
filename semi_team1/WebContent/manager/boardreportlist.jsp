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
<div id="board">
	<h1>신고 글 조회</h1>
	<table id="boardlist" border="1" width="800">
		<tr>
			<td>글번호</td><td>대분류</td><td>소분류</td><td>글제목</td><td>등록일</td><td>닉네임</td><td>신고 상태</td>	
			</tr>
		<c:forEach var="vo"  items="${requestScope.list }">
			<tr>
				<td>${vo.num }</td>
				<td>${vo.f_num }</td>
				<td>${vo.s_num }</td>
				<td>${vo.title_name }</td>
				<td>${vo.regdate }</td>
				<td>${vo.writer }</td>
				<td>${vo.report }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<!--  페이징 -->
	<div id="paging1">
	<!-- 이전 -->
	<c:choose>
		<c:when test="${startPage>10 }">
			<a href="/semi_team1/boardreportlist.do?pageNum=${startPage-1 }">[이전]</a>
		</c:when>
		<c:otherwise>
			[ ◁ ]
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i"  begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="/semi_team1/boardreportlist.do?pageNum=${i }"><span style="color:blue">[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="/semi_team1/boardreportlist.do?pageNum=${i }"><span style="color:#aaa">[${i }]</span></a>
			</c:otherwise>			
		</c:choose>
	</c:forEach>
	<!--  다음 -->
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="/semi_team1/boardreportlist.do?pageNum=${endPage+1 }">[다음]</a>
		</c:when>
		<c:otherwise>
			[ ▷ ]
		</c:otherwise>
	</c:choose>
	</div>
</div>

</body>
</html>