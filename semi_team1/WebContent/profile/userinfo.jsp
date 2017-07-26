<%@page import="com.team1.vo.ProfileVo"%>
<%@page import="com.team1.dao.UserInfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
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
			a.m_nick.readOnly=true;
		}
	}
}

function validate() {
	var m_nick=document.getElementById("m_nick").value;
	if(m_nick==null){
		alert("미입력된 부분이 있습니다");
		return false;
	}
	var nickcheckbtn = document.getElementById("nickcheckbtn");
	if (nickcheckbtn.disabled != true) {
		alert("중복확인을 해주세요");
		return false;
	}
	alert("수정이 완료되었습니다!!");
}	
	
</script>


</head>
<body>

	<%-- 왼쪽 사이드바 --%>
	<div class="col-sm-3 col-md-2 sidebar">
		<%
			request.setCharacterEncoding("utf-8");
			int num = Integer.parseInt(request.getParameter("num"));
			UserInfoDao dao = new UserInfoDao();
			ProfileVo vo = dao.select(num);
		%>
		<table class="table table-bordered" width="300px">
			<tr>
				<td colspan="2">사용자정보</td>
			<tr>
				<td rowspan="5" width="100px"><img
					src="/semi_team1/upload/<%=vo.getM_savefilename()%>"
					style="height: 150px; width: 150px; margin: auto;"></td>
				
				<td><%=vo.getM_nick()%>님</td>
			</tr>

			<tr>
				<td>회원번호:<%=vo.getNum()%></td>
			</tr>
			
			<tr>
				<td>등급:언랭</td>
			</tr>	
			
			<tr>
				<td>경험치:<%=vo.getExp()%></td>
			</tr>

			<tr>
				<td>가입일:<br><%=vo.getReg_date()%></td>
			</tr>
		</table>

		<table class="table table-bordered">
			<tr>
				<td colspan="3">활동내역</td>
			</tr>

			<tr>
				<td>작성글</td>
				<td>작성댓글</td>
				<td>추천수</td>
			</tr>

			<tr>
				<td>xx개</td>
				<td>xx개</td>
				<td>xx개</td>
			</tr>
		</table>
		<ul class="nav nav-sidebar">
		<li><a href="">프로필 수정</a></li>
		<li><a href="">개인정보 수정</a></li>
		<li><a href="">쪽지함</a></li>
		</ul>
	</div>
	
	<%-- 오른쪽 메인 --%>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<table class="table table-bordered">
	<tr>
	<td rowspan="4" width="230px"> 이미지</td>
	<td width="400px" colspan="2">*삭제시 기본이미지 적용</td>
	
	</tr>
	
	<tr>
	<td colspan="2"> *업로드시 즉시적용 될수도</td>
	
	</tr>
	
	<tr>
	<td colspan="2"> (150px * 150px, jpg, jgep, png, gif, 최대용량 2MB)</td>
	
	</tr>
	
	<tr>
	<td width="150px"><input type="file" name="file1" > </td>
	<td><input type="button" name="imgupload" value="적용"> </td>
	
	</tr>
	</table>
	</div>
	
	<div style="width:1000px;height:500px;padding-left: 350px;">
	<form action="/semi_team1/nickupdate" method="post" class="navbar-form" name="a">
		<input type="hidden" name="num" value="<%=num %>">
		<label>닉네임</label><br>
		<input type="text" placeholder="닉네임" name="m_nick" id="m_nick" class="form-control" value="${sessionScope.m_nick }"> 
		<input type="button" id="nickcheckbtn"	value="중복확인" onclick="nickcheck()" class="btn btn-sm btn-success"><br><br> 
		<p>닉네임 설정 안내</p>
		<textarea cols="100" rows="5" name="notice" readonly="readonly" style="resize: none;">
		16자까지, 닉네임 앞뒤로 공백 불가, 단어 사이 공백 1회 허용
		공백 문자 & 보안상 문제되는 특수문자는 발견시 제외처리
		어법에 맞지 않는 한글 사용 금지
		불량 닉네임(욕설, 불건전 닉네임)은 임의 삭제 혹은 계정 징계 조치
		</textarea><br><br>
		&nbsp;<input type="submit" value="수정완료" onclick="return validate()" class="btn btn-lg btn-success">
	</form>
	</div>
	
</body>
</html>