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
	<div style="margin: auto; width: 800px; word-break: break-all; word-wrap: break-word;">
		<table class="table table-bordered" width="600px" border="1">
			<tr>
				<td rowspan="4" width="210px" height="200px">
				<img src="/semi_team1/rs/img/musicImg/${vo.savemimg }" 
				style="width: 200px; height: 200px;  margin: auto;">
				</td>
				<td colspan="2" width="300px"  height="40px"><h3>${vo.title }</h3></td>
			</tr>
			<tr>
				<td>가수</td><td height="20px">${vo.singer }</td>
			</tr>
			<tr>
				<td>작곡가</td><td height="20px">${vo.songwriter}</td>
			</tr>
			<tr>
				<td>작사가</td><td height="20px">${vo.lyricist }</td>
			</tr>
		</table>
		<table class="table table-bordered" width="600px" border="1">
		<tr>
			<td>
				${vo.lyrics }
			</td>
		</tr>
		</table>
	</div>
</div>	
</body>
</html>