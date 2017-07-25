<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="/semi_team1/rs/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/semi_team1/rs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="signin.css" rel="stylesheet">
<script src="/semi_team1/rs/assets/js/ie-emulation-modes-warning.js"></script>
</head>
<body>
<%
	Cookie [] cooks=request.getCookies();
	String id="";
	String u_pw="";
	boolean checked=false;
	if(cooks!=null){
		for(Cookie cook:cooks){
			if(cook.getName().equals("id")){
				id=cook.getValue();
				checked=true;
			}else if(cook.getName().equals("u_pw")){
				u_pw=cook.getValue();
			}
		}
	}
%>

	<div class="container" style="width: 330px; height: 236px">
		<form method="post" action="/semi_team1/login.do?cmd=login"  class="form-signin">
			<h2 class="form-signin-heading">로그인</h2>
			<label for="id" class="sr-only">아이디</label> 
			<input type="text" id="id" name="id" value="<%=id%>" class="form-control" placeholder="아이디" required autofocus> <br>
			<label for="inputPassword" class="sr-only">비밀번호</label> 
			<input type="password" id="u_pw" name="u_pw" value="<%=u_pw%>" class="form-control" placeholder="비밀번호" required autofocus>
			<div class="checkbox">
				<label> 
				<%
					if(checked){
						%>
						<input type="checkbox" id="box"  name="box" value="remember-me" checked="checked">자동 로그인
						<%
						
					}else{
						%>
						<input type="checkbox" id="box" name="box" value="remember-me">자동 로그인
						<%
					}
				%>
				</label>
			</div>
			<div id="errMsg" style="color:red;font-size:15px;">${requestScope.errMsg}</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		</form>

	</div>
	<!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</head>
<body>

</body>
</html>