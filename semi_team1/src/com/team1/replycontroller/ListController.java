package com.team1.replycontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.team1.dao.ReplyDao;
import com.team1.vo.ReplyVo;

@WebServlet("/reply/list")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String spageNum=request.getParameter("pageNum");
		// 임시로 b_num(게시판번호) 1로 받아옴 -> 수정필요
		int b_num=Integer.parseInt(request.getParameter("b_num"));
		System.out.println(b_num);
		
		int pageNum=1;
		if(spageNum!=null){
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		
		ReplyDao dao = ReplyDao.getInstance();
		ArrayList<ReplyVo> list = dao.replyList(b_num,startRow, endRow);
		int cntTot=dao.getCount(b_num);
		
		int pageCount=(int)Math.ceil(dao.getCount(b_num)/10.0);
		int startPageNum=((pageNum-1)/10)*10+1;
		
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount){
			endPageNum=pageCount;
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPageNum);
		request.setAttribute("endPage", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("list", list);
		request.setAttribute("cntTot", cntTot);
		request.getRequestDispatcher("/reply/reply.jsp").forward(request, response);
	}
}
