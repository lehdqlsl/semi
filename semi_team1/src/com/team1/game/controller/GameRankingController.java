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
import com.team1.vo.G_ReviewVo;
import com.team1.vo.boardListVo;
@WebServlet("/game/ranking")
public class GameRankingController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		G_ReviewDao dao=new G_ReviewDao(); // ∞‘¿” ∑©≈∑ Dao
		ArrayList<G_ReviewVo> list=dao.getRanking();
		
		if (list != null) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("/index.jsp?page=game/content.jsp").forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
