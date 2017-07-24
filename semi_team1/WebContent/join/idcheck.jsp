<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.team1.dao.JoinDao"%>
<%@page import="com.team1.vo.JoinVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=request.getParameter("id");
	
	JoinDao dao=new JoinDao();
	boolean check=dao.idcheck(id);
	
	JSONObject json=new JSONObject();
	json.put("check",check);
	
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print(json.toString());
	pw.close();
%>