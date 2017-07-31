package com.team1.replycontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.ReplyDao;
import com.team1.vo.ReplyVo;

@WebServlet("/myreplylist")
public class MyReplyListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");
		String nick = request.getParameter("nick");
		// int s_num=Integer.parseInt(request.getParameter("s_num"));

		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 20 + 1;
		int endRow = startRow + 19;

		ReplyDao dao = ReplyDao.getInstance();
		ArrayList<ReplyVo> list = dao.MyReplyList(nick, startRow, endRow);

		if (list != null) {
			int pageCount = (int) (Math.ceil(dao.getCount2() / 20.0));
			int startPageNum = (int) (Math.ceil(pageNum / 20.0) * 20 - 19);
			int endPageNum = (int) (Math.ceil(pageNum / 20.0) * 20);
			if (endPageNum > pageCount) {
				endPageNum = pageCount;
			}

			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("list", list);
			
			request.setAttribute("nick", nick);
			request.getRequestDispatcher("/index.jsp?page=/reply/myreplylist.jsp").forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
