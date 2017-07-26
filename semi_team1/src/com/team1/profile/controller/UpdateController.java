package com.team1.profile.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.UserInfoDao;
import com.team1.vo.ProfileVo;

@WebServlet("/nickupdate")
public class UpdateController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String m_nick = request.getParameter("m_nick");

		ProfileVo vo = new ProfileVo(num, m_nick);
		UserInfoDao dao = new UserInfoDao();
		int n = dao.update(vo);
		System.out.println("n��" + n);
		if (n > 0) {
			response.sendRedirect("/semi_team1/index.jsp?page=profile/userinfo.jsp?num=" + num);
		} else {
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/users/result.jsp").forward(request, response);
		}
	}
}
