package com.team1.sports.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.vo.boardListVo;

@WebServlet("/sportsmain")
public class SportsMainController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		BoardDao dao = new BoardDao();
		ArrayList<boardListVo> sportsmain1 = dao.sportsMain(20, 10);
		ArrayList<boardListVo> sportsmain2 = dao.sportsMain(21, 10);
		ArrayList<boardListVo> sportsmain3 = dao.sportsMain(23, 10);
		
		request.setAttribute("sportsmain1", sportsmain1);
		request.setAttribute("sportsmain2", sportsmain2);
		request.setAttribute("sportsmain3", sportsmain3);
		
		request.getRequestDispatcher("/index.jsp?page=/sport/content.jsp").forward(request, response);

	}
}
