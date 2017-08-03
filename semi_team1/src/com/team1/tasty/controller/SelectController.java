package com.team1.tasty.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;
import com.team1.dao.BoardDao;
import com.team1.dao.BoardTastyDao;
import com.team1.dao.JoinDao;
import com.team1.dao.ReplyDao;
import com.team1.vo.BoardTastyVo;
import com.team1.vo.JoinVo;
import com.team1.vo.ReplyVo;
import com.team1.vo.boardVo;

@WebServlet("/tasty/select")
public class SelectController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");
		int b_num = Integer.parseInt(request.getParameter("num"));

		BoardTastyDao dao = new BoardTastyDao();
		String writer = dao.getWriter(b_num);
		JoinDao memdao = new JoinDao();
		JoinVo mvo = memdao.memSelect(writer);
		BoardTastyVo vo = dao.select(b_num);
		dao.hitupdate(b_num);

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

		if (vo != null) {
			request.setAttribute("vo", vo);
			System.out.println(vo.toString());
			request.setAttribute("mvo", mvo);
			request.getRequestDispatcher("/index.jsp?page=/tasty/tastyIndex.jsp&s_page=/tasty/select.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
