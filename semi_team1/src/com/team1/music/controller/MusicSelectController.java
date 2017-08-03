package com.team1.music.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.MusicDao;
import com.team1.vo.MusicVo;

@WebServlet("/musicSelect")
public class MusicSelectController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("¹øÈ£ : "+num);
		MusicDao dao=new MusicDao();
		MusicVo vo=dao.select(num);
		if(vo!=null){
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/index.jsp?page=music/musicSelect.jsp")
					.forward(request, response);
		}else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
