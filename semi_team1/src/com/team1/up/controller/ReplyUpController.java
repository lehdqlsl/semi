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

import com.team1.dao.ReplyUpDao;
import com.team1.vo.ReplyUpVo;

@WebServlet("/reply/up")
public class ReplyUpController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		HttpSession session = request.getSession();
		int num = (int) session.getAttribute("num");

		// 추천 할 수 있는지 확인
		ReplyUpDao dao = new ReplyUpDao();
		ReplyUpVo vo = new ReplyUpVo(r_num, num);
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
