package com.team1.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.dao.BoardDao;
import com.team1.vo.boardVo;


@WebServlet("/download")
public class DownloadController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		BoardDao dao=new BoardDao();
		boardVo vo=dao.select(num);
		// 다운로드창에는 전송한 파일이 보여야하므로 전송한 파일명 얻어오기
		String orgfilename=vo.getOrgfilename();
		// 실제 다운로드는(파일복사) 실제 저장된 파일을 다운로드해야 하므로 저장된 파일명 얻어오기
		String savefilename=vo.getSavefilename();
		
		/////////////// 다운로드창으로 응답하기 ///////////////
		
		String filename=URLEncoder.encode(orgfilename, "utf-8");
		// 한글명에 공백이 있을때 +로 변환되는 것을 다시 공백으로 전환
		filename=filename.replaceAll("\\+", "%20");
		// 응답콘텐츠타입설정(다운로드창으로 응답)
		response.setContentType("application/octet-stream");
		// 다운로드창에 보여질 파일명 설정
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		
		/////////////// 실제로 파일복사하기 ///////////////
		// 다운로드할 파일을 읽어오기 위한 스트림
		String uploadPath=getServletContext().getRealPath("/upload");
		FileInputStream fis=new FileInputStream(uploadPath+"//" + savefilename);
		//클라이언트에 응답하기 위한 출력스트림
		OutputStream os=response.getOutputStream();
		//성능향상(속도)를 위해서 버퍼기능이 있는 스트림객체로 전환
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(os);
		byte[] b=new byte[1024];
		int n=0;
		while((n=bis.read(b))!=-1){
			bos.write(b, 0, n);
		}
		bos.close();
		bis.close();
	}	
}
