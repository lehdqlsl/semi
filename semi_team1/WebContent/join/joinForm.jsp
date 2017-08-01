<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xhr = null;
	function idcheck() {
		var id = document.getElementById("id").value;
		if (!(id.length > 5 && id.length < 16)) {
			alert("아이디는 6~15자리로 입력하세요");
			return;
		}
		for (var i = 0; i < id.length; i++) {
			var ch = id.charAt(i);
			if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= 0 && ch <= 9))) {
				alert("아이디는 영문자나 숫자로만 입력하세요!!");
				return;
			}
		}
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = callback;
		xhr.open('get', '/semi_team1/join/idcheck.jsp?id=' + id, true);
		xhr.send();
	}
	function callback() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var idcheckbtn = document.getElementById("idcheckbtn");
			var id = document.getElementById("id");
			var data = xhr.responseText;
			var json = eval('(' + data + ')');

			if (json.check == true) {
				alert("이미 사용 중인 아이디입니다");
			} else {
				alert("사용할 수 있는 아이디입니다");
				idcheckbtn.disabled = true;
				f.id.readOnly = true;
			}
		}
	}

	var xhr1 = null;
	function nickcheck() {
		var m_nick = document.getElementById("m_nick").value;
		if (!(m_nick.length >= 1 && m_nick.length <= 10)) {
			alert("닉네임은 1~10자리로 입력하세요");
			return;
		}
		xhr1 = new XMLHttpRequest();
		xhr1.onreadystatechange = callnick;
		xhr1.open('get', '/semi_team1/join/nickcheck.jsp?m_nick=' + m_nick,
				true);
		xhr1.send();
	}
	function callnick() {
		if (xhr1.readyState == 4 && xhr1.status == 200) {
			var nickcheckbtn = document.getElementById("nickcheckbtn");
			var data = xhr1.responseText;
			var json = eval('(' + data + ')');
			if (json.check == true) {
				alert("이미 사용 중인 닉네임입니다");
			} else {
				alert("사용할 수 있는 닉네임입니다");
				nickcheckbtn.disabled = true;
				f.m_nick.readOnly = true;

			}
		}
	}
	var xhr2 = null;
	function emailcheck() {
		var m_mail = document.getElementById("m_mail").value;
		var e1 = 0;
		var e2 = 0;
		for (var i = 0; i < m_mail.length; i++) {
			var ch = m_mail.charAt(i);
			if (ch == '@') {
				e1 += 1;
			}
			if (ch == '.') {
				e2 += 1;
			}
		}
		for (var i = 0; i < m_mail.length; i++) {
			var ch = m_mail.charAt(i);
			if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
					|| (ch >= 0 && ch <= 9) || ch == '@' || ch == '.')) {
				alert("올바른 이메일 주소가 아닙니다");
				return;
			}
		}
		if (!((e1 == 1) && (e2 == 1))) {
			alert("이메일 형식이 잘못되었습니다");
			return false;
		}
		xhr2 = new XMLHttpRequest();
		xhr2.onreadystatechange = callemail;
		xhr2.open('get', '/semi_team1/join/emailcheck.jsp?m_mail=' + m_mail,
				true);
		xhr2.send();
	}
	function callemail() {
		if (xhr2.readyState == 4 && xhr2.status == 200) {
			var nickcheckbtn = document.getElementById("emailcheckbtn");
			var data = xhr2.responseText;
			var json = eval('(' + data + ')');
			if (json.check == true) {
				alert("이미 사용 중인 이메일입니다");
			} else {
				alert("사용할 수 있는 이메일입니다");
				f.m_mail.readOnly = true;
				emailcheckbtn.disabled = true;
			}
		}
	}
	function validate() {
		//////////비밀번호///////////
		//////////////////비밀번호 조건/////////////////////////////////////
		var u_pw = document.getElementById("u_pw").value;
		if (!(u_pw.length >= 4 && u_pw.length <= 20)) {
			alert("비밀번호는 4자에서 20자 사이이어야 합니다");
			return false;
		}
		for (var i = 0; i < u_pw.length; i++) {
			var ch = u_pw.charAt(i);
			if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
					|| (ch >= 0 && ch <= 9) || ch == '!' || ch == '@')) {
				alert("비밀번호는 영문자, 숫자, !, @로만 입력하세요!!");
				return;
			}
		}
		//////////////////////비밀번호 확인 조건///////////////////////////////////
		var checkpw = document.getElementById("checkpw").value;
		if (u_pw != checkpw) {
			alert("비밀번호를 확인하세요");
			return false;
		}

		////////////////////빈 칸이 입력되면////////////////////////////////////////
		var m_mail = document.getElementById("m_mail").value;
		var id = document.getElementById("id").value;
		var m_nick = document.getElementById("m_nick").value;
		var m_img = document.getElementById("m_img").value;
		if ((m_mail == null) || (id == null) || (m_nick == null)
				|| (u_pw == null) || (checkpw == null) || (m_img == "") ) {
			alert("미입력된 부분이 있습니다");
			return false;
		}
		var idcheckbtn = document.getElementById("idcheckbtn");
		var nickcheckbtn = document.getElementById("nickcheckbtn");
		var emailcheckbtn = document.getElementById("emailcheckbtn");

		if ((idcheckbtn.disabled != true || nickcheckbtn.disabled != true || emailcheckbtn.disabled != true)) {
			alert("중복확인을 해주세요");
			return false;
		}
		alert("회원가입이 완료되었습니다!!");
		return true;
	}

	function selectimg() {
		window.open("/semi_team1/join/popup.html", "_blank",
				"width=900,height=520");
	}
</script>
</head>
<body>
	<div class="container">
		<div style="width: 500px; height: 500px; margin: auto;">
			<h1>회원 가입 하기</h1>
			<form action="/semi_team1/join/insert" method="post"
				class="navbar-form" name="f">
				<label>아이디</label><br> <input type="text" placeholder="아이디"
					name="id" id="id" class="form-control"> <input
					type="button" id="idcheckbtn" value="중복확인" onclick="idcheck()"><br>
				<label>닉네임</label><br> <input type="text" placeholder="닉네임"
					name="m_nick" id="m_nick" class="form-control"> <input
					type="button" id="nickcheckbtn" value="중복확인" onclick="nickcheck()"><br>
				<label>비밀번호</label><br> <input type="password"
					placeholder="비밀번호" name="u_pw" id="u_pw" class="form-control"><br>
				<label>비밀번호 확인</label><br> <input type="password"
					placeholder="비밀번호 확인" name="checkpw" id="checkpw"
					class="form-control"><br> <label>이메일</label><br>
				<input type="text" placeholder="이메일" name="m_mail" id="m_mail"
					class="form-control"> <input type="button"
					id="emailcheckbtn" value="중복확인" onclick="return emailcheck()"><br>
				<label>기본이미지설정</label><br>
				<input type="text" name="m_img" id="m_img" readonly="readonly"
					class="form-control"> <input type="button" value="기본이미지"
					onclick="selectimg()"><br> <input type="submit"
					value="가입" onclick="return validate()">
			</form>
		</div>
	</div>
</body>
</html>