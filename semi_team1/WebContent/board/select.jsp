<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function login(nick){
		
		if(nick == 'null'){
			alert("먼저 로그인을 하셔야합니다.\n로그인페이지로 이동 하시겠습니까?");
			document.location.href="index.jsp?page=login/signin.jsp";
		}
	}
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
							<td style="width: 300px;">${vo.content }</td>
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

				<br> <br>
				<form method="post" action="/semi_team1/reply/insert">
					<div id="input" style="margin: auto; width: 1000px; height: 100px;">
						<input type="hidden" name="b_num" value="${requestScope.b_num }">
						<div>
							<textarea rows="4" cols="80" name="content"
								placeholder="댓글 작성 시 타인에 대한 배려와 책임을 담아주세요." style="width: 90%"
								onclick="login('<%=session.getAttribute("m_nick")%>')"></textarea>
							<button type="submit" class="btn btn-lg btn-success"
								style="float: right; height: 88px; width: 10%">등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>