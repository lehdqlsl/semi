<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<thead>
					<tr>
						<th style="width: 100px">게시글번호</th>
						<th style="text-align: center">제목</th>
						<th style="width: 140px">글쓴이</th>
						<th style="width: 50px; text-align: center">추천</th>
						<th style="width: 75px; text-align: center">조회</th>
						<th style="width: 100px; text-align: center">날짜</th>
					</tr>
				</thead>
				<c:forEach var="vo" items="${requestScope.list }">
					<tr>
					
						<td style="text-align: center">${vo.num }</td>
						<td><a href="/semi_team1/select?num=${vo.num }&writer=${vo.writer}">${vo.title_name }</a></td>
						<td>${vo.writer }</td>
						<td style="text-align: center">${vo.up }</td>
						<td style="text-align: center">${vo.hits }</td>
						<td style="text-align: center">${vo.regdate }</td>
					</tr>
				</c:forEach>
			</table>
			
			<div align="center">
<!-- 이전 -->
<c:choose>
	<c:when test="${startPage>20 }">
		<a href="/semi_team1/mywritelist?pageNum=${startPage-1 }"><input class="btn btn-xs btn-primary" type="submit" value="◁"></a>
	</c:when>
	<c:otherwise>
		<input class="btn btn-xs btn-primary" type="submit" value="◁">
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="/semi_team1/mywritelist?writer=${writer }pageNum=${i }"><input class="btn btn-xs btn-link" type="submit" value="${i}"></a>
			
		</c:when>
		<c:otherwise>
			<a href="/semi_team1/mywritelist?writer=${writer }pageNum=${i }"><input class="btn btn-xs btn-link" type="submit" value="${i}"></a>
			
		</c:otherwise>
	</c:choose>
</c:forEach>
<!-- 다음 -->
<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="/semi_team1/mywritelist?writer=${writer }pageNum=${endPage+1 }">
		<input class="btn btn-xs btn-primary" type="submit" value="▷">		
		</a>
	</c:when>
	<c:otherwise>
		<input class="btn btn-xs btn-primary" type="submit" value="▷">
	</c:otherwise>
</c:choose>

</div>
			
	
</div>
</div>
			
</body>
</html>