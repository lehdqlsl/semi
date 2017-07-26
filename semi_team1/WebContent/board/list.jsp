<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div style="margin: auto; width: 1000px; height: 1300px;">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>게시글번호</th>
						<th>구분</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>추천</th>
						<th>조회</th>
						<th>날짜</th>
					</tr>
				</thead>
				<c:forEach var="vo" items="${requestScope.list }">
					<tr>
						<!--  	
						/semi_team1/index.jsp?page=/game/gameIndex.jsp&s_page=/board/insert.jsp 
						/semi_team1/select?num=${vo.num }
				-->
						<td>${vo.num }</td>
						<td>${vo.f_num }</td>
						<td><a href="/semi_team1/select?num=${vo.num }">${vo.title_name }</a></td>
						<td>${vo.writer }</td>
						<td>${vo.up }</td>
						<td>${vo.hits }</td>
						<td>${vo.regdate }</td>
					</tr>
				</c:forEach>
			</table>
			<input class="btn btn-sm btn-success" type="button" value="글쓰기"
				onclick="location.href = '/semi_team1/write?s_num=${s_num}';">
			<br> <br>
			<%
				String search = request.getParameter("search");
				if (search == null) {
					search = "";
				}

				String selected1 = "";
				String selected2 = "";
				String selected3 = "";
				if (search.equals("writer")) {
					selected1 = "selected=selected";
				} else if (search.equals("title_name")) {
					selected2 = "selected=selected";
				} else if (search.equals("content")) {
					selected3 = "selected=selected";
				}
			%>
			<form action="/semi_team1/list">
				<select name="search">
					<option value="writer" <%=selected1%>>글쓴이</option>
					<option value="title_name" <%=selected2%>>제목</option>
					<option value="content" <%=selected3%>>내용</option>
				</select> <input type="text" name="keyword" value="${param.keyword }">
				<input class="btn btn-sm btn-success" type="submit" value="검색">
			</form>

			<br />

			<div>
				<c:choose>
					<c:when test="${startPageNum>20 }">
						<a
							href="/semi_team1/list?s_num=${s_num }&pageNum=${startPageNum-1 }&search=${param.search}&keyword=${param.keyword}">[이전]</a>
					</c:when>
					<c:otherwise>
			[이전]
		</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a
								href="/semi_team1/list?s_num=${s_num }&pageNum=${i }&search=${param.search}&keyword=${param.keyword}">
								<span style="color: lime">[${i }]</span>
							</a>
						</c:when>
						<c:otherwise>
							<a
								href="/semi_team1/list?s_num=${s_num }&pageNum=${i }&search=${param.search}&keyword=${param.keyword}">
								<span style="color: blue"> [${i }] </span>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${endPageNum<pageCount }">
						<a
							href="/semi_team1/list?s_num=${s_num }&pageNum=${endPageNum+1 }&search=${param.search}&keyword=${param.keyword}">[다음]</a>
					</c:when>
					<c:otherwise>
			[다음]
		</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

</body>
</html>