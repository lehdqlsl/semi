package com.team1.replycontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.ReplyDao;
@WebServlet("/reply/delete")
public class DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int r_num=Integer.parseInt(request.getParameter("r_num"));
		int b_num=Integer.parseInt(request.getParameter("b_num"));
		String nick=request.getParameter("nick");
		String sessionNick=request.getParameter("sessionNick");
		int flag=0;
		if(nick.equals(sessionNick) || sessionNick.equals("admin")){
			ReplyDao dao=ReplyDao.getInstance();
			int n=dao.delete(r_num,b_num,nick);
	
		}else{
			flag=1;
		}
		
		response.setContentType("text/xml;charset=utf-8"	);
		PrintWriter pw=response.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<data>");
		pw.println("<flag>" + flag + "</flag>");
		pw.println("</data>");
		pw.close();

	}
}
