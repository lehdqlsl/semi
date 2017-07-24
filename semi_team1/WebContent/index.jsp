<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link href="/semi_team1/rs/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/semi_team1/rs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="offcanvas.css" rel="stylesheet">
<script src="/semi_team1/rs/assets/js/ie-emulation-modes-warning.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%
		String spage = request.getParameter("page");
		if (spage == null) {
			spage = "content.jsp";
		}
	%>

	<div id="wrap">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="<%=spage%>"></jsp:include>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>