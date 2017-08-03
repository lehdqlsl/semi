package com.team1.vo;

import java.util.Date;;

public class G_ReviewVo {
	private int gr_num;
	private int g_num;
	private String m_nick;
	private float score;
	private String comments;
	private Date r_date;
	private int up;
	private int report;
	private int del;

	public G_ReviewVo() {
	}

	public G_ReviewVo(int gr_num, int g_num, String m_nick, float score, Date r_date, String comments, int up,
			int report, int del) {
		this.gr_num = gr_num;
		this.g_num = g_num;
		this.m_nick = m_nick;
		this.score = score;
		this.comments = comments;
		this.r_date = r_date;
		this.up = up;
		this.report = report;
		this.del = del;
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

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}