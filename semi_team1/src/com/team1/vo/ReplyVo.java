package com.team1.vo;

import java.util.Date;

public class ReplyVo {
	private int r_num;
	private String nick;
	private String content;
	private int up;
	private Date reg_date;
	private int num;
	private int report;
	
	private ReplyVo(){}

	public ReplyVo(int r_num, String nick, String content, int up, Date reg_date, int num, int report) {
		super();
		this.r_num = r_num;
		this.nick = nick;
		this.content = content;
		this.up = up;
		this.reg_date = reg_date;
		this.num = num;
		this.report = report;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}
}
