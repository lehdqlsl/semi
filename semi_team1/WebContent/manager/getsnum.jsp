<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.team1.vo.CategoryVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.team1.dao.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int f_num = Integer.parseInt(request.getParameter("f_num"));
	CategoryDao dao = new CategoryDao();
	ArrayList<CategoryVo> list = dao.list(f_num);

	//list에 담긴 정보를 json배열로 만들기
	JSONArray jarr = new JSONArray();
	for (int i = 0; i < list.size(); i++) {
		CategoryVo vo = list.get(i);
		JSONObject obj = new JSONObject();
		obj.put("s_num", vo.getS_num());
		obj.put("title", vo.getTitle());
		obj.put("f_num", vo.getF_num());
		jarr.add(obj);
	}

	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw = response.getWriter();
	pw.print(jarr.toString());
	pw.close();
%>