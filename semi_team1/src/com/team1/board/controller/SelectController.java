package com.team1.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.vo.boardVo;


@WebServlet("/getInfo")
public class SelectController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDao dao = new BoardDao();
		boardVo vo = dao.select(num);
		if(vo!=null){
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/info.jsp").forward(request, response);
		}else{
			response.sendRedirect("/fail.jsp");
		}
	}
}
