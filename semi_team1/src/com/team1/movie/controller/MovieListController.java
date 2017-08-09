package com.team1.movie.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.M_ReviewDao;
import com.team1.dao.MovieDao;
import com.team1.vo.M_ReviewVo;
import com.team1.vo.MovieVo;

@WebServlet("/movielist")
public class MovieListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String spageNum = request.getParameter("pageNum");

		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum * 5) - 4;
		int endRow = (pageNum * 5);

		MovieDao dao = new MovieDao();
		ArrayList<MovieVo> list = dao.list(startRow, endRow);

		// 영화평점랭킹
		ArrayList<M_ReviewVo> list2 = dao.getRanking();

		if (list != null) {
			int pageCount = (int) (Math.ceil(dao.movieCount() / 5.0));
			int startPageNum = pageNum;
			int endPageNum = pageNum;
			if (endPageNum > pageCount) {
				endPageNum = pageCount;
			}

			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.getRequestDispatcher("/index.jsp?page=movie/main.jsp").forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}

}
