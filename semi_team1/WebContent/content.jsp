<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

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
	<!-- 메인페이지 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-9 col-md-offset-2 main">

		<div
			style="margin: auto; width: 1100px; word-break: break-all; word-wrap: break-word;">

			<table class="table">
				<tr>
					<th style="font-size: 18px;" colspan="2"><a
						href="/semi_team1/game/ranking">게임</a></th>
				</tr>
			</table>
			<c:forEach var="vo" items="${requestScope.main1 }">
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
							<span style="font-size: 9px; text-decoration: none;"><span
								style="color: #666">평점:</span> <span style="color: #f3614c">${vo.avg }</span></span>
						</p>
						<p>
							<span style="font-size: 9px; text-decoration: none;"><span
								style="color: #666">장르:</span> <span style="color: #000">${vo.g_jenre }</span></span>
						</p>
						<p>
							<span style="font-size: 9px; text-decoration: none;"><span
								style="color: #666">발매일:</span> <span style="color: #000">${vo.l_date }</span></span>
						</p>
					</a>
				</div>
			</c:forEach>
			<hr>

		</div>


		<div
			style="margin: auto; width: 1100px; word-break: break-all; word-wrap: break-word;">
			<div style="float: left; width: 500px; margin: 10px;">
				<table class="table table-bordered">
					<tr>
						<td><span style="font-size: 20px; font-weight: bold;"><a
								href="/semi_team1/movielist">영화 평점 & 리뷰</a></span></td>
					</tr>
					<c:forEach var="vo" items="${requestScope.main2 }"
						varStatus="status">
						<tr>
							<td height="60px" width="450px">
								<button type="button" class="btn btn-xs btn-default">${status.count}</button>

								<a href="/semi_team1/movieselect?m_num=${vo.m_num }">${vo.m_name}</a><br>
								<b style="color: #FF7171">${vo.rating } 점</b>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="float: left; width: 500px; margin: 10px;">
				<table class="table table-bordered">
					<tr>
						<td><span style="font-size: 20px; font-weight: bold;"><a
								href="/semi_team1/tasty/main">맛집 평점 & 리뷰</a></span></td>
					</tr>
					<c:forEach var="vo" items="${requestScope.main4 }"
						varStatus="status">
						<tr>
							<td height="60px" width="450px">
								<button type="button" class="btn btn-xs btn-default">${status.count}</button>

								<a href="/semi_team1/tasty/select?num=${vo.num }">${vo.title}</a><br>
								<b style="color: #FF7171">${vo.up } 점</b>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>



		</div>

		<div
			style="margin: auto; width: 1100px; word-break: break-all; word-wrap: break-word;">

			<table class="table">
				<tr>
					<th style="font-size: 18px;" colspan="2"><a
						href="/semi_team1/sportsmain">스포츠</a></th>
				</tr>

				<c:forEach var="vo" items="${requestScope.main3 }"
					varStatus="status" begin="0" end="10" step="2">
					<tr>
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${main3[status.index].num}">${main3[status.index].title_name}
						</a></td>
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${main3[status.index+1].num}">${main3[status.index+1].title_name}</a></td>
					</tr>
				</c:forEach>
			</table>





			<div
				style="margin: auto; width: 1100px; word-break: break-all; word-wrap: break-word;">
				<table class="table table-bordered" width="800px" height="150px">
					<tr>
						<td colspan="7" style="font-size: 18px; font-weight: bold"><a
							href="#">음악 정보</a></td>
					</tr>
					<tr>
						<td><c:choose>
								<c:when test="${startPageNum>1 }">
									<a
										href="/semi_team1/mainlist?num=${num }&pageNum=${startPageNum-1 }">
										<input class="btn btn-xs btn-primary" type="submit" value="◁"
										style="vertical-align: center;">
									</a>
								</c:when>
								<c:otherwise>
									<input class="btn btn-xs btn-primary" type="submit" value="◁">
								</c:otherwise>
							</c:choose></td>
						<c:forEach var="vo" items="${requestScope.main5 }">
							<td align="center"><a
								href="/semi_team1/musicSelect?num=${vo.num}"><img
									src="/semi_team1/rs/img/musicImg/${vo.savemimg}"
									class="img-circle" onmouseover="showImg(event)"
									onmouseout="noshowImg(event)"
									style="height: 150px; width: 150px; opacity: 0.7"></a></td>
						</c:forEach>
						<td><c:choose>
								<c:when test="${endPageNum<pageCount }">
									<a
										href="/semi_team1/mainlist?num=${num }&pageNum=${endPageNum+1 }">
										<input class="btn btn-xs btn-primary" type="submit" value="▷">
									</a>
								</c:when>
								<c:otherwise>
									<input class="btn btn-xs btn-primary" type="submit" value="▷">
								</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
			</div>

		</div>
	</div>
	</div>



	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="/semi_team1/rs/dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/semi_team1/rs/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="offcanvas.js"></script>
</body>
</html>