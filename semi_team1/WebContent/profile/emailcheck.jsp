<%@page import="com.team1.dao.UserUpdateDao"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.team1.dao.JoinDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String m_mail=request.getParameter("m_mail");
	
	UserUpdateDao dao=new UserUpdateDao();
	boolean check=dao.emailcheck(m_mail);
	
	JSONObject json=new JSONObject();
	json.put("check",check);
	
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print(json.toString());
	pw.close();
%>