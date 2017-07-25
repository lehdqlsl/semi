package com.team1.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.vo.boardVo;

@WebServlet("/list")
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

		int pageNum = 1;// 1페이지가 기본값
		// pageNum이 들어올때가 있고 안들어올 때가 있으므로 if문 처리(익셉션)
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);// 페이지 번호가 들어오면 pageNum에 저장.
		}

		/*
		 * 시작 행 자신의 페이지 * 5 - 4 끝 행 : 자신의 페이지 * 5
		 * 
		 * 1페이지 1~5 2페이지 6~10 3페이지 11~15
		 */
		// --------------- 선택한 페이지에 출력할 게시글 행의 개수-------------------
		int startRow = (pageNum * 5) - 4;// 시작행 번호
		int endRow = (pageNum * 5);// 끝행 번호

		BoardDao dao = new BoardDao();
		ArrayList<boardVo> list = dao.list(startRow, endRow, search, keyword);
		System.out.print(list.size());
		if (list != null) {

			// 페이지 갯수 구하기 (전체글의 갯수/한 페이지 행의 총 갯수) Math.ceil로 소수 무조건 올림처리
			int pageCount = (int) (Math.ceil(dao.getCount(search, keyword) / 5.0));

			// 시작페이지번호 구하기
			int startPageNum = ((pageNum - 1) / 4 * 4) + 1; // 1페이지일 경우 1, 2페이지일
															// 경우 2, ... ,11페이지일
															// 경우 11

			// 끝페이지 번호 구하기
			int endPageNum = startPageNum + 3; // 1페이지일 경우 10, 11페이지일 경우 20
			if (endPageNum > pageCount) {// ex)(마지막 페이지가) > (전체 페이지 갯수) 보다 크다면
				endPageNum = pageCount;// 전체 페이지의 갯수를 넣어주면됨.
			}
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);

			request.setAttribute("list", list);

			request.getRequestDispatcher("/index.jsp?page=/board/list.jsp").forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
