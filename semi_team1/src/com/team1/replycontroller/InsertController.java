package com.team1.replycontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.ReplyDao;

@WebServlet("/reply.insert")
public class InsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String content=request.getParameter("content");
		int b_num=Integer.parseInt(request.getParameter("b_num"));
		ReplyDao dao=ReplyDao.getInstance();
		int n=dao.insert(content, b_num);
		
		if(n>0){
			request.setAttribute("b_num", b_num);
			response.sendRedirect("/semi_team1/reply.list");
		}else{
			System.out.println("댓글등록 실패");		
		}
	}
}
