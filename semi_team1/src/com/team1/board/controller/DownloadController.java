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
		// �ٿ�ε�â���� ������ ������ �������ϹǷ� ������ ���ϸ� ������
		String orgfilename=vo.getOrgfilename();
		// ���� �ٿ�ε��(���Ϻ���) ���� ����� ������ �ٿ�ε��ؾ� �ϹǷ� ����� ���ϸ� ������
		String savefilename=vo.getSavefilename();
		
		/////////////// �ٿ�ε�â���� �����ϱ� ///////////////
		
		String filename=URLEncoder.encode(orgfilename, "utf-8");
		// �ѱ۸� ������ ������ +�� ��ȯ�Ǵ� ���� �ٽ� �������� ��ȯ
		filename=filename.replaceAll("\\+", "%20");
		// ����������Ÿ�Լ���(�ٿ�ε�â���� ����)
		response.setContentType("application/octet-stream");
		// �ٿ�ε�â�� ������ ���ϸ� ����
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		
		/////////////// ������ ���Ϻ����ϱ� ///////////////
		// �ٿ�ε��� ������ �о���� ���� ��Ʈ��
		String uploadPath=getServletContext().getRealPath("/upload");
		FileInputStream fis=new FileInputStream(uploadPath+"//" + savefilename);
		//Ŭ���̾�Ʈ�� �����ϱ� ���� ��½�Ʈ��
		OutputStream os=response.getOutputStream();
		//�������(�ӵ�)�� ���ؼ� ���۱���� �ִ� ��Ʈ����ü�� ��ȯ
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
