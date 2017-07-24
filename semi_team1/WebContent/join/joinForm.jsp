<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 가입 하기</h1>
<form action="../join.do?cmd=insert" method="post">
	<label>아이디</label><br><input type="text" value="아이디" name="id" id="id"><input type="button" value="중복확인"><br>
	<label>닉네임</label><br><input type="text" value="닉네임" name="m_nick" id="m_nick"><input type="button" value="중복확인"><br>
	<label>비밀번호</label><br><input type="text" value="비밀번호" name="u_pw" id="u_pw"><br>
	<label>비밀번호 확인</label><br><input type="text" value="비밀번호 확인" name="checkpw" id="checkpw"><br>
	<label>이메일</label><br><input type="text" value="이메일" name="m_mail" id="m_mail"><br>
	<input type="submit" value="가입">
</form>
</body>
</html>