<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div style="margin: auto; width: 1000px; height: 1300px;">
		<h2><span class="label label-danger">보낸 쪽지함</span></h2><br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>받는 사람</th>
						<th>내용</th>
						<th>보낸 날짜</th>
						<th>삭제</th>
						<th>상대방확인</th>
						<th>발송취소</th>
					</tr>
				</thead>
				<c:forEach var="vo" items="${requestScope.list }">
					<tr>
					<td>${vo.receiver }</td>
					<td><a href="/semi_team1/sendview2?num=${vo.num }">${vo.content }</a></td>
					<td>${vo.regdate }</td>
					<td>
					<a href="/semi_team1/senddelete2?num=${vo.num}">삭제</a>
					</td>
					
					<td>
					<c:choose>
						<c:when test="${vo.chk==1 }">
							읽지않음
						</c:when>
						
						<c:otherwise>
							읽음
						</c:otherwise>
					</c:choose>
					</td>
					
					<td>
						<c:choose>
							<c:when test="${vo.chk==1 && vo.send_cxl==1 }">
								<a href="/semi_team1/sendcancel2?num=${vo.num}">발송취소</a>
							</c:when>
							<c:when test="${vo.chk==1 && vo.send_cxl==0 }">
								취소완료
							</c:when>
							<c:otherwise>
								취소불가
							</c:otherwise>
						</c:choose>
					</td>
					</tr>
				</c:forEach>
			</table>
			
			<div>
				<c:choose>
					<c:when test="${startPageNum>10 }">
						<a
							href="">
							<input class="btn btn-xs btn-danger" type="submit" value="◁">
						</a>
					</c:when>
					<c:otherwise>
			<input class="btn btn-xs btn-danger" type="submit" value="◁">
		</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a	style="text-decoration: none"
								href="">
								<input class="btn btn-xs btn-link" type="submit" value="${i}">
							</a>
						</c:when>
						<c:otherwise>
							<a 	style="text-decoration: none"
								href="">
								<input class="btn btn-xs btn-link" type="submit" value="${i}">
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${endPageNum<pageCount }">
						<a
							href="">
							<input class="btn btn-xs btn-danger" type="submit" value="▷">
						</a>
					</c:when>
					<c:otherwise>
			<input class="btn btn-xs btn-danger" type="submit" value="▷">
		</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

</body>
</html>