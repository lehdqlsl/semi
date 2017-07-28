<%@page import="com.team1.message2.dao.recvDao2"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.team1.dao.JoinDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String nick = (String) session.getAttribute("m_nick");
	recvDao2 dao1 = new recvDao2();
	int recvm=dao1.getMsgCount(nick);
	
	JSONObject json=new JSONObject();
	json.put("cnt",recvm);
	
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print(json.toString());
	pw.close();
%>