package com.team1.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.team1.dao.CategoryDao;
import com.team1.dao.JoinDao;

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
			url = "/index.jsp?page=/movie/movieIndex.jsp&s_page=/board/insert.jsp";
			break;

		case 3: // ������
			url = "/index.jsp?page=/sport/sportIndex.jsp&s_page=/board/insert.jsp";
			break;

		case 4: // ����
			if (s_num == 30 || s_num == 31) {
				url = "/index.jsp?page=/tasty/tastyIndex.jsp&s_page=/board/insert.jsp";
			} else {
				url = "/index.jsp?page=/tasty/tastyIndex.jsp&s_page=/tasty/insert.jsp";
			}

			break;

		case 5: // ����
			url = "/index.jsp?page=/music/musicIndex.jsp&s_page=/board/insert.jsp";
			break;

		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
