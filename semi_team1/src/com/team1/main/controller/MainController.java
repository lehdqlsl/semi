package com.team1.main.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.BoardTastyDao;
import com.team1.dao.G_ReviewDao;
import com.team1.dao.GameDao;
import com.team1.dao.MovieDao;
import com.team1.dao.MusicDao;
import com.team1.dao.TastyDao;
import com.team1.vo.BoardTastyVo;
import com.team1.vo.GameVo;
import com.team1.vo.M_ReviewVo;
import com.team1.vo.MusicVo;
import com.team1.vo.boardListVo;

@WebServlet("/mainlist")
public class MainController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GameDao dao1 = new GameDao();
		G_ReviewDao g_dao1 = new G_ReviewDao();
		MovieDao dao2 = new MovieDao();
		BoardDao dao3 = new BoardDao();
		BoardTastyDao dao4 = new BoardTastyDao();
		MusicDao dao5 = new MusicDao();

		String spageNum = request.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum * 5) - 4;
		int endRow = (pageNum * 5);

		ArrayList<GameVo> main1 = dao1.gameList(1, 8);

		for (GameVo vo : main1) {
			vo.setAvg(g_dao1.getReviewAvg(vo.getG_num()));
		}
		ArrayList<M_ReviewVo> main2 = dao2.getRanking();
		ArrayList<boardListVo> main3 = dao3.sportsMain(20, 10);
		ArrayList<BoardTastyVo> main4 = dao4.boblist();
		ArrayList<MusicVo> main5 = dao5.musiclist(startRow, endRow);

		if (main5 != null) {
			int pageCount = (int) (Math.ceil(dao5.getMusicCount() / 5.0));
			int startPageNum = pageNum;
			int endPageNum = pageNum;
			if (endPageNum > pageCount) {
				endPageNum = pageCount;
			}

			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);

			request.setAttribute("main1", main1);
			request.setAttribute("main2", main2);
			request.setAttribute("main3", main3);
			request.setAttribute("main4", main4);
			request.setAttribute("main5", main5);

			request.getRequestDispatcher("/index.jsp?page=/content.jsp").forward(request, response);

		}
	}
}
