package com.team1.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;

@WebServlet("/board/report/update")
public class ReportUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num=Integer.parseInt(request.getParameter("num"));
		String writer=request.getParameter("writer");
		
		System.out.println("num"+num);
		System.out.println("writer"+writer);
		
		BoardDao dao=new BoardDao();
		int n=dao.reportUpdate(num);
		System.out.println("n°ª"+n);
		
		if(n>0){
			response.sendRedirect("/semi_team1/select?num="+num+"&writer="+writer);
		}else{
			request.setAttribute("result", "fail");
			RequestDispatcher rd=request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
