<%@page import="com.team1.vo.boardVo"%>
<%@page import="com.team1.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/semi_team1/rs/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/semi_team1/rs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="signin.css" rel="stylesheet">
<script src="/semi_team1/rs/assets/js/ie-emulation-modes-warning.js"></script>
<%
	request.setCharacterEncoding("utf-8");
	int num=Integer.parseInt(request.getParameter("num"));
	BoardDao dao=new BoardDao();
	boardVo vo=dao.select(num);
%>
</head>
<body>
<div style="margin: auto; width: 1000px">
<form method="post" action="/semi_team1/update" enctype="multipart/form-data">
	<input type="hidden" name="filenum" value="<%=num %>"> <%-- --%>
	<input type="hidden" name="savefilename" value="<%=vo.getSavefilename() %>">  <%-- --%>
	<table class="table table-bordered">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" readonly="readonly" value="<%=vo.getWriter() %>"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title_name" value="<%=vo.getTitle_name() %>"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea cols="50" rows="20" name="content"><%=vo.getContent() %></textarea></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><input type="file" name="file1"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input class="btn btn-success" type="submit" value="전송">
				<input class="btn btn-success" type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>
</div>


</body>
</html>