package com.team1.profile.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.UserInfoDao;

@WebServlet("/getupcount")
public class GetUpCountController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer=request.getParameter("writer");
		String nick=request.getParameter("nick");
		
		UserInfoDao dao=new UserInfoDao();
		int n1=dao.getUpCount1(writer);
		int n2=dao.getUpCount2(nick);
		
		int n3=n1+n2;
		
		
	}
}
