<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/semi_team1/movieinsert" enctype="multipart/form-data">
	<table border="1" width="500">
		<tr>
			<td>영화제목</td>
			<td><input type="text" name="m_name"></td>
		</tr>
		<tr>
			<td>장르</td>
			<td><input type="text" name="m_genre"></td>
		</tr>
		<tr>
			<td>제작국가</td>
			<td><input type="text" name="m_country"></td>
		</tr>
		<tr>
			<td>상영시간</td>
			<td><input type="text" name="m_rt"></td>
		</tr>
		<tr>
			<td>개봉일자</td>
			<td><input type="text" name="m_release"></td>
		</tr>
		<tr>
			<td>상영등급</td>
			<td><input type="text" name="m_rate"></td>
		</tr>
		<tr>	
			<td>감독</td>
			<td><input type="text" name="m_director"></td>
		</tr>
		<tr>
			<td>출연</td>
			<td><input type="text" name="m_actor"></td>
		</tr>
		<tr>
			<td>줄거리</td>
			<td><textarea cols="138" rows="20" name="story" style="resize: none;">
				${vo.story }
				</textarea></td>
		</tr>
		<tr>
			<td>동영상링크</td>
			<td><input type="text" name="link"></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><input type="file" name="file1"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="전송">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>
</body>
</html>