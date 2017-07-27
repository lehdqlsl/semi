package com.team1.manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;

@WebServlet("/board/blind")
public class BlindUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int boardnum=Integer.parseInt(request.getParameter("boardnum"));
		
		BoardDao dao=new BoardDao();
		int n=dao.blindUpdate(boardnum);
		
		if(n>0){
			request.getRequestDispatcher("/report/list").forward(request,response);
		}else{
			request.setAttribute("result", "fail");
			RequestDispatcher rd=request.getRequestDispatcher("경로없음");
			rd.forward(request, response);
		}
	}
}
