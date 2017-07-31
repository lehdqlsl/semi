package com.team1.replycontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.dao.ReplyDao;

@WebServlet("/reply/report/update")
public class ReportUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int r_num=Integer.parseInt(request.getParameter("r_num"));
		
		int b_num=Integer.parseInt(request.getParameter("b_num"));
		String writer=request.getParameter("writer");
		
		ReplyDao dao=ReplyDao.getInstance();
		int n=dao.reportUpdate(r_num);
		
		if(n>0){
			response.sendRedirect("/semi_team1/select?num="+b_num+"&writer="+writer);
		}else{
			request.setAttribute("result", "fail");
			RequestDispatcher rd=request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
}
