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

		// �ۼ��������� Ȯ��
		boolean is = dao.isPossible(m_nick, num);

		// �����ۼ�
		if (is) {
			int n = dao.insert(vo);
			if (n > 0) {
				String url1 = request.getHeader("referer");
				response.sendRedirect(url1);
			}
		} else {
			request.setAttribute("error", "�̹� ���並 ����ϼ̽��ϴ�.");
			RequestDispatcher rd = request.getRequestDispatcher("/tasty/select?num=" + num);
			rd.forward(request, response);
		}
	}
}
