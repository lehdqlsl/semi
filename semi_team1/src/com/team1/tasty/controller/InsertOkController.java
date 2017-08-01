package com.team1.tasty.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team1.dao.BoardDao;
import com.team1.dao.BoardTastyDao;
import com.team1.vo.BoardTastyVo;
import com.team1.vo.boardVo;

@WebServlet("/tasty/insert")
public class InsertOkController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title_name");
		String content = request.getParameter("content");

		String addr = request.getParameter("addr");
		String map = request.getParameter("map");

		int s_num = Integer.parseInt(request.getParameter("s_num"));

		BoardTastyDao dao = new BoardTastyDao();
		BoardTastyVo vo = new BoardTastyVo(0, title, 0, 0, content, null, writer, s_num, 0, 0, 0, addr, map);

		int n = dao.insert(vo);

		if (n > 0) {
			response.sendRedirect("/semi_team1/tasty/list?s_num=" + s_num);
		} else {
			request.setAttribute("result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/join/result.jsp");
			rd.forward(request, response);
		}
	}
	/*
	 * request.setCharacterEncoding("utf-8");
	 * 
	 * 
	 * String uploadPath=getServletContext().getRealPath("/upload");
	 * MultipartRequest mr=new MultipartRequest( request, uploadPath,
	 * 1024*1024*5, "utf-8", new DefaultFileRenamePolicy() ); //String
	 * snum=request.getParameter("num"); String
	 * writer=request.getParameter("writer"); String
	 * title_name=request.getParameter("title_name"); String
	 * content=request.getParameter("content");
	 * 
	 * Enumeration<String> em=mr.getFileNames(); BoardDao dao=new BoardDao(); //
	 * while(em.hasMoreElements()){ String fname=em.nextElement(); String
	 * orgFileName=mr.getOriginalFileName(fname); String
	 * saveFileName=mr.getFilesystemName(fname);
	 * 
	 * File f=new File(uploadPath + "\\" + saveFileName); boardVo vo=new
	 * boardVo(0, title_name, 0, 0, orgFileName, saveFileName, content, null,
	 * writer, 0, 0, 0, 0, 0); int n=dao.insert(vo); // System.out.println("°á°ú:"
	 * + n); } }
	 * 
	 */
}
