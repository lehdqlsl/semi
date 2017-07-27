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

@WebServlet("/recvdelete2")
public class RecvDelController2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		Message2Vo vo = new Message2Vo();
		vo.setNum(num);
		recvDao2 dao = new recvDao2();
		int n = dao.delete(vo);

		if (n > 0) {
			response.sendRedirect("/semi_team1/recvlist2");
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}

	
	}
}
