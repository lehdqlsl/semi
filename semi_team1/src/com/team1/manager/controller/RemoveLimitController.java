package com.team1.manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.JoinDao;

@WebServlet("/remove/member/limit")
public class RemoveLimitController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String m_nick = request.getParameter("m_nick");

		JoinDao dao = new JoinDao();
		int n = dao.removeLimitDateUpdate(m_nick);

		if (n > 0) {
			request.getRequestDispatcher("/member/limit/list").forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("경로없음");
			rd.forward(request, response);
		}
	}
}
