package com.team1.join;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.JoinDao;
import com.team1.vo.JoinVo;

@WebServlet("/join/insert")
public class JoinController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String u_pw = request.getParameter("u_pw");
		String m_nick = request.getParameter("m_nick");
		String m_mail = request.getParameter("m_mail");
		String img = request.getParameter("m_img");

		JoinVo vo = new JoinVo(0, id, u_pw, m_nick, m_mail);
		vo.setM_orgfilename(img);
		vo.setM_savefilename(img);

		JoinDao dao = new JoinDao();
		int n = dao.insert(vo);

		if (n > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?page=login/signin.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("join/result.jsp");
			rd.forward(request, response);
		}
	}
}
