package com.team1.tasty.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.team1.dao.BoardDao;
import com.team1.dao.BoardTastyDao;
import com.team1.vo.boardVo;

@WebServlet("/tasty/update")
public class UpdateController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title_name");
		String content = request.getParameter("content");
		String addr = request.getParameter("addr");
		String map = request.getParameter("map");
		int num = Integer.parseInt(request.getParameter("num"));

		BoardTastyDao dao = new BoardTastyDao();
		int n = dao.update(title, content, addr, map, num);

		if (n > 0) {
			response.sendRedirect("/semi_team1/tasty/select?num=" + num);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}

	}
}
