package com.team1.vo;

import java.sql.Date;

public class G_ReviewVo {
	private int gr_num;
	private int g_num;
	private String m_nick;
	private double score;
	private Date r_date;
	private int up;
	private int report;
	
	public G_ReviewVo(){}

	public G_ReviewVo(int gr_num, int g_num, String m_nick, double score, Date r_date, int up, int report) {
		super();
		this.gr_num = gr_num;
		this.g_num = g_num;
		this.m_nick = m_nick;
		this.score = score;
		this.r_date = r_date;
		this.up = up;
		this.report = report;
	}

	public int getGr_num() {
		return gr_num;
	}

	public void setGr_num(int gr_num) {
		this.gr_num = gr_num;
	}

	public int getG_num() {
		return g_num;
	}

	public void setG_num(int g_num) {
		this.g_num = g_num;
	}

	public String getM_nick() {
		return m_nick;
	}

	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
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
}
