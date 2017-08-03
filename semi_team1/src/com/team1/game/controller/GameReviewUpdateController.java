package com.team1.game.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.G_ReviewDao;
import com.team1.dao.ReplyDao;

@WebServlet("/game/review/update")
public class GameReviewUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gr_num=Integer.parseInt(request.getParameter("gr_num"));
		int g_num=Integer.parseInt(request.getParameter("g_num"));
		String m_nick=request.getParameter("m_nick");
		String sessionNick=request.getParameter("sessionNick");
		
		
		
		int flag=0;
		
		if(m_nick.equals(sessionNick) || sessionNick.equals("admin")){
			G_ReviewDao dao=new G_ReviewDao();
			int n=dao.update(gr_num);
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
		
		
		
		
		
		/*
		if(n>0){
			request.setAttribute("flag", );
			response.sendRedirect("/semi_team1/gameinfo?g_num="+g_num);
		}else{
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}*/
	}
}
