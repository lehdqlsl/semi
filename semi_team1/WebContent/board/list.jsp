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
	            	<c:forEach var="vo" items="${requestScope.list }">
				<tr>
					<td>${vo.num }</td>
					<td>${vo.f_num }</td>
					<td>${vo.title_name }</td>
					<td>${vo.writer }</td>
					<td>${vo.up }</td>
					<td>${vo.hit }</td>
					<td>${vo.regdate }</td>
				</tr>		
			</c:forEach>
		</table>
       </div>
</body>
</html>