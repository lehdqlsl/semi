<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	var imgs = [ '/semi_team1/rs/img/22.jpg', '/semi_team1/rs/img/33.jpg',
			'/semi_team1/rs/img/44.jpg', '/semi_team1/rs/img/55.jpg',
			'/semi_team1/rs/img/66.jpg', '/semi_team1/rs/img/77.jpg',
			'/semi_team1/rs/img/11.jpg' ];

	var index = 0;

	setTimeout(change, 3000);

	function change() {

		var changeMain = document.getElementById("changeMain");

		changeMain.src = imgs[index++];

		if (index == imgs.length)
			index = 0;

		setTimeout(change, 3000);

	}
	var xhr = null;
	function limitCheck() {
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = callback;
		xhr
				.open(
						'get',
						"/semi_team1/board/limitpage.jsp?writer=${sessionScope.m_nick }",
						true);
		xhr.send();
		console.log("${sessionScope.m_nick }");
	}
	function callback() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseXML;
			var limitchk = data.getElementsByTagName("limitChk")[0].firstChild.nodeValue;
			var limitdays = data.getElementsByTagName("cnt")[0].firstChild.nodeValue;
			var limitdate = data.getElementsByTagName("limit_date")[0].firstChild.nodeValue;
			console.log("callback" + limitchk + "/" + limitdays);
			if (limitchk == 1) {
				alert("제재처리로 글을 작성할 수 없습니다. [ " + limitdate + " ] 이후부터 작성가능");
			} else if (limitchk == 2) {
				alert("신규회원 글작성 제한기간입니다. [ " + limitdate + " ] 이후부터 작성가능");
			} else if (limitchk == 3) {
				console.log("확인");
				location.href = "/semi_team1/write?s_num=${s_num}";
			}
		}
	}
</script>
</head>
<body>


	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">

		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word; height: 35px; background-color: #428bca; margin-bottom: 0px; padding: 1px;">
			<h5 style="margin-left: 10px; font-weight: bold; color: white;">${requestScope.title }</h5>
		</div>

		<!-- 베스트 게시글 -->
		<div
			style="width: 1000px; word-break: break-all; word-wrap: break-word; margin: auto;">
			<div style="float: left;">
				<table class="table">
					<tr class="">
						<th style="text-align: center; font-size: 18px;" colspan="2">게시판
							<span style="font-weight: bold; color: red">BEST</span>
						</th>
					</tr>

					<c:forEach var="vo" items="${requestScope.bestlist }"
						varStatus="status" begin="0" end="6" step="2">
						<tr class="">
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 425px;"><a
								href="/semi_team1/tasty/select?num=${bestlist[status.index].num}">${bestlist[status.index].title}
							</a></td>
							<td
								style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 425px;"><a
								href="/semi_team1/tasty/select?num=${bestlist[status.index+1].num}">${bestlist[status.index+1].title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 유리사진 -->

			<div id="mainImage">
				<img src="/semi_team1/rs/img/11.jpg" id="changeMain"
					class="img-thumbnail"
					style="width: 150px; height: 150px; float: right;">
			</div>
		</div>

		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">

			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="width: 100px">게시글번호</th>
						<th style="text-align: center">제목</th>
						<th style="width: 140px">글쓴이</th>
						<th style="width: 75px; text-align: center">평점</th>
						<th style="width: 75px; text-align: center">조회</th>
						<th style="width: 100px; text-align: center">날짜</th>
					</tr>
				</thead>
				<c:forEach var="vo" items="${requestScope.list }">
					<tr>
						<!--  	
						/semi_team1/index.jsp?page=/game/gameIndex.jsp&s_page=/board/insert.jsp 
						/semi_team1/select?num=${vo.num }
				-->
						<td style="text-align: center">${vo.num }</td>
						<td><a href="/semi_team1/tasty/select?num=${vo.num }">${vo.title }
								[${vo.cnt }]</a></td>
						<td>${vo.writer }</td>
						<td style="text-align: center">${vo.up }/10</td>
						<td style="text-align: center">${vo.hits }</td>
						<td style="text-align: center">${vo.regdate }</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${!empty sessionScope.m_nick}">

				<input class="btn btn-sm btn-success" type="button" value="글쓰기"
					onclick="limitCheck()">
				<%-- <input class="btn btn-sm btn-success" type="button" value="글쓰기" onclick="location.href = '/semi_team1/write?s_num=${s_num}';">--%>
				<br>
				<br>
			</c:if>


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
							href="/semi_team1/list?s_num=${s_num }&pageNum=${startPageNum-1 }&search=${param.search}&keyword=${param.keyword}">
							<input class="btn btn-xs btn-primary" type="submit" value="◁">
						</a>
					</c:when>
					<c:otherwise>
						<input class="btn btn-xs btn-primary" type="submit" value="◁">
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a style="text-decoration: none"
								href="/semi_team1/list?s_num=${s_num }&pageNum=${i }&search=${param.search}&keyword=${param.keyword}">
								<input class="btn btn-xs btn-link" type="submit" value="${i}">
							</a>
						</c:when>
						<c:otherwise>
							<a style="text-decoration: none"
								href="/semi_team1/list?s_num=${s_num }&pageNum=${i }&search=${param.search}&keyword=${param.keyword}">
								<input class="btn btn-xs btn-link" type="submit" value="${i}">
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${endPageNum<pageCount }">
						<a
							href="/semi_team1/list?s_num=${s_num }&pageNum=${endPageNum+1 }&search=${param.search}&keyword=${param.keyword}">
							<input class="btn btn-xs btn-primary" type="submit" value="▷">
						</a>
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