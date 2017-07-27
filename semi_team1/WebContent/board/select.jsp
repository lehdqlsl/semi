<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xhr = null;
	// 로그인 체크 / 제재체크
	function login(nick) {
		if (nick == 'null') {
			alert("먼저 로그인을 하셔야합니다.\n로그인페이지로 이동 하시겠습니까?");
			document.location.href = "index.jsp?page=login/signin.jsp";
		}
		//로그인 된 후	
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = callback;
		xhr.open(
						'get',
						"/semi_team1/board/replylimitpage.jsp?writer=${sessionScope.m_nick }",
						true);
		xhr.send();
		
	}
	function callback() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var submitbtn = document.getElementById("submitbtn");
			var content = document.getElementById("content");
			var data = xhr.responseXML;
			var limitchk = data.getElementsByTagName("limitChk")[0].firstChild.nodeValue;
			var limitdate = data.getElementsByTagName("limit_date")[0].firstChild.nodeValue;
			console.log("callback" + limitchk);
			if (limitchk == 1) {
				alert("제재처리로 댓글을 작성할 수 없습니다. [ " + limitdate + " ] 이후부터 작성가능");
				submitbtn.disabled = true;
				content.disabled = true;
			}
		}
	}
	// 댓글 삭제 체크
	var xhr1 = null;
	var bNum=0;
	function nickCheck(r_num,b_num,nick,sessionNick) {
		xhr1 = new XMLHttpRequest();
		xhr1.onreadystatechange = callback1;
		xhr1
				.open(
						'get',
						"/semi_team1/reply/delete?r_num=" + r_num + "&b_num=" + b_num + "&nick=" + nick + "&sessionNick=" + sessionNick,
						true);
		if(!nick.equals(sessionNick)){
			alert("댓글 삭제는 작성자만 할 수 있습니다.");
			return;
		}else{
			bNum=b_num;
			xhr1.send();	
		}

	}
	function callback1() {
		if (xhr1.readyState == 4 && xhr1.status == 200) {
			var data = xhr1.responseXML;
			var flag = data.getElementsByTagName("flag")[0].firstChild.nodeValue;
			if(flag==0){
				alert("댓글이 삭제되었습니다.");
				location.href="/semi_team1/select?num="+bNum;
			}
			<%--
			console.log(flag);
			if (flag == 1) {
				alert("댓글 삭제는 작성자만 할 수 있습니다.");
			}else{
				if(confirm("댓글을 삭제하시겠습니까?")){
					alert("댓글이 삭제되었습니다.");
					location.href="/semi_team1/select?num="+bNum;
				}
			}
		}--%>
	}
	// 댓글 글자수 표시
	function textlen() {
		var div = document.getElementById("len");
		var text = document.getElementById("content").value;
		if (text.byteLength() >= 500) {
			alert("더 이상 글을 작성할 수 없습니다.");
		} else {
			var html = text.byteLength() + "/500";
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
</script>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>${requestScope.vo.title_name}</th>
					</tr>
				</thead>
				<tr>
					<td>사용자정보표시란</td>
				</tr>
				<tr>
					<td>${requestScope.vo.content }</td>
				</tr>
			</table>

			<table align="right">
				<tr>
					<td>
						<c:if test="${(sessionScope.m_nick eq requestScope.vo.writer) || (sessionScope.m_nick eq 'admin')}">
							<input class="btn btn-success" type="button" value="수정" onclick="location.href = 'index.jsp?page=board/update.jsp?num=${requestScope.vo.num}';">
							<input class="btn btn-success" type="button" value="삭제" onclick=delete()> 
						</c:if>
						<input class="btn btn-success" type="button" value="목록" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>


			<br>

			<!-- 댓글 -->
			<div style="margin: auto; width: 1000px">
				<div class="col-xs-2 col-sm-4">
					<span style="font-size: 14px; color: #333; font-weight: bold">댓글</span>
					<span style="font-size: 11px; color: #999;">총 <strong
						style="font-weight: bold; color: #5795dd;">${requestScope.cntTot}</strong>개
					</span>
				</div>
				<br> <br>
				<table class="table table-striped" style="padding: 50px;">
					<c:forEach var="vo" items="${requestScope.list }">
						<tr>
							<!--  <td>${vo.r_num }</td>-->
							<td id="user">
								<div>
									<a href="회원정보조회페이지">${vo.nick }</a>
								</div>
							</td>
							<td width="600"
								style="word-break: break-all; word-wrap: break-word;">${vo.content }</td>
							<td>${vo.reg_date }</td>

							<!--  삭제/추천 버튼 한가지 폼으로 코딩?? -->
							<form method="post" action="/semi_team1/reply.recomm">
								<input type="hidden" name="r_num" value=${vo.r_num }> <input
									type="hidden" name="b_num" value="${param.b_num}">
								<td><div id="recommend">
										<button type="submit" class="btn btn-xs btn-success">
											추천 <strong>${vo.up }</strong>
										</button>
									</div></td>
							</form>
							<form method="post" action="/semi_team1/reply.delete">
								<input type="hidden" name="r_num" value=${vo.r_num }> <input
									type="hidden" name="b_num" value="${param.b_num}">
								<td><div id="delete">
										<button type="submit" class="btn btn-xs btn-success">삭제</button>
									</div></td>
							</form>

						</tr>
					</c:forEach>
				</table>

				<!-- 페이징 -->

				<!--  이전 -->
				<c:choose>
					<c:when test="${startPage>10 }">
						<a
							href="/semi_team1/select?pageNum=${startPage-1 }&num=${requestScope.b_num }"></a>
					</c:when>
					<c:otherwise>
		[◁]	
	</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a
								href="/semi_team1/select?pageNum=${i }&num=${requestScope.b_num }"><span
								style="color: green">[${i }]</span></a>
						</c:when>
						<c:otherwise>
							<a
								href="/semi_team1/select?pageNum=${i }&num=${requestScope.b_num }"><span
								style="color: #aaa">[${i }]</span></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 다음 -->
				<c:choose>
					<c:when test="${endPage<pageCount }">
						<a
							href="/semi_team1/select?pageNum=${endPage+1 }&num=${requestScope.b_num }">다음</a>
					</c:when>
					<c:otherwise>
		[▷]
	</c:otherwise>
				</c:choose>

				<!-- 글쓰기 -->

				<br> <br>
				<form method="post" action="/semi_team1/reply/insert">
					<div id="input" style="margin: auto; width: 1000px; height: 100px;">
						<input type="hidden" name="b_num" value="${requestScope.b_num }">
						<div>
							<textarea rows="4" cols="80" name="content" id="content"
								placeholder="댓글 작성 시 타인에 대한 배려와 책임을 담아주세요." style="width: 90%"
								onclick="login('<%=session.getAttribute("m_nick")%>')"
								onkeyup="textlen()"></textarea>
							<button type="submit" class="btn btn-lg btn-success"
								style="float: right; height: 88px; width: 10%">등록</button>
						</div>
					</div>
					<div id="len">0/500 byte</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>