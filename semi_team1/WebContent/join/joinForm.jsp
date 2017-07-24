<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xhr=null;
	function idcheck(){
		var id=document.getElementById("id").value;
		if(!(id.length>5 && id.length<16)){
			alert("아이디는 6~15자리로 입력하세요");
			return;
		}
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		xhr.open("get","idcheck.jsp?id"+id, true);
		xhr.send();
	}
	
</script>
</head>
<body>
	<div class="container">
		<h1>회원 가입 하기</h1>
		<form action="/semi_team1/join.do?cmd=insert" method="post"
			class="navbar-form">
			<label>아이디</label><br> <input type="text" placeholder="아이디" name="id"
				id="id" class="form-control"><input type="button"
				value="중복확인" onclick="idcheck()"><br> <label>닉네임</label><br> <input
				type="text" placeholder="닉네임" name="m_nick" id="m_nick"
				class="form-control"><input type="button" value="중복확인"><br>
			<label>비밀번호</label><br> <input type="text" placeholder="비밀번호"
				name="u_pw" id="u_pw" class="form-control"><br> <label>비밀번호
				확인</label><br> <input type="text" placeholder="비밀번호 확인" name="checkpw"
				id="checkpw" class="form-control"><br> <label>이메일</label><br>
			<input type="text" placeholder="이메일" name="m_mail" id="m_mail"
				class="form-control"><br> <input type="submit"
				value="가입">
		</form>
	</div>
</body>
</html>