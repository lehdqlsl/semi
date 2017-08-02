package com.team1.vo;

import java.util.Date;

public class MovieReplyVo {
	private int r_num;
	private int m_num;
	private String m_nick;
	private double r_gpa; 
	private String r_comm;
	private Date reg_date;
	private int up;
	private int report;
	private int del;
	
	public MovieReplyVo(){}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public String getM_nick() {
		return m_nick;
	}

	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}

	public double getR_gpa() {
		return r_gpa;
	}

	public void setR_gpa(double r_gpa) {
		this.r_gpa = r_gpa;
	}

	public String getR_comm() {
		return r_comm;
	}

	public void setR_comm(String r_comm) {
		this.r_comm = r_comm;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
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

	public MovieReplyVo(int r_num, int m_num, String m_nick, double r_gpa, String r_comm, Date reg_date, int up,
			int report, int del) {
		super();
		this.r_num = r_num;
		this.m_num = m_num;
		this.m_nick = m_nick;
		this.r_gpa = r_gpa;
		this.r_comm = r_comm;
		this.reg_date = reg_date;
		this.up = up;
		this.report = report;
		this.del = del;
	}
	
	
	
}
