package com.team1.vo;

import java.util.Date;

public class M_ReviewVo {
	private int r_num;
	private int m_num;
	private String m_nick;
	private float r_gpa;
	private String r_comm;
	private Date reg_date;
	private int up;
	private int report;
	private int del;
	private String m_name;//
	private float rating;//
		
	
	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public M_ReviewVo(int m_num, float rating, String m_name) {
		super();
		this.m_num = m_num;
		this.m_name = m_name;
		this.rating = rating;
	}

	public M_ReviewVo(){}

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

	public float getR_gpa() {
		return r_gpa;
	}

	public void setR_gpa(float r_gpa) {
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

	public M_ReviewVo(int r_num, int m_num, String m_nick, float r_gpa, String r_comm, Date reg_date, int up,
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
