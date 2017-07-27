package com.team1.message2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.message2.dao.recvDao2;
import com.team1.vo.Message2Vo;

@WebServlet("/recvview2")
public class RecvViewController2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		recvDao2 dao = new recvDao2();
		Message2Vo vo = dao.select(num);
		if (vo != null) {
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/index.jsp?page=message2/recvview2.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
		}
}
