<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/semi_team1/index.jsp?page=content.jsp">우리웹(임시)</a>
		</div>

		<div id="navbar" class="collapse navbar-collapse">

			<ul class="nav navbar-nav">
				<li id="c1"><a
					href="/semi_team1/index.jsp?page=game/gameIndex.jsp&f_num=1">게임</a></li>
				<li id="c2"><a
					href="/semi_team1/index.jsp?page=movie/movieIndex.jsp&f_num=2">영화</a></li>
				<li id="c3"><a
					href="/semi_team1/index.jsp?page=sport/mainForm.jsp">스포츠</a></li>
				<li id="c4"><a
					href="/semi_team1/index.jsp?page=tasty/mainForm.jsp">맛집</a></li>
				<li id="c5"><a
					href="/semi_team1/index.jsp?page=music/mainForm.jsp">음악</a></li>
			</ul>
			<!-- 로그인 및 회원가입 버튼 -->
			<div class="navbar-form navbar-right">

<<<<<<< HEAD
			<c:choose>
				<c:when test="${empty sessionScope.m_nick }">
					<div class="navbar-form navbar-right">
						<Button type="button" class="btn btn-success"
							onclick="location.href = 'index.jsp?page=login/signin.jsp';">로그인</Button>
						<Button type="button" class="btn btn-success"
							onclick="location.href = 'index.jsp?page=join/joinForm.jsp';">회원가입</Button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="navbar-form navbar-right" style="margin-top: 0px;">
						<ul class="nav navbar-nav">
							<li><a>${sessionScope.m_nick } 님</a></li>
							<li><a href="/semi_team1/index.jsp?page=profile/userinfo.jsp?num=${sessionScope.num }">회원정보</a></li>
						</ul>
						<Button type="button" class="btn btn-success"
							onclick="location.href = '/semi_team1/login.do?cmd=logout';"
							style="margin: 8px">로그아웃</Button>
					</div>
				</c:otherwise>
			</c:choose>
=======
				<Button type="button" class="btn btn-success"
					onclick="location.href = 'index.jsp?page=login/signin.jsp';">로그인</Button>
				<Button type="button" class="btn btn-success"
					onclick="location.href = 'index.jsp?page=join/joinForm.jsp';">회원가입</Button>
>>>>>>> branch 'master' of https://github.com/lehdqlsl/semi


			</div>
		</div>
	</div>
	</nav>

</body>
</html>