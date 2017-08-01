package com.team1.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;

@WebServlet("/boarddelete")
public class DeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boardnum = Integer.parseInt(request.getParameter("b_num"));
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		BoardDao dao = new BoardDao();
		int n = dao.blindUpdate(boardnum);
		if (n > 0) {
			response.sendRedirect("/semi_team1/list?s_num=" + s_num);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}

	}
}
