<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<!-- 게임정보 -->

		<c:choose>
			<c:when test="${sessionScope.m_nick=='admin' }">
				<input type="button" value="게임정보 등록" class="btn btn-xs btn-default"
					onclick="location.href='/semi_team1/index.jsp?page=game/gameSubmit.jsp'">
			</c:when>
		</c:choose>

		<!-- 페이지형 리스트 -->
		<div
			style="margin-left:100px;width: 987px; word-break: break-all; word-wrap: break-word; height: 35px; background-color: #428bca; margin-bottom: 20px; padding: 1px;">
			<h5 style="margin-left: 10px; font-weight: bold; color: white;">게임정보</h5>
		</div>

		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">

			<c:forEach var="vo" items="${requestScope.list }">
				<div id="imgArea" style="display: inline-block">
					<a href="/semi_team1/gameinfo?g_num=${vo.g_num}"> <img
						src="/semi_team1/rs/img/gameImg/${vo.saveImg}"
						onmouseover="showImg(event)" onmouseout="noshowImg(event)"
						style="height: 180px; width: 120px; opacity: 0.7; margin: auto">
						<p>
							<span
								style="font-size: 13px; text-decoration: none; color: black; font-weight: bold; display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 100px;">${vo.g_name }</span>
						</p>
						<p>
							<span style="font-size: 9px;text-decoration: none;"><span style="color: #666">평점:</span> <span style="color: #f3614c">${vo.avg }</span></span>
						</p>
						<p>
							<span style="font-size: 9px;text-decoration: none;"><span style="color: #666">장르:</span> <span style="color: #000">${vo.g_jenre }</span></span>
						</p>
						<p>
							<span style="font-size: 9px;text-decoration: none;"><span style="color: #666">발매일:</span> <span style="color: #000">${vo.l_date }</span></span>
						</p>
					</a>
				</div>
			</c:forEach>
			<br>
			<!-- 페이징 -->
			<c:choose>
				<c:when test="${startPageNum>16 }">
					<a
						href="/semi_team1/gameList2?g_num=${g_num }&pageNum=${startPageNum-1 }">
						<input class="btn btn-xs btn-primary" type="submit" value="◁">
					</a>
				</c:when>
				<c:otherwise>
					<input class="btn btn-xs btn-primary" type="submit" value="◁">
				</c:otherwise>
			</c:choose>

			<!-- 페이지숫자 -->

			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
				<c:choose>
					<c:when test="${i==pageNum }">
						<input type="hidden" value="${param.pageNum }" name="pageNum">
						<a style="text-decoration: none"
							href="/semi_team1/gameList2?g_num=${g_num }&pageNum=${i }"> <%-- <input class="btn btn-xs btn-link" type="submit" value="${i}"> --%>
							<input class="btn btn-xs btn-link" type="submit" value="${i}">
						</a>
					</c:when>
					<c:otherwise>
						<a style="text-decoration: none"
							href="/semi_team1/gameList2?g_num=${g_num }&pageNum=${i }"> <input
							class="btn btn-xs btn-link" type="submit" value="${i}">
						</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 -->

			<c:choose>
				<c:when test="${endPageNum<pageCount }">
					<a
						href="/semi_team1/gameList2?g_num=${g_num }&pageNum=${endPageNum+1 }">
						<input class="btn btn-xs btn-primary" type="submit" value="▷">
					</a>
				</c:when>
				<c:otherwise>
					<input class="bt n btn-xs btn-primary" type="submit" value="▷">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>