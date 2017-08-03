package com.team1.vo;

import java.util.Date;

public class TastyVo {
	private int num;
	private int b_num;
	private String m_nick;
	private float rating;
	private Date r_date;
	private String comments;
	private int up;
	private int report;
	private int del;

	public TastyVo(int num, int b_num, String m_nick, float rating, Date r_date, String comments, int up, int report,
			int del) {
		super();
		this.num = num;
		this.b_num = b_num;
		this.m_nick = m_nick;
		this.rating = rating;
		this.r_date = r_date;
		this.comments = comments;
		this.up = up;
		this.report = report;
		this.del = del;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public String getM_nick() {
		return m_nick;
	}

	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
