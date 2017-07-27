package com.team1.message2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.message2.dao.sendDao2;
import com.team1.vo.Message2Vo;

@WebServlet("/sendinsert2")
public class SendInsertController2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		String content = request.getParameter("content");

		HttpSession session = request.getSession();
		String m_nick = (String) session.getAttribute("m_nick");

		Message2Vo vo = new Message2Vo(0, sender, receiver, content, 0, null, 0);
		sendDao2 dao = new sendDao2();
		int n = dao.insert(vo);

		request.setAttribute("m_nick", m_nick);
		if (n > 0) {
			String prev = request.getParameter("prev");
			response.sendRedirect(prev);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
