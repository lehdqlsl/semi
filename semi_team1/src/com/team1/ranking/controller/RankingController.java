package com.team1.ranking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.JoinDao;
import com.team1.vo.JoinVo;
@WebServlet("/ranking/list")
public class RankingController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JoinDao dao = new JoinDao();
		ArrayList<JoinVo> list = dao.rankingList();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/index.jsp?page=/manager/managerIndex.jsp&s_page=/ranking/ranking.jsp").forward(request, response);
	}
}
