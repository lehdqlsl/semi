package com.team1.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.CategoryDao;
import com.team1.vo.boardVo;

@WebServlet("/mywritelist")
public class MyWriteListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");

		if (keyword == null || keyword.equals("")) {
			keyword = "";
			search = "";
		}

		String spageNum = request.getParameter("pageNum");
		String writer=request.getParameter("writer");
		int s_num = Integer.parseInt(request.getParameter("s_num"));

		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum * 20) - 19;
		int endRow = (pageNum * 20);

		BoardDao dao = new BoardDao();
		ArrayList<boardVo> list = dao.MyWriteList(writer, startRow, endRow, search, keyword);

		if (list != null) {
			int pageCount = (int) (Math.ceil(dao.getCount(s_num, search, keyword) / 20.0));
			int startPageNum = (int) (Math.ceil(pageNum / 10.0) * 10 - 9); 
			int endPageNum = (int) (Math.ceil(pageNum / 10.0) * 10); 
			if (endPageNum > pageCount) {
				endPageNum = pageCount;
			}


			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("list", list);
			request.setAttribute("s_num", s_num);
			request.setAttribute("writer", writer);
			request.getRequestDispatcher("/index.jsp?page=/board/list.jsp")
			.forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
