<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>

<body>
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
			<a class="navbar-brand" href="index.jsp?page=content.jsp">Project name</a>
		</div>

		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
			</ul>
			<!-- 로그인 및 회원가입 버튼 -->
			<div class="navbar-form navbar-right">
				<Button type="button" class="btn btn-success" onclick = "location.href = 'index.jsp?page=login/signin.jsp';" >로그인</Button>
				<Button type="button" class="btn btn-success" onclick = "location.href = 'index.jsp?page=join/joinForm.jsp';">회원가입</Button>
			</div>
		</div>





	</div>
	<!-- /.container --> </nav>
	<!-- /.navbar -->
</body>
</html>