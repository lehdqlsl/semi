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
		String s_page = request.getParameter("s_page");
		if (s_page == null) {
			s_page = "content.jsp";
		}
	%>
	
	
	
	
	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>
	<jsp:include page="<%=s_page%>"></jsp:include>
</body>
</html>