package com.team1.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.CategoryDao;
import com.team1.dao.ReplyDao;
import com.team1.dao.UserInfoDao;
import com.team1.vo.ReplyVo;
import com.team1.vo.boardListVo;
import com.team1.vo.boardVo;

@WebServlet("/list")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");
		BoardDao dao = new BoardDao();
		if (keyword == null || keyword.equals("")) {
			keyword = "";
			search = "";
		}

		// 글에 해당하는 댓글 개수 가져오기
		ReplyDao rdao = ReplyDao.getInstance();

		String spageNum = request.getParameter("pageNum");
		int s_num = Integer.parseInt(request.getParameter("s_num"));

		int pageNum = 1;// 1페이지가 기본값
		// pageNum이 들어올때가 있고 안들어올 때가 있으므로 if문 처리(익셉션)
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);// 페이지 번호가 들어오면 pageNum에 저장.
		}

		// 공지사항 가져오기
		ArrayList<boardListVo> noticelist = dao.noticelist(s_num);
		int notice_length = noticelist.size();
		for (boardListVo vo : noticelist) {
			int rcnt = rdao.getCount(vo.getNum());
			vo.setCnt(rcnt);
		}
		/*
		 * 시작 행 자신의 페이지 * 5 - 4 끝 행 : 자신의 페이지 * 5
		 * 
		 * 1페이지 1~5 2페이지 6~10 3페이지 11~15
		 */
		// --------------- 선택한 페이지에 출력할 게시글 행의 개수-------------------
		int startRow = (pageNum * 20) - 19;// 시작행 번호
		int endRow = (pageNum * 20) - notice_length;// 끝행 번호

		ArrayList<boardListVo> list = dao.list(s_num, startRow, endRow, search, keyword);

		for (boardListVo vo : list) {
			int rcnt = rdao.getCount(vo.getNum());
			vo.setCnt(rcnt);
		}

		if (list != null) {

			// 페이지 갯수 구하기 (전체글의 갯수/한 페이지 행의 총 갯수) Math.ceil로 소수 무조건 올림처리
			int pageCount = (int) (Math.ceil(dao.getCount(s_num, search, keyword) / (double) 20.0));

			// 시작페이지번호 구하기
			int startPageNum = (int) (Math.ceil(pageNum / 10.0) * 10 - 9); // 1페이지일
																			// 경우
																			// 1,
																			// 2페이지일
			// 경우 2, ... ,11페이지일
			// 경우 11

			// 끝페이지 번호 구하기
			int endPageNum = (int) (Math.ceil(pageNum / 10.0) * 10); // 1페이지일 경우
																		// 10,
																		// 11페이지일
																		// 경우 20
			if (endPageNum > pageCount) {// ex)(마지막 페이지가) > (전체 페이지 갯수) 보다 크다면
				endPageNum = pageCount;// 전체 페이지의 갯수를 넣어주면됨.
			}

			CategoryDao c_dao = new CategoryDao();
			UserInfoDao dao1 = new UserInfoDao();

			int n = c_dao.isMember(s_num);

			for (boardListVo vo : list) {
				String writer = dao1.getGrade(vo.getWriter());
				vo.setGrade(writer);
			}

			// 베스트게시글
			ArrayList<boardListVo> bestlist = dao.bestlist(s_num);
			
			// 카테고리 타이틀
			String title = c_dao.getTitle(s_num);

			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("list", list);
			request.setAttribute("bestlist", bestlist);
			
			request.setAttribute("noticelist", noticelist);
			request.setAttribute("s_num", s_num);
			request.setAttribute("title", title);

			switch (n) {
			case 1:
				request.getRequestDispatcher("/index.jsp?page=/game/gameIndex.jsp&s_page=/board/list.jsp")
						.forward(request, response);
				break;
			case 2:
				request.getRequestDispatcher("/index.jsp?page=/movie/movieIndex.jsp&s_page=/board/list.jsp")
						.forward(request, response);
				break;
			case 3:
				request.getRequestDispatcher("/index.jsp?page=/sport/sportIndex.jsp&s_page=/board/list.jsp")
						.forward(request, response);
				break;
			case 4:
				request.getRequestDispatcher("/index.jsp?page=/tasty/tastyIndex.jsp&s_page=/board/list.jsp")
						.forward(request, response);
				break;
			case 5:
				request.getRequestDispatcher("/index.jsp?page=/music/musicIndex.jsp&s_page=/board/list.jsp")
						.forward(request, response);
				break;
			}

		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
