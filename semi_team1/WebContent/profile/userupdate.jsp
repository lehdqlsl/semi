<%@page import="com.team1.dao.UserUpdateDao"%>
<%@page import="com.team1.vo.ProfileVo"%>
<%@page import="com.team1.dao.UserInfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/semi_team1/rs/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="dashboard.css" rel="stylesheet">
<link href="/semi_team1/rs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">   
<link href="offcanvas.css" rel="stylesheet">

<script type="text/javascript">
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
			alert("비밀번호는 영문자, 숫자, !, @로만 입력하세요");
			return;
		}
	}
	//////////////////////비밀번호 확인 조건///////////////////////////////////
	var checkpw = document.getElementById("checkpw").value;
	
	<%--
	var span=document.getElementById("span");//결과출력란
	--%>
	
	if (u_pw != checkpw) {
		alert("비밀번호를 확인하세요");
		return false;
	}	
		<%-- //결과출력란
		if(u_pw==""){
			document.getElementById("result").innerHTML="";
			return;
		} 
			
	} else if(u_pw = checkpw){
		span.innerHTML="비밀번호가 확인되었습니다.";
	} --%>
	
	////////////////////빈 칸이 입력되면////////////////////////////////////////
	var m_mail=document.getElementById("m_mail").value;
	
	if((m_mail==null)||(u_pw==null)||(checkpw==null)){
		alert("미입력된 부분이 있습니다");
		return false;
	}
	var emailcheckbtn = document.getElementById("emailcheckbtn");
	console.log(emailcheckbtn.disabled);
	if (emailcheckbtn.disabled != true) {
		alert("중복확인을 해주세요");
		return false;
	}
	alert("수정이 완료되었습니다");
}
</script>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	int num=Integer.parseInt(request.getParameter("num"));
	UserUpdateDao dao=new UserUpdateDao();
	ProfileVo vo=dao.select(num);
%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h2><span class="label label-info">개인정보 수정</span></h2><br>
	<form action="/semi_team1/userupdate" method="post" class="navbar-form">
		
		<input type="hidden" name="num" value="<%=num %>">
		
		<label>비밀번호</label><br>	
		<input type="password" placeholder="비밀번호" name="u_pw" id="u_pw"	class="form-control"><br> 
		
		<label>비밀번호 확인</label><br>
		<input type="password" placeholder="비밀번호 확인" name="checkpw" id="checkpw" class="form-control">
		
		<%-- <span id="span" style="color: red"> </span> 
		ajax01/4/insert.html 참고 idcheck.jsp -> pwdcheck(dao) --%>

		<br> 
		
		<label>이메일</label><br>
		<input type="text" placeholder="이메일" name="m_mail" id="m_mail" class="form-control" value="<%=vo.getM_mail()%>"> 
		<input type="button" id="emailcheckbtn"	value="중복확인" onclick="return emailcheck()" class="btn btn-sm btn-info"><br><br> 
		<input type="submit" value="수정" onclick="return validate()" class="btn btn-sm btn-info">
	</form>
</div>
</body>
</html>







