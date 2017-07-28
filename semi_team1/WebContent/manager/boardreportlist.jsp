<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function blindProcess(boardnum){
		var flag=confirm("블라인드 처리하시겠습니까?");
		if(flag){
			location.href = "/semi_team1/board/blind?boardnum="+boardnum;
		}
	}
</script>
</head>
<body>

	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div style="margin: auto; width: 1000px; height: 1300px;">
			<table class="table table-bordered">
				<tr>
					<td>글번호</td>
					<td>소분류</td>
					<td>글제목</td>
					<td>등록일</td>
					<td>닉네임</td>
					<td>블라인드</td>
				</tr>
				<c:forEach var="vo" items="${requestScope.list }">
					<tr>
						<input type="hidden" name="writer"  id="writer" value="${vo.writer }">
						<td>${vo.boardnum }</td>
						<td>${vo.c_title }</td>
						<td><a href="/semi_team1/select?num=${vo.boardnum }">${vo.b_title }</a></td>
						<td>${vo.regdate }</td>
						<td>${vo.writer }</td>
						<td><button type="button" class="btn btn-xs btn-success"  onclick="blindProcess(${vo.boardnum})">블라인드</button></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<!--  페이징 -->
			<div id="paging1">
				<!-- 이전 -->
				<c:choose>
					<c:when test="${startPage>10 }">
						<a href="/semi_team1/report/list?pageNum=${startPage-1 }"><input class="btn btn-xs btn-primary" type="submit" value="◁"></a>
					</c:when>
					<c:otherwise>
			<input class="btn btn-xs btn-primary" type="submit" value="◁">
		</c:otherwise>
				</c:choose>

				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a style="text-decoration: none" href="/semi_team1/report/list?pageNum=${i }"><input class="btn btn-xs btn-link" type="submit" value="${i}"></a>
						</c:when>
						<c:otherwise>
							<a style="text-decoration: none" href="/semi_team1/report/list?pageNum=${i }"><input class="btn btn-xs btn-link" type="submit" value="${i}"></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!--  다음 -->
				<c:choose>
					<c:when test="${endPage<pageCount }">
						<a href="/semi_team1/report/list?pageNum=${endPage+1 }"><input class="btn btn-xs btn-primary" type="submit" value="▷"></a>
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