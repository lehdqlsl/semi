package com.team1.game.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.vo.boardListVo;
@WebServlet("/game/bestList")
public class GameBestListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int snum=Integer.parseInt(request.getParameter("snum"));
		
		BoardDao boardDao=new BoardDao(); // best게시글 Dao
		ArrayList<boardListVo> bestList = null;
		
		if(snum==1){
			bestList= boardDao.bestlist(1);
		}else if(snum==2){
			bestList= boardDao.bestlist(1);
		}else if(snum==3){
			bestList= boardDao.bestlist(1);
		}else if(snum==4){
			bestList= boardDao.bestlist(1);
		}
		
		// ajax 이용 코딩
		if(bestList!=null){
			request.setAttribute("bestList", bestList);
			
		}
	}
}
