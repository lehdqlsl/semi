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
	<div style="margin: auto; width: 800px; word-break: break-all; word-wrap: break-word;">
		<table class="table table-bordered" width="600px" border="1">
			<tr>
				<td rowspan="5" width="210px" height="300px">
				<img src="/semi_team1/upload/${vo.saveimg }" 
				style="width: 210px; height: 300px;  margin: auto;">
				</td>
				<td colspan="4" width="300px" height="150px"><h3>${vo.m_name }</h3></td>
			</tr>
			<tr>
				<td height="15px">${vo.m_genre }</td>
				<td height="15px">${vo.m_country }</td>
				<td height="15px">${vo.m_rt }</td>
				<td height="15px">${vo.m_release }</td>
			</tr>
			<tr>
				<td colspan="4">${vo.m_director }</td>
			</tr>
			<tr>
				<td colspan="4">${vo.m_actor }</td>
			</tr>
			<tr>
				<td colspan="4">${vo.m_rate }</td>
			</tr>
		</table>
		<table class="table table-bordered" width="600px" border="1">
		<tr>
			<td>
				<textarea cols="108" rows="15" name="notice" readonly="readonly"
				style="resize: none;">
				${vo.story }
				</textarea>
			</td>
		</tr>
		</table>
		<table class="table table-bordered" width="600px" border="1">
		<tr>
			<td>
				${vo.link }
			</td>
		</tr>
		</table>
		
		
	</div>
</div>	

</body>
</html>