<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div
			style="width: 600px; word-break: break-all; word-wrap: break-word; padding-left: 100px">
			<table class="table table-bordered">
				<tr>
					<td>보낸사람</td>
					<td>${vo.sender }</td>
				</tr>

				<tr>
					<td>받은시간</td>
					<td>${vo.regdate }</td>
				</tr>

				<tr>
					<td colspan="2" style="height: 400px;">${vo.content }</td>
				</tr>

			</table>
			<table align="right">
				<tr>
					<td><input class="btn btn-success" type="button" value="답장"
						onclick="location.href = '/semi_team1/index.jsp?page=message2/insert2.jsp&sender=${vo.sender}';">
						<input class="btn btn-success" type="button" value="삭제"
						onclick="location.href = '/semi_team1/recvdelete2?num=${vo.num}';"> <input class="btn btn-success"
						type="button" value="목록"
						onclick="location.href = '/semi_team1/recvlist2';"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>