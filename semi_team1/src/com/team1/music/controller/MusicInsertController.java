package com.team1.music.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.MusicDao;
import com.team1.vo.MusicVo;

@WebServlet("/music/insert")
public class MusicInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title=request.getParameter("title");
		String singer=request.getParameter("singer");
		String lyrics=request.getParameter("lyrics");
		String songwriter=request.getParameter("songwriter");
		String lyricist=request.getParameter("lyricist");
		String music_img=request.getParameter("music_img");
		
		MusicVo vo=new MusicVo(0, title, null, singer, lyrics, songwriter, lyricist);
		vo.setOrgmimg(music_img);
		vo.setSavemimg(music_img);
		
		MusicDao dao=new MusicDao();
		int n=dao.insert(vo);
		
		if(n>0){
			RequestDispatcher rd = request.getRequestDispatcher("/gallerylist");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("join/result.jsp");
			rd.forward(request, response);
		}
	}
}
