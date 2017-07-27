package com.team1.manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.JoinDao;

@WebServlet("/limit/day")
public class LimitUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int days=Integer.parseInt(request.getParameter("days"));
		String m_nick=request.getParameter("m_nick");
		
		JoinDao dao=new JoinDao();
		int n=dao.limitDateUpdate(days, m_nick);
		
		if(n>0){
			request.getRequestDispatcher("/member/list").forward(request, response);
		}else{
			request.setAttribute("result", "fail");
			RequestDispatcher rd=request.getRequestDispatcher("��ξ���");
			rd.forward(request, response);
		}
	}
}
