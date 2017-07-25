package com.team1.profile.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.ProfileDao;
import com.team1.vo.ProfileVo;




@WebServlet("/updateprofile")
public class UpdateController extends HttpServlet {
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		String u_pw=request.getParameter("u_pw");
		String m_nick=request.getParameter("m_nick");
		String m_mail=request.getParameter("m_mail");
		
		ProfileVo vo=new ProfileVo(num, u_pw, m_nick, m_mail);
		ProfileDao dao=new ProfileDao();
		int n=dao.update(vo);
		
		if(n>0){
			response.sendRedirect("myusers.do?cmd=list");
		}else{
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/users/result.jsp").forward(request, response);
		}
	}
	
}
