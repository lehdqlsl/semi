package com.team1.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.team1.dao.BoardDao;
import com.team1.vo.boardVo;

@WebServlet("/update")
public class UpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		int num=Integer.parseInt(request.getParameter("num"));
		String title_name=request.getParameter("title_name");
		String content=request.getParameter("content");
		//int f_num=Integer.parseInt(request.getParameter("f_num"));
		int s_num=Integer.parseInt(request.getParameter("s_num"));
		
		System.out.println("num"+num);
		System.out.println("title_name"+title_name);
		System.out.println("content"+content);
		//System.out.println("f_num"+f_num);
		System.out.println("s_num"+s_num);
		
		
		
		boardVo vo=new boardVo(num, title_name, content, s_num);
		BoardDao dao=new BoardDao();
		int n=dao.update(vo);
		
		System.out.println("n°ª"+n);
		
		if(n>0){
			response.sendRedirect("/semi_team1/list?s_num=" + s_num);
		}else{
			request.setAttribute("result", "fail");
			RequestDispatcher rd=request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
		

	}
}
