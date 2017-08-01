package com.team1.manager.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.JoinDao;
import com.team1.vo.JoinVo;

@WebServlet("/select/limit/mem")
public class SelectLimitMem extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String writer=request.getParameter("writer");
		JoinDao dao=new JoinDao();
		JoinVo vo=dao.memSelect(writer);
		
		if(vo!=null){
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/index.jsp?page=/manager/managerIndex.jsp&s_page=/manager/selectlimitmem.jsp").forward(request, response);
		}else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
