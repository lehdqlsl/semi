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

</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String scnt=request.getParameter("cnt");
	String writer=request.getParameter("writer");
	String title_name=request.getParameter("title_name");
	String content=request.getParameter("content");
	
	int cnt=0;
	if(scnt!=null){
		cnt=Integer.parseInt(scnt);
	}
	if(writer==null){
		writer="";
		title_name="";
		content="";
	}

%>
<div style="margin: auto; width: 1000px">
<form action="/semi_team1/insert" method="post">
	<input type="hidden" name="f_num" value="1"> 
	<input type="hidden" name="s_num" value="1">
	<table	class="table table-bordered">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>	
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title_name"></td>	
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea cols="50" rows="20" name="content"></textarea></td>	
		</tr>
		
		<tr>
		<td>첨부파일갯수</td>
		<td>
		<input type="text" name="cnt" value="<%=cnt %>">
		<!-- <input type="submit" value="확인"> -->
		</td>
	</tr>
		<tr>
		<td colspan="2" align="center">
		<input class="btn btn-success" type="submit" value="확인" >
		</td>
		</tr>
</table>
</form>
</div>
<!--
<form method="post" action="/semi_team1/insert" enctype="multipart/form-data">
	<input type="hidden" name="writer" value="<%=writer %>"> <%-- --%>
	<input type="hidden" name="title" value="<%=title_name %>">  <%-- --%>
	<input type="hidden" name="content" value="<%=content %>"> <%-- --%>
	
	<table border="1" width="500">

	<%
		for(int i=1;i<=cnt;i++){
	%>

		<tr>
		<td>첨부파일<%=i %></td>
		<td><input type="file" name="file<%=i %>"></td>
		</tr>
	<%
		}
	%>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="전송">
			</td>
		</tr>
	</table>
</form>
 -->
</body>
</html>