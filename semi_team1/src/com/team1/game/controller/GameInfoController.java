package com.team1.game.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.G_ReviewDao;
import com.team1.dao.GameDao;
import com.team1.dao.JoinDao;

import com.team1.vo.G_ReviewVo;
import com.team1.vo.GameVo;


@WebServlet("/gameinfo")
public class GameInfoController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameDao dao = new GameDao();
		G_ReviewDao grdao=new G_ReviewDao();
		JoinDao memdao = new JoinDao();

		int g_num=Integer.parseInt(request.getParameter("g_num"));
		GameVo vo=dao.select(g_num);
		
		double cntAvg=grdao.getReviewAvg(g_num);
		
		String spageNum = request.getParameter("pageNum");
		
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 10 + 1;
		int endRow = startRow + 9;

		ArrayList<G_ReviewVo> list = grdao.gReviewList(g_num, startRow, endRow);
		int cntTot = grdao.getCnt(g_num);

		int pageCount = (int) Math.ceil(grdao.getCnt(g_num) / 10.0);
		int startPageNum = ((pageNum - 1) / 10) * 10 + 1;

		int endPageNum = startPageNum + 9;
		if (endPageNum > pageCount) {
			endPageNum = pageCount;
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPageNum);
		request.setAttribute("endPage", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("list", list);
		request.setAttribute("cntTot", cntTot);
		request.setAttribute("cntAvg", cntAvg);
		request.setAttribute("b_num", request.getParameter("num"));
		
		if(vo!=null){
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/index.jsp?page=/game/gameSelect.jsp").forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
