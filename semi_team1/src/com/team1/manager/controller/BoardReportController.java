package com.team1.manager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.vo.ManagerVo;
import com.team1.vo.boardVo;

@WebServlet("/report/list")
public class BoardReportController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");

		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 10 + 1;
		int endRow = startRow + 9;

		BoardDao dao = new BoardDao();
		ArrayList<ManagerVo> list = dao.boardReport(startRow, endRow);
		// ��ü ������ ���� ���ϱ�
		int pageCount = (int) Math.ceil(dao.getCnt() / 10.0);
		// ���� ������ ���ϱ�
		int startPageNum = ((int) Math.ceil(pageNum / 10.0)) * 10 - 9;
		// �������� ���ϱ�
		int endPageNum = ((int) Math.ceil(pageNum / 10.0)) * 10;

		if (endPageNum > pageCount) {
			endPageNum = pageCount;
		}

		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPageNum);
		request.setAttribute("endPage", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/index.jsp?page=/manager/managerIndex.jsp&s_page=/manager/boardreportlist.jsp").forward(request, response);
	}
}
