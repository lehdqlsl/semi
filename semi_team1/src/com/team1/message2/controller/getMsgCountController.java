package com.team1.message2.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.message2.dao.recvDao2;
import com.team1.vo.Message2Vo;


@WebServlet("/getmsgcount")
public class getMsgCountController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String receiver=request.getParameter("receiver");
		recvDao2 dao=new recvDao2();
		int vo=dao.getMsgCount(receiver);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
		
	}
}
