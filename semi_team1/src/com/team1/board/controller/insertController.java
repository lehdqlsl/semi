package com.team1.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.CategoryDao;

import sun.security.x509.CertAttrSet;

@WebServlet("/write")
public class insertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int s_num = Integer.parseInt(request.getParameter("s_num"));

		CategoryDao dao = new CategoryDao();

		int f_num = dao.isMember(s_num);

		request.setAttribute("s_num", s_num);
		String url = "";

		switch (f_num) {
		case 1: // ����
			url = "/index.jsp?page=/game/gameIndex.jsp&s_page=/board/insert.jsp";
			break;

		case 2: // ��ȭ

			break;

		case 3: // ������

			break;

		case 4: // ����

			break;

		case 5: // ����

			break;

		}

		request.getRequestDispatcher(url).forward(request, response);

	}
}
