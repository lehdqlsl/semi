<%@page import="com.team1.vo.ProfileVo"%>
<%@page import="com.team1.dao.ProfileDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	request.setCharacterEncoding("utf-8");
	int num=Integer.parseInt(request.getParameter("num"));
	ProfileDao dao=new ProfileDao();
	ProfileVo vo=dao.select(num);
--%>
<table class="table table-bordered">
	<tr>
	<td rowspan="4" width="230px"> 이미지</td>
	<td width="400px" colspan="2">*삭제시 기본이미지 적용</td>
	<td>1</td>
	</tr>
	
	<tr>
	<td colspan="2"> *업로드시 즉시적용 될수도</td>
	<td>2</td>
	</tr>
	
	<tr>
	<td colspan="2"> (150px * 150px, jpg, jgep, png, gif, 최대용량 2MB)</td>
	<td>3</td>
	</tr>
	
	<tr>
	<td width="150px"><input type="file" name="file1" > </td>
	<td><input type="button" name="imgupload" value="적용"> </td>
	<td>가입일:</td>
	</tr>
	</table>

</body>
</html>