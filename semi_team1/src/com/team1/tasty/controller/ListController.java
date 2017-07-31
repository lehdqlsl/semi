package com.team1.tasty.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.BoardTastyDao;
import com.team1.dao.CategoryDao;
import com.team1.dao.ReplyDao;
import com.team1.dao.UserInfoDao;
import com.team1.vo.BoardTastyVo;
import com.team1.vo.ReplyVo;
import com.team1.vo.boardListVo;
import com.team1.vo.boardVo;

@WebServlet("/tasty/list")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");

		if (keyword == null || keyword.equals("")) {
			keyword = "";
			search = "";
		}

		String spageNum = request.getParameter("pageNum");
		int s_num = Integer.parseInt(request.getParameter("s_num"));

		int pageNum = 1;// 1�������� �⺻��
		// pageNum�� ���ö��� �ְ� �ȵ��� ���� �����Ƿ� if�� ó��(�ͼ���)
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);// ������ ��ȣ�� ������ pageNum�� ����.
		}

		/*
		 * ���� �� �ڽ��� ������ * 5 - 4 �� �� : �ڽ��� ������ * 5
		 * 
		 * 1������ 1~5 2������ 6~10 3������ 11~15
		 */
		// --------------- ������ �������� ����� �Խñ� ���� ����-------------------
		int startRow = (pageNum * 20) - 19;// ������ ��ȣ
		int endRow = (pageNum * 20);// ���� ��ȣ

		BoardTastyDao dao = new BoardTastyDao();
		ArrayList<BoardTastyVo> list = dao.list(s_num, startRow, endRow, search, keyword);

		if (list != null) {

			// ������ ���� ���ϱ� (��ü���� ����/�� ������ ���� �� ����) Math.ceil�� �Ҽ� ������ �ø�ó��
			int pageCount = (int) (Math.ceil(dao.getCount(s_num, search, keyword) / 20.0));

			// ������������ȣ ���ϱ�
			int startPageNum = (int) (Math.ceil(pageNum / 10.0) * 10 - 9); // 1��������
																			// ���
																			// 1,
																			// 2��������
			// ��� 2, ... ,11��������
			// ��� 11

			// �������� ��ȣ ���ϱ�
			int endPageNum = (int) (Math.ceil(pageNum / 10.0) * 10); // 1�������� ���
																		// 10,
																		// 11��������
																		// ��� 20
			if (endPageNum > pageCount) {// ex)(������ ��������) > (��ü ������ ����) ���� ũ�ٸ�
				endPageNum = pageCount;// ��ü �������� ������ �־��ָ��.
			}

			CategoryDao c_dao = new CategoryDao();

			int n = c_dao.isMember(s_num);

			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("list", list);
			request.setAttribute("s_num", s_num);
			request.getRequestDispatcher("/index.jsp?page=/tasty/tastyIndex.jsp&s_page=/tasty/list.jsp")
					.forward(request, response);

		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
