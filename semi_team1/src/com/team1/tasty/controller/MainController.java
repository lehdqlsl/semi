package com.team1.tasty.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.BoardTastyDao;
import com.team1.vo.BoardTastyVo;
import com.team1.vo.boardListVo;

@WebServlet("/tasty/main")
public class MainController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		ArrayList<boardListVo> free = dao.sportsMain(30, 5);
		ArrayList<boardListVo> qa = dao.sportsMain(31, 5);

		BoardTastyDao tdao = new BoardTastyDao();
		ArrayList<BoardTastyVo> s1 = tdao.bestlist(32, 5);
		ArrayList<BoardTastyVo> s2 = tdao.bestlist(33, 5);
		ArrayList<BoardTastyVo> s3 = tdao.bestlist(34, 5);
		ArrayList<BoardTastyVo> s4 = tdao.bestlist(35, 5);
		ArrayList<BoardTastyVo> s5 = tdao.bestlist(36, 5);
		ArrayList<BoardTastyVo> s6 = tdao.bestlist(37, 5);
		ArrayList<BoardTastyVo> bob = tdao.boblist();

		request.setAttribute("free", free);
		request.setAttribute("qa", qa);

		request.setAttribute("s1", s1);
		request.setAttribute("s2", s2);
		request.setAttribute("s3", s3);
		request.setAttribute("s4", s4);
		request.setAttribute("s5", s5);
		request.setAttribute("s6", s6);
		request.setAttribute("bob", bob);

		request.getRequestDispatcher("/index.jsp?page=/tasty/content.jsp").forward(request, response);

	}
}
