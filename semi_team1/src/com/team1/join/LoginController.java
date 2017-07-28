package com.team1.join;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.dao.JoinDao;

import com.team1.vo.JoinVo;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if (cmd.equals("login")) {
			login(request, response);
		} else if (cmd.equals("logout")) {
			logout(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		// 로그아웃된 다음에 이동할 페이지
		response.sendRedirect("index.jsp?page=login/signin.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String u_pw = request.getParameter("u_pw");
		// String m_nick=request.getParameter("m_nick");
		String box = request.getParameter("box");
		String url = request.getParameter("prev");

		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("u_pw", u_pw);
		JoinDao dao = new JoinDao();
		JoinVo vo = dao.isMember(map);

		// boolean result=dao.isMember(map);

		if (vo != null) {
			if (box != null) {
				// 쿠키 생성
				Cookie cook1 = new Cookie("id", map.get("id"));
				cook1.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(cook1);

				Cookie cook2 = new Cookie("u_pw", map.get("u_pw"));
				cook2.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(cook2);

				System.out.println(id);
				System.out.println(u_pw);
			} else {
				Cookie cook1 = new Cookie("id", map.get("id"));
				cook1.setMaxAge(0);
				response.addCookie(cook1);

				Cookie cook2 = new Cookie("u_pw", map.get("u_pw"));
				cook2.setMaxAge(0);
				response.addCookie(cook2);
			}

			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("m_nick", vo.getM_nick());
			session.setAttribute("num", vo.getNum());

			response.sendRedirect("/semi_team1/index.jsp?page=content.jsp");
		} else {
			request.setAttribute("errMsg", "아이디 또는 비밀번호가 틀립니다");
			request.getRequestDispatcher("index.jsp?page=login/signin.jsp").forward(request, response);
		}
	}
}
