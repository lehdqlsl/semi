package com.team1.profile.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.dao.UserInfoDao;
import com.team1.vo.ProfileVo;

//닉네임 업데이트(삭제금지)
@WebServlet("/nickupdate")
public class NickUpdateController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String m_nick = request.getParameter("m_nick");
		HttpSession session = request.getSession();
		session.setAttribute("m_nick", m_nick);
		ProfileVo vo = new ProfileVo(num, m_nick);
		UserInfoDao dao = new UserInfoDao();
		int n = dao.update(vo);

		if (n > 0) {
			response.sendRedirect("/semi_team1/index.jsp?page=profile/userinfo.jsp?num=" + num);
		} else {
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/users/result.jsp").forward(request, response);
		}
	}
}
