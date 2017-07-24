<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#reply{width:100%;display:inline-block;border:1px solid pink}
	#tot{float:left;}
	#writeComm{float:right;margin:5px}
</style>
<link href="/semi_team1/rs/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/semi_team1/rs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="offcanvas.css" rel="stylesheet">
<script src="/semi_team1/rs/assets/js/ie-emulation-modes-warning.js"></script>
</head>
<body>
<form method="post" action="/semi_team1/reply.insert">
<div id="input" style="margin:auto;width:500px;height:100px;">
	<input type="hidden" name="b_num" value=${vo.b_num }>
	<textarea rows="3" cols="50" border="1" name="content"></textarea>
</div>
<div id="reply">
	<div id="tot">
		댓글 | 총 [ ${requestScope.cntTot} ]개
	</div>
	<div id="writeComm" align="right">
		<button type="submit" class="btn btn-success">댓글쓰기</button>
	</div>
</div>
</form>
<table class="table table-striped">

	<c:forEach var="vo" items="${requestScope.list }"> 
		<tr>
			<!--  <td>${vo.r_num }</td>-->
			<td id="user">
				<div><a href="회원정보조회페이지">${vo.nick }</a></div></td>
			<td>${vo.content }</td>
			<td>${vo.up }</td>
			<td>${vo.reg_date }</td>
			<td>${vo.b_num }</td>
			<td>${vo.report }</td>
		</tr>
	</c:forEach>	
</table>
<!-- 페이징 -->
<div>
<!--  이전 -->
<c:choose>
	<c:when test="${startPage>10 }">
		<a href="/semi_team1/reply.list?pageNum=${startPage -1 }"></a>
	</c:when>
	<c:otherwise>
		[◁]	
	</c:otherwise>
</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="/semi_team1/reply.list?pageNum=${i }"><span style="color:green">[${i }]</span></a>
		</c:when>
		<c:otherwise>
			<a href="/semi_team1/reply.list?pageNum=${i }"><span style="color:#aaa">[${i }]</span></a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<!-- 다음 -->
<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="/semi_team1/reply.list?pageNum=${endPage+1 }">다음</a>
	</c:when>
	<c:otherwise>
		[▷]
	</c:otherwise>
</c:choose>
</div>
</body>
</html>