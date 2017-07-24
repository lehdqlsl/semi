package com.team1.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.vo.boardVo;


@WebServlet("/list")
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");
		
		
		if(keyword==null||keyword.equals("")){
			keyword = "";
			search = "";
		}
		
		String spageNum = request.getParameter("pageNum");
		
		int pageNum = 1;//1�������� �⺻��
		//pageNum�� ���ö��� �ְ� �ȵ��� ���� �����Ƿ� if�� ó��(�ͼ���)
		if(spageNum!=null){
			pageNum = Integer.parseInt(spageNum);//������ ��ȣ�� ������ pageNum�� ����.
		}
		
		/*
		���� �� �ڽ��� ������ * 5 - 4
		�� �� : �ڽ��� ������ * 5
		
		1������ 1~5
		2������ 6~10
		3������ 11~15
		*/
		//--------------- ������ �������� ����� �Խñ� ���� ����-------------------
		int startRow = (pageNum*5)-4;//������ ��ȣ
		int endRow = (pageNum*5);//���� ��ȣ


		BoardDao dao = new BoardDao();
		ArrayList<boardVo> list = dao.list(startRow,endRow,search,keyword);
	
		if(list!=null){
			
			//������ ���� ���ϱ� (��ü���� ����/�� ������ ���� �� ����) Math.ceil�� �Ҽ� ������ �ø�ó��
			int pageCount = (int)(Math.ceil(dao.getCount(search,keyword)/5.0));
			
			
			//������������ȣ ���ϱ�
			int startPageNum = ((pageNum-1)/4*4)+1; //1�������� ��� 1, 2�������� ��� 2, ... ,11�������� ��� 11
			
			
			//�������� ��ȣ ���ϱ�
			int endPageNum = startPageNum+3; //1�������� ��� 10, 11�������� ��� 20
			if(endPageNum>pageCount){//ex)(������ ��������) > (��ü ������ ����) ���� ũ�ٸ� 
				endPageNum = pageCount;//��ü �������� ������ �־��ָ��.
			}
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/board/list.jsp").forward(request, response);
		}else{
			response.sendRedirect("/fail.jsp");
		}
	}
}	
