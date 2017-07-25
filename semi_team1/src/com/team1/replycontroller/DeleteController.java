package com.team1.replycontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.ReplyDao;
@WebServlet("/reply.delete")
public class DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_num=Integer.parseInt(request.getParameter("r_num"));
		int b_num=Integer.parseInt(request.getParameter("b_num"));
		System.out.println("r_num:" +r_num);
		System.out.println("b_num:" +b_num);
		ReplyDao dao=ReplyDao.getInstance();
		int n=dao.delete(r_num,b_num);
		
		if(n>0){
			request.setAttribute("b_num", b_num);
			response.sendRedirect("/semi_team1/reply.list");
		}else{
			System.out.println("댓글삭제 실패");		
		}
	}
}
