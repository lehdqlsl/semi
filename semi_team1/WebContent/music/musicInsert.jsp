<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
		<div style="width: 500px; height: 500px; margin: auto;">
			<h1>음악 정보 등록</h1>
			<form action="/semi_team1/music/insert" method="post"
				class="navbar-form" name="f">
				<label>제목</label><br> 
				<input type="text"  class="form-control" name="title"><br>
				<label>가수</label><br> 
				<input type="text"  class="form-control" name="singer"> <br>
				<label>가사</label><br> 
				<input type="text"class="form-control" name="lyrics"><br>
				<label>작곡가</label><br>
				<input type="text" class="form-control" name="songwriter"><br> 
				<label>작사가</label><br>
				<input type="text" class="form-control" name="lyricist"><br> 
				<label>이미지등록</label><br>
				<input type="text" name="music_img" id="music_img" readonly="readonly" class="form-control"> 
				<input type="button" value="음악이미지" onclick=""><br>
				<input type="submit" value="등록" >
			</form>
		</div>
	</div>
</body>
</html>