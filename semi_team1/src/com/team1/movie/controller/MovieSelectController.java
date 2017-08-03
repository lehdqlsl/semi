package com.team1.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.MovieDao;
import com.team1.vo.MovieVo;

@WebServlet("/movieselect")
public class MovieSelectController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		MovieDao dao = new MovieDao();
		MovieVo vo = dao.select(m_num);
		if (vo != null) {
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/index.jsp?page=movie/select.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
