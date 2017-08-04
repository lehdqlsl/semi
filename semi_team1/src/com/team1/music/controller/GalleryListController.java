package com.team1.music.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.team1.dao.BoardDao;
import com.team1.dao.MusicDao;
import com.team1.vo.MusicVo;
import com.team1.vo.boardListVo;
import com.team1.vo.boardVo;

@WebServlet("/gallerylist")
public class GalleryListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDao dao = new BoardDao();
		ArrayList<boardVo> list = dao.galleryList();
		
		ArrayList<boardListVo> flist = dao.bestlist(40, 10);
		ArrayList<boardListVo> qlist = dao.bestlist(41, 10);
		
		// 이미지 태그를 추출하기 위한 정규식.
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

		for (boardVo vo : list) {
			String content = vo.getContent();
			// 내용 중에서 이미지 태그를 찾아라!
			Matcher match = pattern.matcher(content);
			String imgTag = null;
			if (match.find()) { // 이미지 태그를 찾았다면,,
				imgTag = match.group(0); // 글 내용 중에 첫번째 이미지 태그를 뽑아옴.
				StringBuffer sb = new StringBuffer(imgTag); 
				sb.insert(5,"style=\"width:150px;height:150px\"");
				//System.out.println(sb.toString());
				vo.setTag(sb.toString());
			}
		}
		
		String spageNum=request.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum * 5) - 4;
		int endRow = (pageNum * 5);

		MusicDao mdao = new MusicDao();
		ArrayList<MusicVo> mlist = mdao.musiclist(startRow, endRow);
		
		

		if (mlist != null) {
			int pageCount = (int) (Math.ceil(mdao.getMusicCount() / 5.0));
			int startPageNum = pageNum;
			int endPageNum = pageNum;
			if (endPageNum > pageCount) {
				endPageNum = pageCount;
			}
			
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("mlist", mlist);
			
			request.setAttribute("list", list);
			request.setAttribute("flist", flist);
			request.setAttribute("qlist", qlist);
			request.getRequestDispatcher("/index.jsp?page=music/musicIndex.jsp").forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
