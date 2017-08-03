package com.team1.game.controller;

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
import com.team1.dao.GameDao;
import com.team1.vo.GameVo;

@WebServlet("/game/insert")
public class GameInsertController extends HttpServlet {
 @Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
	
	 	request.setCharacterEncoding("utf-8");
		String g_name= request.getParameter("g_name");
		String g_jenre = request.getParameter("g_jenre");
		String flatform = request.getParameter("flatform");
		String company= request.getParameter("company");
		String l_date = request.getParameter("l_date");
		String g_img = request.getParameter("g_img");
		GameVo vo = new GameVo(0, g_name, g_jenre, flatform, company, l_date);
		vo.setOrgImg(g_img);
		vo.setSaveImg(g_img);

		GameDao dao = new GameDao();
		int n = dao.insert(vo);

		if (n > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?page=game/gameInfo.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("join/result.jsp");
			rd.forward(request, response);
		}
	}
 }
