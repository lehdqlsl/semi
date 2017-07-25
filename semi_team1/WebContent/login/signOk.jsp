<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id=request.getParameter("id");
	String u_pw=request.getParameter("u_pw");
	String box=request.getParameter("box");
	
	if (box != null) {
		//쿠키 생성
		Cookie cook1 = new Cookie("id", id);
		cook1.setMaxAge(60*60*24*30);
		response.addCookie(cook1);

		Cookie cook2 = new Cookie("u_pw", u_pw);
		cook2.setMaxAge(60*60*24*30);
		response.addCookie(cook2);
	}else{
		Cookie cook1 = new Cookie("id", id);
		cook1.setMaxAge(0);
		response.addCookie(cook1);

		Cookie cook2 = new Cookie("u_pw", u_pw);
		cook2.setMaxAge(0);
		response.addCookie(cook2);
	}
%>
</body>
</html>