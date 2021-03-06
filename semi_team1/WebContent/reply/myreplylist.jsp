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
						<th style="width: 100px">번호</th>
						<th style="width: 140px">글쓴이</th>
						<th style="width: 50px; text-align: center">내용</th>
						<th style="width: 75px; text-align: center">추천</th>
						<th style="width: 100px; text-align: center">날짜</th>
					</tr>
				</thead>
				<c:forEach var="vo" items="${requestScope.list }">
					<tr>
					
						<td style="text-align: center">${vo.r_num }</td>
						<td>${vo.nick }</td>
						<td>${vo.content }</td>
						<td style="text-align: center">${vo.up }</td>
						<td style="text-align: center">${vo.reg_date }</td>
					</tr>
				</c:forEach>
			</table>
			
			<div align="center">
<c:choose>
	<c:when test="${startPage>5}">
		<a href="/semi_team1/myreplylist?pageNum=${startPage-1 }&nick=${requestScope.nick}"><input class="btn btn-xs btn-primary" type="submit" value="◁"></a>	
	</c:when>
	<c:otherwise>
		<input class="btn btn-xs btn-primary" type="submit" value="◁">
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage }" end="${endPage }" >
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="/semi_team1/myreplylist?pageNum=${i }&nick=${requestScope.nick}"><input class="btn btn-xs btn-link" type="submit" value="${i}"></a>	
		</c:when>
		<c:otherwise>
			<a href="/semi_team1/myreplylist?pageNum=${i }&nick=${requestScope.nick}"><input class="btn btn-xs btn-link" type="submit" value="${i}"></a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="/semi_team1/myreplylist?pageNum=${endPage+1 }&nick=${requestScope.nick}"><input class="btn btn-xs btn-primary" type="submit" value="▷">	</a>
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