package com.team1.profile.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.ProfileDao;
import com.team1.vo.ProfileVo;

@WebServlet("/profileselect")
public class ProfileSelectController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		ProfileDao dao = new ProfileDao();
		ProfileVo vo = dao.select(num);
		if (vo != null) {
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("index.jsp?page=profile/update.jsp").forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
