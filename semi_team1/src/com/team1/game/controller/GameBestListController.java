package com.team1.game.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.team1.dao.BoardDao;
import com.team1.vo.boardListVo;
@WebServlet("/game/bestList")
public class GameBestListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int snum=Integer.parseInt(request.getParameter("snum"));
		System.out.println("snum:" + snum);
		BoardDao boardDao=new BoardDao(); // best게시글 Dao
		ArrayList<boardListVo> bestList = null;
		JSONArray jarr=new JSONArray();
		
		if(snum==1){
			bestList= boardDao.bestlist(1,10);
		}else if(snum==2){
			bestList= boardDao.bestlist(2,10);
		}else if(snum==3){
			bestList= boardDao.bestlist(3,10);
		}else if(snum==4){
			bestList= boardDao.bestlist(4,10);
		}
		
		for(int i=0;i<bestList.size();i++){
			boardListVo vo=bestList.get(i);
			JSONObject obj=new JSONObject();
			obj.put("num",vo.getNum());
			obj.put("title_name",vo.getTitle_name());
			jarr.add(obj);
		}
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.print(jarr.toString());
		pw.close();
		
		// ajax 이용 코딩
//		if(bestList!=null){
//			request.setAttribute("bestList", bestList);
//			request.getRequestDispatcher("/index.jsp?page=game/content.jsp").forward(request, response);
//		} else {
//			response.sendRedirect("/fail.jsp");
//		}
	}
}
