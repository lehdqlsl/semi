<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/semi_team1/rs/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/semi_team1/rs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="signin.css" rel="stylesheet">
<script type="text/javascript" src="/semi_team1/rs/assets/js/ie-emulation-modes-warning.js">
	function delete() {
		
	}

</script>
</head>
<body>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
   <div style="margin: auto; width: 1000px">
          <table class="table table-bordered">
          	<thead>
          		<tr>
          		<th>${requestScope.vo.s_num}${requestScope.vo.title_name}</th>
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
          		<td>
          		<input class="btn btn-success" type="button" value="수정" onclick = "location.href = 'index.jsp?page=board/update.jsp?num=${requestScope.vo.num}';">
          		<input class="btn btn-success" type="button" value="삭제" onclick = delete()>
          		<input class="btn btn-success" type="button" value="글쓰기" onclick = "location.href = 'index.jsp?page=board/insert.jsp';">
          		<input class="btn btn-success" type="button" value="목록" onclick = "location.href = '/semi_team1/list';">
          		</td>
          	</tr>
          </table>
    </div>
</div>  
          
</body>
</html>