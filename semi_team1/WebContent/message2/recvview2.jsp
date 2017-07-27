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
		<div style="margin: auto; width: 1000px">
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
					<td colspan="2">${vo.content }</td>
				</tr>
				
			</table>
			<table align="right">
				<tr>
					<td><input class="btn btn-success" type="button" value="삭제"
						onclick=delete()> 
						<input class="btn btn-success" type="button" value="목록"
						onclick="location.href = '/semi_team1/list';"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>