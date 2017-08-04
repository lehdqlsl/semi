package com.team1.tasty.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.team1.dao.BoardDao;
import com.team1.dao.BoardTastyDao;
import com.team1.vo.BoardTastyVo;
import com.team1.vo.boardListVo;
import com.team1.vo.boardVo;

@WebServlet("/tasty/main")
public class MainController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		ArrayList<boardListVo> free = dao.sportsMain(30, 5);
		ArrayList<boardListVo> qa = dao.sportsMain(31, 5);

		BoardTastyDao tdao = new BoardTastyDao();
		ArrayList<BoardTastyVo> s1 = tdao.bestlist(32, 5);
		ArrayList<BoardTastyVo> s2 = tdao.bestlist(33, 5);
		ArrayList<BoardTastyVo> s3 = tdao.bestlist(34, 5);
		ArrayList<BoardTastyVo> s4 = tdao.bestlist(35, 5);
		ArrayList<BoardTastyVo> s5 = tdao.bestlist(36, 5);
		ArrayList<BoardTastyVo> s6 = tdao.bestlist(37, 5);
		ArrayList<BoardTastyVo> bob = tdao.boblist();

		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		for (BoardTastyVo vo : bob) {
			String content = vo.getContent();
			// 내용 중에서 이미지 태그를 찾아라!
			Matcher match = pattern.matcher(content);
			String imgTag = null;
			if (match.find()) { // 이미지 태그를 찾았다면,,
				imgTag = match.group(0); // 글 내용 중에 첫번째 이미지 태그를 뽑아옴.
				StringBuffer sb = new StringBuffer(imgTag);
				sb.insert(5, "style=\"width:1200px;height:360px\"");
				// System.out.println(sb.toString());
				vo.setTag(sb.toString());
			}
		}

		request.setAttribute("free", free);
		request.setAttribute("qa", qa);

		request.setAttribute("s1", s1);
		request.setAttribute("s2", s2);
		request.setAttribute("s3", s3);
		request.setAttribute("s4", s4);
		request.setAttribute("s5", s5);
		request.setAttribute("s6", s6);
		request.setAttribute("bob", bob);
		request.setAttribute("first", bob.get(0));

		request.getRequestDispatcher("/index.jsp?page=/tasty/content.jsp").forward(request, response);

	}
}
