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
import com.team1.vo.boardVo;

@WebServlet("/gallerylist")
public class GalleryListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDao dao = new BoardDao();
		ArrayList<boardVo> list = dao.galleryList();

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
				sb.insert(5,"style=\"width:200px;height:200px\"");
				System.out.println(sb.toString());
				vo.setTag(sb.toString());
			}
		}

		if (list != null) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("/index.jsp?page=music/musicIndex.jsp").forward(request, response);
		} else {
			response.sendRedirect("/fail.jsp");
		}
	}
}
