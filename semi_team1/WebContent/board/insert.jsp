<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<%
			request.setCharacterEncoding("utf-8");
			String scnt = request.getParameter("cnt");
			String writer = request.getParameter("writer");
			String title_name = request.getParameter("title_name");
			String content = request.getParameter("content");

			//카테고리 번호
			String s_num = request.getParameter("s_num");
			int cnt = 0;
			if (scnt != null) {
				cnt = Integer.parseInt(scnt);
			}
			if (writer == null) {
				writer = "";
				title_name = "";
				content = "";
			}
		%>
		<div style="margin: auto; width: 1000px">
			<form action="/semi_team1/insert" method="post">
				<input type="hidden" value="<%=s_num%>" name="s_num">
				<table class="table table-bordered">
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer"
							value="${sessionScope.m_nick }" readonly="readonly"></td>
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
						<td><input type="text" name="cnt" value="<%=cnt%>"> <!-- <input type="submit" value="확인"> -->
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input class="btn btn-success"
							type="submit" value="확인"> <input class="btn btn-success"
							type="button" value="목록"
							onclick="location.href = '/semi_team1/list';"></td>
					</tr>
				</table>
			</form>
		</div>

	</div>
	<!--
<form method="post" action="/semi_team1/insert" enctype="multipart/form-data">
	<input type="hidden" name="writer" value="<%=writer%>"> <%-- --%>
	<input type="hidden" name="title" value="<%=title_name%>">  <%-- --%>
	<input type="hidden" name="content" value="<%=content%>"> <%-- --%>
	
	<table border="1" width="500">

	<%for (int i = 1; i <= cnt; i++) {%>

		<tr>
		<td>첨부파일<%=i%></td>
		<td><input type="file" name="file<%=i%>"></td>
		</tr>
	<%}%>
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