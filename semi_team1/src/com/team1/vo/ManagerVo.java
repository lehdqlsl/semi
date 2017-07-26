package com.team1.vo;

import java.util.Date;

public class ManagerVo {
	private int boardnum;
	private String c_title;
	private String b_title;
	private Date regdate;
	private String writer;
	private int report;
	
	public ManagerVo(){}

	public ManagerVo(int boardnum, String c_title, String b_title, Date regdate, String writer, int report) {
		super();
		this.boardnum = boardnum;
		this.c_title = c_title;
		this.b_title = b_title;
		this.regdate = regdate;
		this.writer = writer;
		this.report = report;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}
	
}