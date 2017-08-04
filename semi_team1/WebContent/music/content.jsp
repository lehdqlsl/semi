<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function showImg(event) {
		var img = event.target;
		img.style.opacity = 1.2;

	}
	function noshowImg(event) {
		var img = event.target;
		img.style.opacity = 0.7;
	}
</script>
</head>
<body>

	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>

	<!-- 메인페이지 -->
	
	<!-- 정보 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
	<c:choose>
			<c:when test="${sessionScope.m_nick=='admin' }">
				<input type="button" value="음악 등록" class="btn btn-xs btn-default" onclick="location.href='/semi_team1/index.jsp?page=music/musicInsert.jsp'">
			</c:when>
		</c:choose>
	<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
	<table class="table table-bordered" width="800px" height="150px">
			<tr><td colspan="7" style="font-size: 18px;font-weight: bold"><a href="#">음악 정보</a></td></tr>
			<tr>
			<td><c:choose>
					<c:when test="${startPageNum>1 }">
						<a
							href="/semi_team1/gallerylist?num=${num }&pageNum=${startPageNum-1 }">
							<input class="btn btn-xs btn-primary" type="submit" value="◁"
							style="vertical-align: center;">
						</a>
					</c:when>
					<c:otherwise>
						<input class="btn btn-xs btn-primary" type="submit" value="◁">
					</c:otherwise>
				</c:choose></td>
			<c:forEach var="vo" items="${requestScope.mlist }">
				<td align="center"><a
					href="/semi_team1/musicSelect?num=${vo.num}"><img
						src="/semi_team1/rs/img/musicImg/${vo.savemimg}" class="img-circle"
						onmouseover="showImg(event)" onmouseout="noshowImg(event)"
						style="height: 150px; width: 150px;opacity: 0.7"></a>
				</td>
			</c:forEach>
			<td><c:choose>
					<c:when test="${endPageNum<pageCount }">
						<a
							href="/semi_team1/gallerylist?num=${num }&pageNum=${endPageNum+1 }">
							<input class="btn btn-xs btn-primary" type="submit" value="▷">
						</a>
					</c:when>
					<c:otherwise>
						<input class="btn btn-xs btn-primary" type="submit" value="▷">
					</c:otherwise>
				</c:choose></td></tr>
		</table>
		</div>
	
		<br>	
	<!-- 베스트 게시글 -->
	
		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">

			<table class="table">
				<tr>
					<th style="font-size: 18px;" colspan="2"><a
						href="/semi_team1/list?s_num=41">자유게시판</a></th>
				</tr>

				<c:forEach var="vo" items="${requestScope.flist }"
					varStatus="status" begin="0" end="10" step="2">
					<tr>
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${flist[status.index].num}">${flist[status.index].title_name}
						</a></td>
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${flist[status.index+1].num}">${flist[status.index+1].title_name}</a></td>
					</tr>
				</c:forEach>
			</table>

			<br>
			<table class="table">
				<tr>
					<th style="font-size: 18px;" colspan="2"><a
						href="/semi_team1/list?s_num=41">질문게시판</a></th>
				</tr>

				<c:forEach var="vo" items="${requestScope.qlist }"
					varStatus="status" begin="0" end="10" step="2">
					<tr>
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${qlist[status.index].num}">${qlist[status.index].title_name}
						</a></td>
						<td
							style="display: inline-block; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 500px;"><a
							href="/semi_team1/select?num=${qlist[status.index+1].num}">${qlist[status.index+1].title_name}</a></td>
					</tr>
				</c:forEach>
			</table>

			<br>

			
			<table class="table table-bordered" width="1000px" height="200px">
				<tr>
						<td colspan="5" style="font-size: 18px;font-weight: bold"><a
						href="/semi_team1/list?s_num=42">갤러리</a></td>
				</tr>
				<tr>
					<c:forEach var="vo" items="${requestScope.list }">
						<td align="center"><a href="/semi_team1/select?num=${vo.num}">${vo.tag }</a>
						</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="vo" items="${requestScope.list }">
						<td align="center">제목 : ${vo.title_name }</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="vo" items="${requestScope.list }">
						<td align="center">작성자 : ${vo.writer }</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>