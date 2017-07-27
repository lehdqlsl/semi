package com.team1.message2.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.message2.dao.recvDao2;
import com.team1.vo.Message2Vo;

@WebServlet("/recvlist2")
public class RecvListContorller2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}

		int startRow = (pageNum * 20) - 19;// ������ ��ȣ
		int endRow = (pageNum * 20);// ���� ��ȣ

		recvDao2 dao = new recvDao2();
		
		HttpSession session=request.getSession();
		String m_nick=(String) session.getAttribute("m_nick");
		ArrayList<Message2Vo> list = dao.list(m_nick, startRow, endRow);

		if (list != null) {
			int pageCount = (int) (Math.ceil(dao.getCount() / 20.0));
			int startPageNum = (int) (Math.ceil(pageNum / 10.0) * 10 - 9);
			int endPageNum = (int) (Math.ceil(pageNum / 10.0) * 10);
			if (endPageNum > pageCount) {
				endPageNum = pageCount;
			}
			request.setAttribute("list", list);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.getRequestDispatcher("/index.jsp?page=message2/recvlist2.jsp").forward(request,
					response);

		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}