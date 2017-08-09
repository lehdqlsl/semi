package com.team1.profile.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team1.dao.ImgUpdateDao;
import com.team1.vo.ProfileVo;

@WebServlet("/imgupdate")
public class ImgUpdateController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uploadPath = getServletContext().getRealPath("/upload");
		MultipartRequest mr = new MultipartRequest(request, uploadPath, 1024 * 1024 * 5, "utf-8",
				new DefaultFileRenamePolicy());

		int num = Integer.parseInt(mr.getParameter("num"));
		String m_savefilename = mr.getParameter("m_savefilename");
		File d = new File(uploadPath + "\\" + m_savefilename);

		String orgFileName = mr.getOriginalFileName("file1");
		String saveFileName = mr.getFilesystemName("file1");
		ProfileVo vo = new ProfileVo();
		vo.setNum(num);
		vo.setM_savefilename(saveFileName);

		ImgUpdateDao dao = new ImgUpdateDao();
		int n = dao.update(vo);
		if (n > 0) {
			response.sendRedirect("/semi_team1/index.jsp?page=profile/userinfo.jsp?num=" + num);
		}
	}
}
