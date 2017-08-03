<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link href="/semi_team1/rs/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/semi_team1/dashboard.css" rel="stylesheet">
<link href="/semi_team1/rs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="/semi_team1/offcanvas.css" rel="stylesheet">
<script src="/semi_team1/rs/assets/js/ie-emulation-modes-warning.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	var bCheckXhr=null;
	function boardCheck(snum){
		if(snum==1){
			location.href="/semi_team1/game/bestList?snum=1";
		}else if(snum==2){
			location.href="/semi_team1/game/bestList?snum=2";
		}else if(bnum==3){
			location.href="/semi_team1/game/bestList?snum=3";
		}else if(bnum==4){
			location.href="/semi_team1/game/bestList?snum=4";
		}
	}
	
</script>
</head>
<body>
	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>

	<!-- 메인페이지 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	
		<div id="wrap" style="width:100%">
		<div id="gameRanking" style="width:600px;float:left">
			<h2>게임페이지</h2>
			<strong style="color: #747474"> 게임 평점 랭킹</strong>
			<div class="row" style="width:80%">
				<div class="col-sm-4" style="width:100%">
					<ul class="list-group">
						<c:forEach var="vo" items="${requestScope.list }"
							varStatus="status">
							<c:choose>
								<c:when test="${status.count==1 }">
									<li class="list-group-item">
										<div>
											<button type="button" class="btn btn-xs btn-primary">${status.count}</button>
											<a href="/semi_team1/gameinfo?g_num=${vo.g_num}"> <img
												src="/semi_team1/rs/img/gameImg/${vo.saveImg }"
												style="width: 130px; height: 130px"></a>
											<div
												style="width: 180px; display: inline-block; height: 80px; margin-left: 5px">
												<a href="/semi_team1/gameinfo?g_num=${vo.g_num}">${vo.g_name}</a><br>
												<b style="color: #FF7171">${vo.rating } 점</b>
											</div>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<li class="list-group-item">
										<button type="button" class="btn btn-xs btn-info">${status.count}</button>
										<a href="/semi_team1/gameinfo?g_num=${vo.g_num}">${vo.g_name}</a><br>
										<b style="color: #FF7171">${vo.rating } 점</b>
								</c:otherwise>
							</c:choose>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div id="best" style="width:500px;display:inline-block;">
			<strong style="color: #747474"> BEST 게시글</strong>
			<ul class="nav nav-pills" role="tablist">
				<li role="presentation" onmouseover="boardCheck(1)"><a href="#">자유게시판</a></li>
				<li role="presentation" onmouseover="location.href='/semi_team1/game/bestList?bnum=2"><a href="#">공략게시판</a></li>
				<li role="presentation" onmouseover="location.href='/semi_team1/game/bestList?snum=3"><a href="#">질문게시판</a></li>
				<li role="presentation" onmouseover="location.href='/semi_team1/game/bestList?snum=4"><a href="#">게임게시판</a></li>
			</ul>
			<div id="bestListArea" style="width:400px;height:425px;background-color:pink;float:left">
			<table>
				<c:forEach var="vo" items="${requestScope.bestList1 }">
					<tr>
						<td><a href="/semi_team1/select?num=${vo.num }">${vo.title_name}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</div>
	</div>
</div>
</body>
</html>