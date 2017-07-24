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
<script src="/semi_team1/rs/assets/js/ie-emulation-modes-warning.js"></script>

</head>
<body>
   <div class="col-md-6">
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
    </div>      
</body>
</html>