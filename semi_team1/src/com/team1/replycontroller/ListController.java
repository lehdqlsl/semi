package com.team1.replycontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.team1.dao.ReplyDao;
import com.team1.vo.ReplyVo;

@WebServlet("/reply.list")
public class ListController {
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyDao dao=ReplyDao.getInstance();
		ArrayList<ReplyVo> list=dao.replyList();
		JSONArray jarr=new JSONArray();
		for(int i=0;i<list.size();i++){
			ReplyVo vo=list.get(i);
			JSONObject obj=new JSONObject();
			obj.put("num", vo.getR_num());
			obj.put("nick", vo.getNick());
			obj.put("content", vo.getContent());
			obj.put("up", vo.getUp());
			obj.put("reg_date", vo.getReg_date());
			obj.put("num", vo.getNum());
			obj.put("report", vo.getReport());
			jarr.add(obj);
		}
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.print(jarr.toString());
		pw.close();
	}
}
