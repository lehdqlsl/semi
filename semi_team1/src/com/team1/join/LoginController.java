package com.team1.join;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.dao.LoginDao;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("login")){
			login(request,response);
		}else if(cmd.equals("logout")){
			logout(request,response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		session.invalidate();
		//로그아웃된 다음에 이동할 페이지
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String u_pw=request.getParameter("u_pw");
		HashMap<String, String> map=new HashMap<>();
		map.put("id", id);
		map.put("u_pw", u_pw);
		LoginDao dao=new LoginDao();
		boolean result=dao.isMember(map);
		if(result){
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			//로그인 한 후 이동할 페이지
		}else{
			request.setAttribute("errMsg", "아이디 또는 비밀번호가 틀립니다");
			request.getRequestDispatcher("/semi_team1/join/signin.jsp").forward(request, response);
		}	
	}
}
