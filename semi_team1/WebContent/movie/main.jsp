<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function showImg(event) {
		var img = event.target;
		img.style.opacity = 1.2;
	}
	function noshowImg(event) {
		var img = event.target;
		img.style.opacity = 0.7;
	}

	var imgs = [
			[ 'movie/2.jpg', 'http://www.vlive.tv/video/36701' ],
			[ 'movie/3.png',
					'http://nstore.naver.com/event/details.nhn?eventNo=9547' ],
			[ 'movie/4.png',
					'http://movie.naver.com/movie/other/gonggi.nhn?docID=10000000000027004502' ],
			[ 'movie/5.png',
					'http://nstore.naver.com/event/details.nhn?eventNo=9580&productType=movie' ],
			[ 'movie/1.jpg', 'http://www.vlive.tv/video/36398' ] ];

	var index = 0;
	setTimeout(change, 1000);
	function change() {
		var changeMain = document.getElementById("changeMain");
		changeMain.src = imgs[index][0];

		changeMain.onclick = function() {
			location.href = imgs[index - 1][1];
		};
		index++;
		if (index == imgs.length)
			index = 0;
		setTimeout(change, 3000);
	}
</script>
<body>
	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>


	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">

			<c:choose>
				<c:when test="${sessionScope.m_nick=='admin' }">
					<input type="button" value="영화정보 등록" class="btn btn-default"
						onclick="location.href='/semi_team1/index.jsp?page=movie/insert.jsp'">
					<br>
					<br>
				</c:when>
			</c:choose>


			<table class="table table-bordered" width="875px" height="250px">

				<td><c:choose>
						<c:when test="${startPageNum>1 }">
							<a
								href="/semi_team1/movielist?m_num=${m_num }&pageNum=${startPageNum-1 }">
								<input class="btn btn-xs btn-default" type="submit" value="◁"
								style="vertical-align: center;">
							</a>
						</c:when>
						<c:otherwise>
							<input class="btn btn-xs btn-default" type="submit" value="◁">
						</c:otherwise>
					</c:choose></td>

				<c:forEach var="vo" items="${requestScope.list }">
					<td align="center"><a
						href="/semi_team1/movieselect?m_num=${vo.m_num }"> <img
							src="/semi_team1/upload/${vo.saveimg}"
							onmouseover="showImg(event)" onmouseout="noshowImg(event)"
							style="height: 250px; width: 175px; opacity: 0.7">
					</a></td>
				</c:forEach>

				<td><c:choose>
						<c:when test="${endPageNum<pageCount }">
							<a
								href="/semi_team1/movielist?m_num=${m_num }&pageNum=${endPageNum+1 }">
								<input class="btn btn-xs btn-default" type="submit" value="▷">
							</a>
						</c:when>
						<c:otherwise>
							<input class="btn btn-xs btn-default" type="submit" value="▷">
						</c:otherwise>
					</c:choose></td>

			</table>

			<div style="float: left">
			<table class="table table-bordered">
				<tr>
					<td><h4>평점 & 리뷰</h4></td>
				</tr>
				<c:forEach var="vo" items="${requestScope.list2 }"
					varStatus="status">
					<tr>
						<td height="60px" width="750px">
							<button type="button" class="btn btn-xs btn-default">${status.count}</button>

							<a href="/semi_team1/movieselect?m_num=${vo.m_num }">${vo.m_name}</a><br> <b style="color: #FF7171">${vo.rating }
								점</b>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			
			<div style="float: left">
			<table class="table table-bordered">
				<tr>
					<td><h4>스포트라이트</h4></td>
				</tr>
				<tr>
					<td rowspan="5" height="300px">
						<div id="mainImage">
							<img src="movie/1.jpg" id="changeMain"
								onclick="location.href = 'http://www.vlive.tv/video/36398'">
						</div>
					</td>
				</tr>
			</table>
			</div>


			<table class="table table-bordered">
				<tr>
					<td>
						<h1>Movie Ranking</h1>
					</td>
				</tr>
			</table>


		</div>
	</div>

</body>
</html>