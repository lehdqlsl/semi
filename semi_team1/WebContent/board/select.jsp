<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div style="margin: auto; width: 1000px">
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
					<td><input class="btn btn-success" type="button" value="수정"
						onclick="location.href = 'index.jsp?page=board/update.jsp?num=${requestScope.vo.num}';">
						<input class="btn btn-success" type="button" value="삭제"
						onclick=delete()> <input class="btn btn-success"
						type="button" value="글쓰기"
						onclick="location.href = 'index.jsp?page=board/insert.jsp';">
						<input class="btn btn-success" type="button" value="목록"
						onclick="javascript:history.back()"></td>
				</tr>
			</table>
		</div>


		<!-- 댓글 -->
		<form method="post" action="/semi_team1/reply.insert">
			<div id="input" style="margin: auto; width: 800px; height: 100px;">
				<input type="hidden" name="b_num" value="${param.b_num }">
				<textarea rows="4" cols="80" border="1" name="content"
					placeholder="댓글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
			</div>
			<div id="reply">
				<div id="tot">댓글 | 총 [ ${requestScope.cntTot} ]개</div>
				<div id="writeComm" al ign="right">
					<input type="hidden" name="b_num" value="${param.b_num }">
					<button type="submit" class="btn btn-success">댓글쓰기</button>
				</div>
			</div>
		</form>

		<table class="table table-striped">

			<c:forEach var="vo" items="${requestScope.list }">
				<tr>
					<!--  <td>${vo.r_num }</td>-->
					<td id="user">
						<div>
							<a href="회원정보조회페이지">${vo.nick }</a>
						</div>
					</td>
					<td>${vo.content }</td>
					<td>${vo.up }</td>
					<td>${vo.reg_date }</td>
					<td>${vo.b_num }</td>
					<td>${vo.report }</td>
					<!--  삭제/추천 버튼 한가지 폼으로 코딩?? -->
					<form method="post" action="/semi_team1/reply.delete">
						<input type="hidden" name="r_num" value=${vo.r_num }> <input
							type="hidden" name="b_num" value="${param.b_num}">
						<td><div id="delete">
								<button type="submit" class="btn btn-xs btn-success">삭제</button>
							</div></td>
					</form>
					<form method="post" action="/semi_team1/reply.recomm">
						<input type="hidden" name="r_num" value=${vo.r_num }> <input
							type="hidden" name="b_num" value="${param.b_num}">
						<td><div id="recommend">
								<button type="submit" class="btn btn-xs btn-success">추천</button>
							</div></td>
					</form>
				</tr>
			</c:forEach>
		</table>
		<!-- 페이징 -->
		<div>
			<!--  이전 -->
			<c:choose>
				<c:when test="${startPage>10 }">
					<a
						href="/semi_team1/reply.list?pageNum=${startPage -1 }&b_num=${param.b_num}"></a>
				</c:when>
				<c:otherwise>
		[◁]	
	</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${i==pageNum }">
						<a
							href="/semi_team1/reply.list?pageNum=${i }&b_num=${param.b_num}"><span
							style="color: green">[${i }]</span></a>
					</c:when>
					<c:otherwise>
						<a
							href="/semi_team1/reply.list?pageNum=${i }&b_num=${param.b_num}"><span
							style="color: #aaa">[${i }]</span></a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 -->
			<c:choose>
				<c:when test="${endPage<pageCount }">
					<a
						href="/semi_team1/reply.list?pageNum=${endPage+1 }&b_num=${param.b_num}">다음</a>
				</c:when>
				<c:otherwise>
		[▷]
	</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>