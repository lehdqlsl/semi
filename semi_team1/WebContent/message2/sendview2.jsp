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
					<td>받는사람</td>
					<td>${vo.receiver }</td>
				</tr>

				<tr>
					<td>보낸시간</td>
					<td>${vo.regdate }</td>
				</tr>

				<tr>
					<td colspan="2" style="height: 400px;">${vo.content }</td>
				</tr>

			</table>
			<table align="right">
				<tr>
					<td><input class="btn btn-success" type="button" value="삭제"
						onclick=delete()> <input class="btn btn-success"
						type="button" value="목록"
						onclick="location.href = '/semi_team1/sendlist2';"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>