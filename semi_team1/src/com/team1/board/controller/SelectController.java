package com.team1.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.CategoryDao;
import com.team1.dao.JoinDao;
import com.team1.dao.ReplyDao;
import com.team1.vo.JoinVo;
import com.team1.vo.ReplyVo;
import com.team1.vo.boardVo;

@WebServlet("/select")
public class SelectController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		CategoryDao c_dao = new CategoryDao();

		boolean flag = true;

		String spageNum = request.getParameter("pageNum");
		int b_num = Integer.parseInt(request.getParameter("num"));
		String nick = dao.getWriter(b_num);
		String bnum = request.getParameter("num");

		JoinDao memdao = new JoinDao();
		JoinVo mvo = memdao.memSelect(nick);
		boardVo vo = dao.select(b_num);

		//////////////////////////////////////////////////////////////////////////////////
		// 조회수 쿠키
		Cookie[] cooks = request.getCookies();
		String num = String.valueOf(bnum);

		for (Cookie cook : cooks) {
			String name = cook.getName();
			if (name.equals(num)) {
				flag = false;
				break;
			}
		}

		if (flag) {
			Cookie cook = new Cookie(num, num);
			cook.setMaxAge(60); // 60초*30=30분
			cook.setPath("/");
			response.addCookie(cook);
		}

		///////////////////////////////////////////////////////////////////////////////////

		// dao.hitupdate(b_num);
		if (flag) {
			dao.hitupdate(b_num);
		}

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
		request.setAttribute("b_num", request.getParameter("num"));
		///////////////////
		int f_num = c_dao.f_num(b_num);
		if (vo != null) {
			request.setAttribute("vo", vo);
			request.setAttribute("mvo", mvo);

			switch (f_num) {
			case 1:
				request.getRequestDispatcher("/index.jsp?page=/game/gameIndex.jsp&s_page=/board/select.jsp")
						.forward(request, response);
				break;
			case 2:
				request.getRequestDispatcher("/index.jsp?page=/movie/movieIndex.jsp&s_page=/board/select.jsp")
						.forward(request, response);
				break;
			case 3:
				request.getRequestDispatcher("/index.jsp?page=/sport/sportIndex.jsp&s_page=/board/select.jsp")
						.forward(request, response);
				break;
			case 4:
				request.getRequestDispatcher("/index.jsp?page=/tasty/tastyIndex.jsp&s_page=/board/select.jsp")
						.forward(request, response);
				break;
			case 5:
				request.getRequestDispatcher("/index.jsp?page=/music/musicIndex.jsp&s_page=/board/select.jsp")
						.forward(request, response);
				break;
			}

		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
