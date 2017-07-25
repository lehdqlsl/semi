package com.team1.vo;

import java.util.Date;

public class ReplyVo {
	private int r_num;
	private String nick;
	private String content;
	private int up;
	private Date reg_date;
	private int b_num;
	private int report;

	public ReplyVo(int r_num, String nick, String content, int up, Date reg_date, int b_num, int report) {
		this.r_num = r_num;
		this.nick = nick;
		this.content = content;
		this.up = up;
		this.reg_date = reg_date;
		this.b_num = b_num;
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

	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

}
