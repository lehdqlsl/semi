<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="row">
        <div class="col-md-6">
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
	            	<c:forEach var="dto" items="${requestScope.list }">
				<tr>
					<td>${dto.num }</td>
					<td>${dto.writer }</td>
					<td><a href="board?cmd=getInfo&num=${dto.num }">${dto.title }</a></td>
					<td>
						<a href="board?cmd=delete&num=${dto.num }">삭제</a>
					</td>
				</tr>		
			</c:forEach>
		</table>
       </div>
</body>
</html>