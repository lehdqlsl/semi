<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
	<div style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word;">
	
	
			<c:forEach var="vo" items="${requestScope.bestlist }"
					varStatus="status" begin="0" end="6" step="2">
					<tr class="active">
						<td><a
							href="/semi_team1/select?num=${bestlist[status.index].num}">${bestlist[status.index].title_name}
						</a></td>
						<td><a
							href="/semi_team1/select?num=${bestlist[status.index+1].num}">${bestlist[status.index+1].title_name}</a></td>
					</tr>
				</c:forEach>	
	
			
<table class="table table-bordered">
<tr>
	<td rowspan="2" align="center">◁</td>
	<td style="hegith: 175px; width: 175px;">
	<img src="/semi_team1/upload/" style="height: 150px; width: 150px; margin: auto;"></td>
	<td style="hegith: 175px; width: 175px;">2</td>
	<td>3</td>
	<td>4</td>
	<td>5</td>
</tr>
<tr>
	<td>1</td>
	<td>2</td>
	<td>3</td>
	<td>4</td>
	<td>5</td>
	<td rowspan="2" align="center">▷</td>
	<td></td>
</tr>

</table>

	</div>
</div>
      </body>
</html>