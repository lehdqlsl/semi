package com.team1.game.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.G_ReviewDao;
import com.team1.dao.GameDao;
import com.team1.vo.G_ReviewVo;
import com.team1.vo.GameVo;
import com.team1.vo.boardListVo;

@WebServlet("/game/ranking")
public class GameRankingController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		G_ReviewDao gdao = new G_ReviewDao(); // 게임 랭킹 Dao
		ArrayList<G_ReviewVo> list = gdao.getRanking();

		String spageNum = request.getParameter("pageNum");
		// System.out.println("페이지넘:" + spageNum);
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum * 5) - 4;
		int endRow = (pageNum * 5);
		GameDao dao = new GameDao();
		ArrayList<GameVo> glist = dao.gameList(startRow, endRow);

		if (glist != null) {
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
		}

		request.setAttribute("glist", glist);

		if (list != null) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("/index.jsp?page=game/content.jsp").forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
