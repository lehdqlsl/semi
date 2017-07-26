package com.team1.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.ReplyDao;
import com.team1.vo.ReplyVo;
import com.team1.vo.boardVo;

@WebServlet("/select")
public class SelectController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int b_num = Integer.parseInt(request.getParameter("num"));
		BoardDao dao = new BoardDao();
		boardVo vo = dao.select(b_num);

		// 댓글 가져오기
		String spageNum = request.getParameter("r_pageNum");

		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 10 + 1;
		int endRow = startRow + 9;

		ReplyDao rdao = ReplyDao.getInstance();
		ArrayList<ReplyVo> list = rdao.replyList(b_num, startRow, endRow);
		int cntTot = rdao.getCount(b_num);

		int pageCount = (int) Math.ceil(rdao.getCount(b_num) / 10.0);
		int startPageNum = ((pageNum - 1) / 10) * 10 + 1;

		int endPageNum = startPageNum + 9;
		if (endPageNum > pageCount) {
			endPageNum = pageCount;
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPageNum);
		request.setAttribute("endPage", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("list", list);
		request.setAttribute("cntTot", cntTot);
		///////////////////

		if (vo != null) {
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/index.jsp?page=/game/gameIndex.jsp&s_page=/board/select.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
