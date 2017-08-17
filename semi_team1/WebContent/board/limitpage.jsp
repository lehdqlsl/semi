<%@page import="java.util.Date"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.team1.db.DBCPBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String m_nick = request.getParameter("writer");
	System.out.println(m_nick);
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int limitChk = 0;
	int cnt = 0;
	Date limit_date = null;
	try {
		con = DBCPBean.getConn();
		String sql = "select trunc((limit_date)-sysdate,0) cnt, stop,limit_date from members where m_nick=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, m_nick);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			cnt = rs.getInt("cnt");
			int stop = rs.getInt("stop");
			limit_date = rs.getDate("limit_date");
			if (stop == 1) {
				limitChk = 1;
			} else if (stop == 0 && cnt > 0) {
				limitChk = 2;
			} else if (stop == 0 && cnt < 0) {
				limitChk = 3;
			}
		}
	} catch (SQLException se) {
		System.out.println(se.getMessage());
	} finally {
		DBCPBean.close(con, pstmt, rs);
	}
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw = response.getWriter();
	pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	pw.println("<data>");
	pw.println("<limitChk>" + limitChk + "</limitChk>");
	pw.println("<cnt>" + cnt + "</cnt>");
	pw.println("<limit_date>" + limit_date + "</limit_date>");
	pw.println("</data>");
	pw.close();
%>