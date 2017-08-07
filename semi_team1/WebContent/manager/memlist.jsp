<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function inputLimitDays(nick) {
		var days;
		var m_nick = nick;
		console.log(m_nick);
		days = prompt("제재 일수를 입력하세요");
		if (days >= -1 && days <= 100) {
			var flag = confirm("제재하시겠습니까?");
			if (flag) {
				location.href = "/semi_team1/limit/day?days=" + days
						+ "&m_nick=" + m_nick;
			}
		} else {
			alert("1부터 100사이의 숫자만 입력하세요");
			//return;
		}
	}
</script>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
			<table class="table table-bordered">
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>닉네임</td>
					<td>이메일</td>
					<td>회원등급</td>
					<td>경험치</td>
					<td>가입날짜</td>
					<td>상태</td>
					<td>제재</td>
				</tr>
				<c:forEach var="vo" items="${requestScope.list }">
					<tr>
						<td>${vo.num }</td>
						<td>${vo.id }</td>
						<td>${vo.m_nick }</td>
						<td>${vo.m_mail }</td>
						<td>${vo.grade }</td>
						<td>${vo.exp }</td>
						<td>${vo.reg_date }</td>
						<c:choose>
							<c:when test="${vo.stop==0 }">
								<td>정상</td>
							</c:when>
							<c:otherwise>
								<td>정지</td>
							</c:otherwise>
						</c:choose>
						<td><button type="button" class="btn btn-xs btn-success"
								onclick="inputLimitDays('${vo.m_nick }')">정지</button></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<!--  페이징 -->
			<div id="paging">
				<!-- 이전 -->
				<c:choose>
					<c:when test="${startPage>5 }">
						<a href="/semi_team1/member/list?pageNum=${startPage-1 }"><input class="btn btn-xs btn-primary" type="submit" value="◁"></a>
					</c:when>
					<c:otherwise>
			<input class="btn btn-xs btn-primary" type="submit" value="◁">
		</c:otherwise>
				</c:choose>

				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a style="text-decoration: none" href="/semi_team1/member/list?pageNum=${i }">
								<input class="btn btn-xs btn-link" type="submit" value="${i}"></a>
						</c:when>
						<c:otherwise>
							<a style="text-decoration: none" href="/semi_team1/member/list?pageNum=${i }"><input class="btn btn-xs btn-link" type="submit" value="${i}"></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!--  다음 -->
				<c:choose>
					<c:when test="${endPage<pageCount }">
						<a href="/board/member/list?pageNum=${endPage+1 }"><input class="btn btn-xs btn-primary" type="submit" value="▷"></a>
					</c:when>
					<c:otherwise>
			<input class="btn btn-xs btn-primary" type="submit" value="▷">
		</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>