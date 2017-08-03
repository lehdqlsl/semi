package com.team1.tasty.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.TastyDao;
import com.team1.vo.TastyVo;

@WebServlet("/tasty/review/insert")
public class ReviewInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		float rating = Float.parseFloat(request.getParameter("rating"));
		String content = request.getParameter("content");
		int num = Integer.parseInt(request.getParameter("num"));
		String m_nick = request.getParameter("m_nick");

		TastyVo vo = new TastyVo(0, num, m_nick, rating, null, content, 0, 0, 1);
		TastyDao dao = new TastyDao();

		// 작성가능한지 확인
		boolean is = dao.isPossible(m_nick, num);

		// 리뷰작성
		if (is) {
			int n = dao.insert(vo);
			if (n > 0) {
				String url1 = request.getHeader("referer");
				response.sendRedirect(url1);
			}
		} else {
			request.setAttribute("error", "이미 리뷰를 등록하셨습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/tasty/select?num=" + num);
			rd.forward(request, response);
		}
	}
}
