package com.team1.replycontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.dao.CategoryDao;
import com.team1.dao.ReplyDao;

@WebServlet("/reply/insert")
public class InsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();

		String content = request.getParameter("content");
		String m_nick = (String) session.getAttribute("m_nick");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		ReplyDao dao = ReplyDao.getInstance();
		int n = dao.insert(m_nick, content, b_num);
		String str = request.getHeader("referer");
		if (n > 0) {
			request.setAttribute("num", b_num);
			response.sendRedirect(str);

		} else {
			System.out.println("댓글등록 실패");
		}
	}
}
