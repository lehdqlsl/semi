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
	xhr1.open('get', '/semi_team1/message2/nickcheck.jsp?m_nick=' + m_nick, true);
	xhr1.send();
}
function callnick() {
	if (xhr1.readyState == 4 && xhr1.status == 200) {
		var nickcheckbtn = document.getElementById("nickcheckbtn");
		var data = xhr1.responseText;
		var json = eval('(' + data + ')');
		var div=document.getElementById("result");
		if (json.check == true) {
			//a.m_nick.readOnly=true;
			nickcheckbtn.disabled=true;
			div.innerHTML="닉네임이 확인되었습니다.";
		} else {
			alert("존재하지 않는 닉네임입니다.");
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
		alert("받는사람이 있는지 확인해주세요.");
		return false;
	}
	alert("쪽지를 송신하였습니다.");
}	

function onclickreset() {
	var nickcheckbtn = document.getElementById("nickcheckbtn");
	nickcheckbtn.disabled = false;
	var div=document.getElementById("result");
	div.innerHTML="";
}
</script>

</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		int num = (int)session.getAttribute("num");
		String sender = request.getParameter("sender");
		if(sender==null){
			sender="";
		}
		String receiver = request.getParameter("receiver");
		String content = request.getParameter("content");
	%>
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div style="margin: auto; width: 1000px; height: 1300px;">
	<form action="/semi_team1/sendinsert2" method="post" id="a">
		<%-- <input type="hidden" value="<%=num%>" name="num">--%>
		<input type="hidden" value="${sessionScope.m_nick }" name="sender">
		<input type="hidden" value="<%=request.getHeader("referer") %>" name="prev">
		<table class="table table-bordered" style="width: 500px;">
			<tr>
				<td style="width: 75px;">받는사람</td>
				<td style="width: 200px;"><input type="text" name="receiver" id="m_nick" style="width: 100px;" value="<%=sender%>">
				<input type="button" id="nickcheckbtn" value="대상체크" onclick="nickcheck()" class="btn btn-sm btn-success">
				</td>
				<td style="width: 225px;">
				<div id="result" style="color: red"></div>
				</td>
			</tr>
			<tr>
				<td colspan="3"><textarea cols="75" rows="20" name="content" style="resize: none;"></textarea></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
				<input class="btn btn-success" type="submit" value="보내기" onclick="return validate()"> 
				<input class="btn btn-success" type="button" value="목록"
				onclick="location.href = '/semi_team1/sendlist2';">
				<input class="btn btn-success" type="reset" value="다시입력" onclick="onclickreset()">
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</body>
</html>