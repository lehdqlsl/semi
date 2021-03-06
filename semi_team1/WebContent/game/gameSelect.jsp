<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

//로그인 체크 / 제재체크
var loginxhr = null;
function login(nick) {
	if (nick == 'null') {
		alert("먼저 로그인을 하셔야합니다.\n로그인페이지로 이동 하시겠습니까?");
		document.location.href = "index.jsp?page=login/signin.jsp";
	}
	//로그인 된 후
	loginxhr = new XMLHttpRequest();
	loginxhr.onreadystatechange = callback;
	loginxhr
			.open(
					'get',
					"/semi_team1/board/replylimitpage.jsp?writer=${sessionScope.m_nick }",
					true);
	loginxhr.send();

}
function callback() {
	if (loginxhr.readyState == 4 && loginxhr.status == 200) {
		var submitbtn = document.getElementById("submitbtn");
		var content = document.getElementById("content");
		var data = loginxhr.responseXML;
		var limitchk = data.getElementsByTagName("limitChk")[0].firstChild.nodeValue;
		var limitdate = data.getElementsByTagName("limit_date")[0].firstChild.nodeValue;
		if (limitchk == 1) {
			alert("제재처리로 댓글을 작성할 수 없습니다. [ " + limitdate + " ] 이후부터 작성가능");
			submitbtn.disabled = true;
			content.disabled = true;
		}
	}
}
// 댓글 공백 체크
function checkBlank(object) {
	var content = document.getElementById("content").value;
	var rating = document.getElementById("rating").value;
	var val = parseFloat(rating);
	if(rating == null || rating == ""){
		alert("평점을 입력하세요!");
		return;
	}else{

		if(!(val >=1 && val <=10 )){
			alert("1~10사이의 평점을 입력해주세요.");
			return;
		}
	}
	if (content == null || content == "") {
		alert("내용을 입력하세요!");
		return;
	} else {
		object.form.submit();
	}
}
//댓글 글자수 표시
function textlen() {
	var div = document.getElementById("len");
	var text = document.getElementById("content").value;
	if (text.byteLength() >= 100) {
		alert("더 이상 글을 작성할 수 없습니다.");
	} else {
		var html = text.byteLength() + "/100";
		div.innerHTML = html;
	}
}

String.prototype.byteLength = function() {
	var l = 0;

	for (var idx = 0; idx < this.length; idx++) {
		var c = escape(this.charAt(idx));

		if (c.length == 1)
			l++;
		else if (c.indexOf("%u") != -1)
			l += 3;
		else if (c.indexOf("%") != -1)
			l += c.length / 3;
	}

	return l;
};


//댓글 삭제 체크
var xhr1 = null;
var gNum = 0;
function nickCheck(gr_num, g_num, m_nick, sessionNick) {
	xhr1 = new XMLHttpRequest();
	xhr1.onreadystatechange = callback1;
	xhr1
			.open('get', "/semi_team1/game/review/update?gr_num=" + gr_num
					+ "&g_num=" + g_num + "&m_nick=" + m_nick + "&sessionNick="
					+ sessionNick, true);
	if (m_nick != sessionNick) {
		alert("댓글 삭제는 작성자만 할 수 있습니다.");
		return;
	} else {
		if (confirm("댓글을 삭제하시겠습니까?")) {
			gNum = g_num;
			xhr1.send();
		}
	}
}
function callback1() {
	if (xhr1.readyState == 4 && xhr1.status == 200) {
		var data = xhr1.responseXML;
		var flag = data.getElementsByTagName("flag")[0].firstChild.nodeValue;
		if (flag == 0) {
			alert("댓글이 삭제되었습니다.");
			location.href = "/semi_team1/gameinfo?g_num=" + gNum;
		}
	}
}
</script>
</head>
<body>
	<c:if test="${!empty requestScope.error }">
		<script type="text/javascript">
			alert('${requestScope.error}');
		</script>
	</c:if>

	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div
			style="margin: auto; width: 800px; word-break: break-all; word-wrap: break-word;">
			<table class="table table-bordered" width="600px" border="1">
				<tr>
					<td rowspan="6" width="210px" height="300px"><img
						src="/semi_team1/rs/img/gameImg/${vo.saveImg }"
						style="width: 210px; height: 300px; margin: auto;"></td>
					<td colspan="2" width="300px" height="50px"><h3>${vo.g_name }</h3></td>
				</tr>
				<tr>
					<td>장르</td>
					<td height="15px">${vo.g_jenre }</td>
				</tr>
				<tr>
					<td>플랫폼</td>
					<td height="15px">${vo.flatform}</td>
				</tr>
				<tr>
					<td>회사</td>
					<td height="15px">${vo.company }</td>
				</tr>
				<tr>
					<td>출시일</td>
					<td height="15px">${vo.l_date }</td>
				</tr>
				<tr>
					<td>평점</td>
					<td height="15px">${requestScope.cntAvg}</td>
				</tr>
			</table>
		</div>
	</div>

	<br>
	<!-- 댓글 -->
	<div style="margin: auto; width: 1000px">
		<div class="col-xs-2 col-sm-4">
			<span style="font-size: 14px; color: #333; font-weight: bold">댓글</span>
			<span style="font-size: 11px; color: #999;">총 <strong
				style="font-weight: bold; color: #5795dd;">${requestScope.cntTot}</strong>개
			</span>
		</div>

		<%-- 
		<div class="col-xs-2 col-sm-4">
			<span style="font-size: 14px; color: #333; font-weight: bold">평점</span>
			<span style="font-size: 11px; color: #999;">총 <strong
				style="font-weight: bold; color: #5795dd;">${requestScope.cntAvg}</strong>
			</span>
		</div>
		--%>

		<br> <br>
		<table class="table table-striped" style="padding: 50px;">
			<c:forEach var="vo" items="${requestScope.list }" varStatus="status">
				<tr>
					<td>${vo.score }/ 10.0</td>
					<td id="user"><a href="">${vo.m_nick }</a></td>
					<td width="600"
						style="word-break: break-all; word-wrap: break-word;">${vo.comments}</td>
					<td>${vo.r_date }</td>


					<td><button type="button" class="btn btn-xs btn-success"
							onclick="replyup('${vo.gr_num}','${status.index}')">
							<span class="glyphicon glyphicon-thumbs-up"></span> <strong
								id="re_up${status.index}">${vo.up }</strong>
						</button></td>



					<td><c:choose>
							<c:when test="${vo.m_nick==sessionScope.m_nick }">
								<div id="delete">
									<button type="button" class="btn btn-xs btn-warning"
										onclick="nickCheck('${vo.gr_num }','${vo.g_num}','${vo.m_nick}','${sessionScope.m_nick}')">삭제</button>
								</div>
							</c:when>
							<c:otherwise>
								<div id="report">
									<button type="button" class="btn btn-xs btn-danger"
										onclick="replyreport(${vo.gr_num},'${requestScope.mvo.m_nick }',${vo.g_num })">신고</button>
								</div>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>

		<!-- 페이징 -->

		<!--  이전 -->
		<c:choose>
			<c:when test="${startPage>10 }">
				<a
					href="/semi_team1/gameinfo?pageNum=${startPage-1 }&g_num=${vo.g_num }"><input
					class="btn btn-xs btn-primary" type="submit" value="◁"></a>
			</c:when>
			<c:otherwise>
				<input class="btn btn-xs btn-primary" type="submit" value="◁">
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${i==pageNum }">
					<a style="text-decoration: none"
						href="/semi_team1/gameinfo?pageNum=${i }&g_num=${vo.g_num }">
						<input class="btn btn-xs btn-link" type="submit" value="${i}">
					</a>
				</c:when>
				<c:otherwise>
					<a style="text-decoration: none"
						href="/semi_team1/gameinfo?pageNum=${i }&g_num=${vo.g_num }">
						<input class="btn btn-xs btn-link" type="submit" value="${i}">
					</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- 다음 -->
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a
					href="/semi_team1/gameinfo?pageNum=${endPage+1 }&g_num=${vo.g_num }"><input
					class="btn btn-xs btn-primary" type="submit" value="▷"></a>
			</c:when>
			<c:otherwise>
				<input class="btn btn-xs btn-primary" type="submit" value="▷">
			</c:otherwise>
		</c:choose>

		<!-- 댓글쓰기 -->

		<br> <br>

		<form method="post" action="/semi_team1/game/review/insert">
			<input type="hidden" name="m_nick" value="${sessionScope.m_nick }">
			<div id="input" style="margin: auto; width: 1000px; height: 130px;">
				<div style="margin-bottom: 10px;">
					평점 <input type="text" name="rating" id="rating" size=1> /
					10
				</div>
				<input type="hidden" name="g_num" value="${vo.g_num }">
				<div>
					<textarea rows="4" cols="80" name="content" id="content"
						placeholder="댓글 작성 시 타인에 대한 배려와 책임을 담아주세요." style="width: 90%"
						onclick="login('<%=session.getAttribute("m_nick")%>')"
						onkeyup="textlen()"></textarea>
					<button type="button" id="submitbtn" class="btn btn-lg btn-success"
						style="float: right; height: 88px; width: 10%"
						onclick="checkBlank(this)">등록</button>
				</div>
			</div>
			<div id="len">0/100 byte</div>
		</form>
	</div>
	</div>
	</div>
</body>
</html>