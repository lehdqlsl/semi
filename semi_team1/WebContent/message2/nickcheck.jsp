<%@page import="com.team1.message2.dao.sendDao2"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.team1.dao.JoinDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String m_nick=request.getParameter("m_nick");
	
	sendDao2 dao=new sendDao2();
	System.out.print("m_nick:"+m_nick);
	boolean check=dao.nickcheck(m_nick);
	
	JSONObject json=new JSONObject();
	json.put("check",check);
	
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print(json.toString());
	pw.close();
%>