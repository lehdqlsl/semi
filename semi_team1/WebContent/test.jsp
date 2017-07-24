
<%@page import="com.team1.db.DBCPBean"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>

<%@page import="javax.naming.InitialContext"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();
	%>

	conn :
	<%=conn%>
	<br>
	<%
		Connection conn1 = DBCPBean.getConn();
	%>

	conn1:<%=conn1%>
</body>
</html>