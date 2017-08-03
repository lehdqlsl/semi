<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function showImg(event) {
		var img = event.target;
		img.style.opacity = 1.2;

	}
	function noshowImg(event) {
		var img = event.target;
		img.style.opacity = 0.7;
	}
</script>
</head>
<body>

	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>

	<!-- 메인페이지 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<c:choose>
			<c:when test="${sessionScope.m_nick=='admin' }">
				<input type="button" value="음악 등록" class="btn btn-xs btn-default" onclick="location.href='/semi_team1/index.jsp?page=music/musicInsert.jsp'">
			</c:when>
		</c:choose>
	</div>
	
	<div
		style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
		<table class="table table-bordered" width="875px" height="200px">
			<td><c:choose>
					<c:when test="${startPageNum>1 }">
						<a
							href="/semi_team1/musiclist?num=${num }&pageNum=${startPageNum-1 }">
							<input class="btn btn-xs btn-primary" type="submit" value="◁"
							style="vertical-align: center;">
						</a>
					</c:when>
					<c:otherwise>
						<input class="btn btn-xs btn-primary" type="submit" value="◁">
					</c:otherwise>
				</c:choose></td>
			<c:forEach var="vo" items="${requestScope.list }">
				<td align="center"><a
					href="/semi_team1/musicSelect?num=${vo.num}"><img
						src="/semi_team1/rs/img/musicImg/${vo.savemimg}"
						onmouseover="showImg(event)" onmouseout="noshowImg(event)"
						style="height: 200px; width: 200px;opacity: 0.7"></a>
				</td>
			</c:forEach>
			<td><c:choose>
					<c:when test="${endPageNum<pageCount }">
						<a
							href="/semi_team1/musiclist?num=${num }&pageNum=${endPageNum+1 }">
							<input class="btn btn-xs btn-primary" type="submit" value="▷">
						</a>
					</c:when>
					<c:otherwise>
						<input class="btn btn-xs btn-primary" type="submit" value="▷">
					</c:otherwise>
				</c:choose></td>
		</table>
	</div>
	
</body>
</html>