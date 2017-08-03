package com.team1.game.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.GameDao;
import com.team1.vo.GameVo;

@WebServlet("/gameList")
public class GameListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String spageNum = request.getParameter("pageNum");
		//System.out.println("페이지넘:" + spageNum);
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum * 5) - 4;
		int endRow = (pageNum * 5);
		GameDao dao = new GameDao();
		ArrayList<GameVo> list = dao.gameList(startRow, endRow);

		if (list != null) {
			int pageCount = (int) (Math.ceil(dao.gameCount() / 5.0));
			int startPageNum = pageNum;
			int endPageNum = pageNum;
			if (endPageNum > pageCount) {
				endPageNum = pageCount;
			}
			
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/index.jsp?page=game/gameInfo.jsp").forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}