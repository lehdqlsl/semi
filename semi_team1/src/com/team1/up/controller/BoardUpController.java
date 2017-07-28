package com.team1.up.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.team1.dao.BoardUpDao;
import com.team1.vo.BoardUpVo;

@WebServlet("/board/up")
public class BoardUpController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		HttpSession session = request.getSession();
		int num = (int) session.getAttribute("num");

		BoardUpDao dao = new BoardUpDao();
		BoardUpVo vo = new BoardUpVo(b_num, num);
		// 추천 할 수 있는지 확인
		boolean up_ok = dao.upOK(vo);
		if (up_ok) {
			dao.upUpdate(vo);
		}

		// 반환
		JSONObject json = new JSONObject();
		json.put("up_ok", up_ok);
		if (up_ok) {
			int cnt = dao.upCnt(vo);
			json.put("cnt", cnt);
		}
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json.toString());
		pw.close();

	}
}
