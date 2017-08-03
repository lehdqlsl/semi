package com.team1.movie.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.JoinDao;
import com.team1.dao.M_ReviewDao;
import com.team1.dao.MovieDao;
import com.team1.vo.M_ReviewVo;
import com.team1.vo.MovieVo;

@WebServlet("/movieselect")
public class MovieSelectController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		MovieDao dao = new MovieDao();
		MovieVo vo = dao.select(m_num);
		
		
		M_ReviewDao mrdao=new M_ReviewDao();
		JoinDao memdao = new JoinDao();
		
		double cntAvg=mrdao.getReviewAvg(m_num);
		
		String spageNum = request.getParameter("pageNum");
		
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 10 + 1;
		int endRow = startRow + 9;
		ArrayList<M_ReviewVo> list = mrdao.mReviewList(m_num, startRow, endRow);
		int cntTot = mrdao.getCnt(m_num);

		int pageCount = (int) Math.ceil(mrdao.getCnt(m_num) / 10.0);
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
		request.setAttribute("cntAvg", cntAvg);
		request.setAttribute("b_num", request.getParameter("num"));
		
		if(vo!=null){
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/index.jsp?page=/movie/select.jsp").forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}	
	}
}
