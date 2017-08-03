package com.team1.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.M_ReviewDao;
import com.team1.vo.M_ReviewVo;

@WebServlet("/moviereviewinsert")
public class MovieReviewInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		float rating = Float.parseFloat(request.getParameter("rating"));
		String content = request.getParameter("content");
		
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		String m_nick = request.getParameter("m_nick");

		M_ReviewVo vo = new M_ReviewVo(0, m_num, m_nick, rating, content, null, 0, 0, 1);
		M_ReviewDao dao = new M_ReviewDao();

		// 작성가능한지 확인
		boolean is = dao.isPossible(m_nick, m_num);

		// 리뷰작성
		if (is) {
			int n = dao.insert(vo);
			if (n > 0) {
				String url1 = request.getHeader("referer");
				response.sendRedirect(url1);
			}
		} else {
			request.setAttribute("error", "이미 리뷰를 등록하셨습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/movieselect?m_num=" + m_num);
			rd.forward(request, response);
		}
	}
}
