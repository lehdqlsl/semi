package com.team1.join;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.JoinDao;
import com.team1.vo.JoinVo;

//managerlist
@WebServlet("/list.do")
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null){
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*20+1;
		int endRow=startRow+19;
		//endRow=pageNum*10;
		//startRow=endRow-9;
		
		JoinDao dao=new JoinDao();
		ArrayList<JoinVo> list=dao.list(startRow, endRow);
		//전체 페이지 개수 구하기
		int pageCount=(int)Math.ceil(dao.getCount()/20.0);
		//시작 페이지 구하기
		int startPageNum=((int)Math.ceil(pageNum/5.0))*5-4;
		//끝페이지 구하기
		int endPageNum=((int)Math.ceil(pageNum/5.0))*5;
		if(endPageNum>pageCount){
			endPageNum=pageCount;
		}
		System.out.println(startPageNum);
		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPageNum);
		request.setAttribute("endPage", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/manager/memlist.jsp").forward(request, response);
	}
}
