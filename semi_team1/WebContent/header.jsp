<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	//var test="location.href='/semi_team1/index.jsp?page=profile/userinfo.jsp'";

	var msgxhr = null;
	function msgrefresh() {

		var nick = ${empty sessionScope.m_nick};
		if(nick != true){
			msgxhr = new XMLHttpRequest();
			msgxhr.onreadystatechange = callback;
			msgxhr.open('get', '/semi_team1/message2/msgrefresh.jsp', true);
			msgxhr.send();
		}
	}
	function callback() {
		if (msgxhr.readyState == 4 && msgxhr.status == 200) {
			var data = msgxhr.responseText;
			var json = eval('(' + data + ')');
			var div = document.getElementById("cnt");
			div.innerHTML = "쪽지 " + json.cnt;
		}
	}
</script>
</head>
<body onload="msgrefresh()">
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
			<a class="navbar-brand" href="/semi_team1/mainlist">유리웹</a>
		</div>

		<div id="navbar" class="collapse navbar-collapse">

			<ul class="nav navbar-nav">
				<li id="c1"><a
					href="/semi_team1/index.jsp?page=game/ranking">게임</a></li>
				<li id="c2"><a
					href="/semi_team1/movielist">영화</a></li>
				<li id="c3"><a
					href="/semi_team1/sportsmain">스포츠</a></li>
				<li id="c4"><a
					href="/semi_team1/tasty/main">맛집</a></li>
				<li id="c5"><a
					href="/semi_team1/gallerylist">음악</a></li>
				<li id="c6"><a href="/semi_team1/index.jsp?page=ranking/list">랭킹</a></li>

				<c:if test="${sessionScope.m_nick == 'admin'}">
					<li id="c7"><a
						href="/semi_team1/index.jsp?page=manager/managerIndex.jsp">관리자페이지</a></li>
				</c:if>
			</ul>
			<!-- 로그인 및 회원가입 버튼 -->
			<c:choose>
				<c:when test="${empty sessionScope.m_nick }">
					<div class="navbar-form navbar-right">
						<Button type="button" class="btn btn-success"
							onclick="location.href = '/semi_team1/index.jsp?page=login/signin.jsp';">로그인</Button>
						<Button type="button" class="btn btn-success"
							onclick="location.href = '/semi_team1/index.jsp?page=join/joinForm.jsp';">회원가입</Button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="navbar-form navbar-right" style="margin-top: 0px;">
						<ul class="nav navbar-nav">
							<li><a href="/semi_team1/recvlist2" id="cnt"></a></li>
							<li><a>${sessionScope.m_nick } 님</a></li>
							<li><a
								href="/semi_team1/index.jsp?page=profile/userinfo.jsp?num=${sessionScope.num }">회원정보</a></li>
						</ul>
						<Button type="button" class="btn btn-success"
							onclick="location.href = '/semi_team1/login.do?cmd=logout';"
							style="margin: 8px">로그아웃</Button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</nav>

</body>
</html>