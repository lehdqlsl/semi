package com.team1.movie.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team1.dao.MovieDao;
import com.team1.vo.MovieVo;

@WebServlet("/movieinsert")
public class MovieInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath=getServletContext().getRealPath("/upload");
		MultipartRequest mr=new MultipartRequest(
			request,			
			uploadPath,
			1024*1024*10,
			"utf-8",
			new DefaultFileRenamePolicy()
		);
		String m_name=mr.getParameter("m_name");
		String m_genre=mr.getParameter("m_genre");
		String m_country=mr.getParameter("m_country");
		String m_rt=mr.getParameter("m_rt");
		String m_release=mr.getParameter("m_release");
		String m_rate=mr.getParameter("m_rate");
		String m_director=mr.getParameter("m_director");
		String m_actor=mr.getParameter("m_actor");
		
		String story=mr.getParameter("story");
		String link=mr.getParameter("link");
		
		//전송된 파일명
		String orgimg=mr.getOriginalFileName("file1");
		//실제 저장된 파일명
		String saveimg=mr.getFilesystemName("file1");
		
		//////////////////// DB에 저장하기 ////////////////////
		File f=mr.getFile("file1");	//파일크기를 구하기 위해 File객체 얻어오기
		MovieVo vo=new MovieVo(0, m_name, m_genre, m_country, m_rt, m_release, m_rate, m_director, m_actor, orgimg, saveimg, 0, story, link);
		MovieDao dao=new MovieDao();
		int n=dao.insert(vo);
		if(n>0){
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?page=movielist");
			rd.forward(request, response);
			
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("join/result.jsp");
			rd.forward(request, response);
		}
		
		
		
	}
}
