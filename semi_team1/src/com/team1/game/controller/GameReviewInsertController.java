package com.team1.game.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.G_ReviewDao;
import com.team1.vo.G_ReviewVo;

@WebServlet("/game/review/insert")
public class GameReviewInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		float rating = Float.parseFloat(request.getParameter("rating"));
		String content = request.getParameter("content");
		int g_num = Integer.parseInt(request.getParameter("g_num"));
		String m_nick = request.getParameter("m_nick");

		G_ReviewVo vo = new G_ReviewVo(0, g_num, m_nick, rating, null, content, 0, 0, 1);
		G_ReviewDao dao = new G_ReviewDao();

		// 작성가능한지 확인
		boolean is = dao.isPossible(m_nick, g_num);

		// 리뷰작성
		if (is) {
			int n = dao.insert(vo);
			if (n > 0) {
				String url1 = request.getHeader("referer");
				response.sendRedirect(url1);
			}
		} else {
			request.setAttribute("error", "이미 리뷰를 등록하셨습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/gameinfo?g_num=" + g_num);
			rd.forward(request, response);
		}
	}
}
