package com.team1.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.M_ReviewDao;

@WebServlet("/moviereviewdelete")
public class MovieReviewDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_num=Integer.parseInt(request.getParameter("r_num"));
		int m_num=Integer.parseInt(request.getParameter("m_num"));
		String m_nick=request.getParameter("m_nick");
		String sessionNick=request.getParameter("sessionNick");
				
		int flag=0;
		
		if(m_nick.equals(sessionNick) || sessionNick.equals("admin")){
			M_ReviewDao dao=new M_ReviewDao();
			int n=dao.update(r_num);
		}else{
			flag=1;
		}
		
		response.setContentType("text/xml;charset=utf-8"	);
		PrintWriter pw=response.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<data>");
		pw.println("<flag>" + flag + "</flag>");
		pw.println("</data>");
		pw.close();
	}
}
