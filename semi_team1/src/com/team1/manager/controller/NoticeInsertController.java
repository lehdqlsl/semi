package com.team1.manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.vo.boardVo;

@WebServlet("/notice/insert")
public class NoticeInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String writer = request.getParameter("writer");
		String title_name = request.getParameter("title_name");
		String content = request.getParameter("content");
		int f_num = 1;
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		String orgFileName = "text.jpg";
		String saveFileName = "text.jpg";
		boardVo vo = new boardVo(0, title_name, 0, 0, orgFileName, saveFileName, content, null, writer, f_num, s_num, 0,
				0, 1);
		BoardDao dao = new BoardDao();
		int n = dao.insert(vo);

		if (n > 0) {
			response.sendRedirect("/semi_team1/list?s_num=" + s_num);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
