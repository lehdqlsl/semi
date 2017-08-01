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
		<div style="margin: auto; width: 1000px; height: 1300px;">
			<h2>
				<span class="label label-primary">받은 쪽지함</span>
			</h2>
			<br>
			<table class="table table-bordered" >
				<thead>
					<tr style="text-overflow:ellipsis; overflow:hidden;">
						<th>보낸 사람</th>
						<th style="width: 500px">내용</th>
						<th>받은 날짜</th>
						<th>삭제</th>
					</tr>
				</thead>
				<c:forEach var="vo" items="${requestScope.list }">
					<tr>
						<td>${vo.sender }</td>
						<c:choose>
							<c:when test="${vo.chk==1 }">
								<td style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a href="/semi_team1/recvview2?num=${vo.num }">${vo.content }</a></td>
							</c:when>
							<c:otherwise>
								<td><a href="/semi_team1/recvview2?num=${vo.num }"
									style="color: #777;">${vo.content }</a></td>
							</c:otherwise>
						</c:choose>

						<td>${vo.regdate }</td>
						<td><a href="/semi_team1/recvdelete2?num=${vo.num}">삭제</a></td>
					</tr>
				</c:forEach>
			</table>

			<div>
				<c:choose>
					<c:when test="${startPageNum>5 }">
						<a href="/semi_team1/recvlist2?pageNum=${startPageNum-1 }"> <input
							class="btn btn-xs btn-primary" type="submit" value="◁">
						</a>
					</c:when>
					<c:otherwise>
						<input class="btn btn-xs btn-primary" type="submit" value="◁">
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a style="text-decoration: none"
								href="/semi_team1/recvlist2?pageNum=${i }"> <input
								class="btn btn-xs btn-link" type="submit" value="${i}">
							</a>
						</c:when>
						<c:otherwise>
							<a style="text-decoration: none"
								href="/semi_team1/recvlist2?pageNum=${i }"> <input
								class="btn btn-xs btn-link" type="submit" value="${i}">
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${endPageNum<pageCount }">
						<a href="/semi_team1/recvlist2?pageNum=${endPageNum+1 }"> <input
							class="btn btn-xs btn-primary" type="submit" value="▷">
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