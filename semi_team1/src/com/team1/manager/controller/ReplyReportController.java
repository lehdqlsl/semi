package com.team1.manager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.ReplyDao;
import com.team1.vo.ReplyVo;
@WebServlet("/reportlist.do")
public class ReplyReportController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null){
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;

		ReplyDao dao=ReplyDao.getInstance();
		ArrayList<ReplyVo> list=dao.replyReport(startRow, endRow);
		//전체 페이지 개수 구하기
		int pageCount=(int)Math.ceil(dao.getCnt()/10.0);
		//시작 페이지 구하기
		int startPageNum=((int)Math.ceil(pageNum/10.0))*10-9;
		//끝페이지 구하기
		int endPageNum=((int)Math.ceil(pageNum/10.0))*10;
		if(endPageNum>pageCount){
			endPageNum=pageCount;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPageNum);
		request.setAttribute("endPage", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/manager/reportlist.jsp").forward(request, response);
	}
}
