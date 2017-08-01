<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//댓글 삭제 체크
var delxhr = null;
var bNum = 0;
function delCheck(r_num, b_num, nick, sessionNick) {
	delxhr = new XMLHttpRequest();
	delxhr.onreadystatechange = callback1;
	delxhr.open('get', "/semi_team1/reply/delete?r_num=" + r_num
					+ "&b_num=" + b_num + "&nick=" + nick + "&sessionNick="
					+ sessionNick, true);
	if (confirm("댓글을 삭제하시겠습니까?")) {
		bNum = b_num;
		delxhr.send();
	}
}
function callback1() {
	if (delxhr.readyState == 4 && delxhr.status == 200) {
		var data = delxhr.responseXML;
		var flag = data.getElementsByTagName("flag")[0].firstChild.nodeValue;
		if (flag == 0) {
			alert("댓글이 삭제되었습니다.");
			location.reload();  
		}
	}
}
</script>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div style="margin: auto; width: 1000px; height: 1300px;">
			<table class="table table-bordered">
				<tr>
					<td>게시글번호</td>
					<td>댓글번호</td>
					<td>닉네임</td>
					<td>글내용</td>
					<td>삭제</td>
				</tr>
				<c:forEach var="vo" items="${requestScope.list }">
				
					<tr>
						<td><a href="/semi_team1/select?num=${vo.b_num }">${vo.b_num }</a></td>
						<td>${vo.r_num }</td>
						<td>
						<a href="/semi_team1/select/limit/mem?writer=${vo.nick }">${vo.nick }</a></td>
						<td>${vo.content }</td>
						<td><button type="button" class="btn btn-xs btn-success"
								onclick="delCheck(${vo.r_num},${vo.b_num },'${vo.nick }','${ sessionScope.m_nick }')">삭제</button></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<!--  페이징 -->
			<div id="paging2">
				<!-- 이전 -->
				<c:choose>
					<c:when test="${startPage>10 }">
						<a href="/semi_team1/report/reply?pageNum=${startPage-1 }"><input
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
								href="/semi_team1/report/reply?pageNum=${i }"><input
								class="btn btn-xs btn-link" type="submit" value="${i}"></a>
						</c:when>
						<c:otherwise>
							<a style="text-decoration: none"
								href="/semi_team1/report/reply?pageNum=${i }"><input
								class="btn btn-xs btn-link" type="submit" value="${i}"></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!--  다음 -->
				<c:choose>
					<c:when test="${endPage<pageCount }">
						<a href="/semi_team1/report/reply?pageNum=${endPage+1 }"><input
							class="btn btn-xs btn-primary" type="submit" value="▷"></a>
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