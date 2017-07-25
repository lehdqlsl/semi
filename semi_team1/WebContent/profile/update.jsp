<%@page import="com.team1.vo.ProfileVo"%>
<%@page import="com.team1.vo.JoinVo"%>
<%@page import="com.team1.dao.ProfileDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/semi_team1/rs/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/semi_team1/rs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="signin.css" rel="stylesheet">
<script type="text/javascript" >



var xhr1 = null;
function nickcheck() {
	var m_nick = document.getElementById("m_nick").value;
	if (!(m_nick.length >= 1 && m_nick.length <= 10)) {
		alert("닉네임은 1~10자리로 입력하세요");
		return;
	}
	xhr1 = new XMLHttpRequest();
	xhr1.onreadystatechange = callnick;
	xhr1.open('get', '/semi_team1/profile/nickcheck.jsp?m_nick=' + m_nick, true);
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
	for(var i=0;i<m_mail.length;i++){
		var ch=m_mail.charAt(i);
		if(!((ch>='A'&&ch<='Z')||(ch>='a'&&ch<='z')||(ch>=0&&ch<=9)||ch=='@'||ch=='.')){
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
	xhr2.open('get', '/semi_team1/profile/emailcheck.jsp?m_mail=' + m_mail, true);
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
	for(var i=0;i<u_pw.length;i++){
		var ch=u_pw.charAt(i);
		if(!((ch>='A'&&ch<='Z')||(ch>='a'&&ch<='z')||(ch>=0&&ch<=9)||ch=='!'||ch=='@')){
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
	var m_mail=document.getElementById("m_mail").value;
	var m_nick=document.getElementById("m_nick").value;
	
	if((m_mail==null)||(m_nick==null)||(u_pw==null)||(checkpw==null)){
		alert("미입력된 부분이 있습니다");
		return false;
	}
	var nickcheckbtn = document.getElementById("nickcheckbtn");
	var emailcheckbtn = document.getElementById("emailcheckbtn");
	console.log(nickcheckbtn.disabled);
	console.log(emailcheckbtn.disabled);
	if ((nickcheckbtn.disabled != true || emailcheckbtn.disabled != true)) {
		alert("중복확인을 해주세요");
		return false;
	}
	alert("수정이 완료되었습니다!!");
}

</script>
</head>
<body>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
<%
	request.setCharacterEncoding("utf-8");
	int num=Integer.parseInt(request.getParameter("num"));
	ProfileDao dao=new ProfileDao();
	ProfileVo vo=dao.select(num);
%>
	<table class="table table-bordered">
	<tr>
	<td rowspan="4" width="230px"> 이미지</td>
	<td width="400px" colspan="2">*삭제시 기본이미지 적용</td>
	<td>님</td>
	</tr>
	
	<tr>
	<td colspan="2"> *업로드시 즉시적용 될수도</td>
	<td>회원번호:<%=vo.getNum() %></td>
	</tr>
	
	<tr>
	<td colspan="2"> (150px * 150px, jpg, jgep, png, gif, 최대용량 2MB)</td>
	<td>경험치:<%=vo.getExp() %></td>
	</tr>
	
	<tr>
	<td width="150px"><input type="file" name="file1" > </td>
	<td><input type="button" name="imgupload" value="적용"> </td>
	<td>가입일:<%=vo.getReg_date() %></td>
	</tr>
	</table>
	
	<%-- <div class="container">--%>
	<div style="width:500px;height:500px;margin:auto;">
	<form action="/semi_team1/profileselect" method="post" class="navbar-form">
		
		<input type="hidden" name="Snum" value="<%=num %>">
		
		<label>닉네임</label><br>
		<input type="text" placeholder="닉네임" name="m_nick" id="m_nick" class="form-control" value="<%=vo.getM_nick()%>"> 
		<input type="button" id="nickcheckbtn"	value="중복확인" onclick="nickcheck()" class="btn btn-sm btn-success"><br> 
		
		<label>비밀번호</label><br>	
		<input type="password" placeholder="비밀번호" name="u_pw" id="u_pw"	class="form-control"><br> 
		
		<label>비밀번호 확인</label><br>
		<input type="password" placeholder="비밀번호 확인" name="checkpw" id="checkpw" class="form-control"><br> 
		
		<label>이메일</label><br>
		<input type="text" placeholder="이메일" name="m_mail" id="m_mail" class="form-control" value="<%=vo.getM_mail()%>"> 
		<input type="button" id="emailcheckbtn"	value="중복확인" onclick="return emailcheck()" class="btn btn-sm btn-success"><br><br> 
		<input type="submit" value="수정" onclick="return validate()" class="btn btn-sm btn-success">
	</form>
	</div>
	<%-- </div>--%>
	
	
	
	
	
	
</div>
</body>
</html>