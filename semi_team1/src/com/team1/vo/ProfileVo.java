package com.team1.vo;

import java.util.Date;

public class ProfileVo {
	private int num;
	private String id;
	private String u_pw;
	private String m_nick;
	private String m_mail;
	private String m_orgfilename;
	private String m_savefilename;
	private String grade;
	private int exp;
	private Date reg_date;
	private int stop;
	private Date limit_date;
	public ProfileVo(int num, String id,String u_pw, String m_nick, String m_mail, String m_orgfilename, String m_savefilename,
			String grade, int exp, Date reg_date, int stop, Date limit_date) {
		this.num = num;
		this.id=id;
		this.u_pw = u_pw;
		this.m_nick = m_nick;
		this.m_mail = m_mail;
		this.m_orgfilename = m_orgfilename;
		this.m_savefilename = m_savefilename;
		this.grade = grade;
		this.exp = exp;
		this.reg_date = reg_date;
		this.stop = stop;
		this.limit_date = limit_date;
	}
	
	public ProfileVo(String m_savefilename, String m_nick, int num, int exp, Date reg_date) {
		this.m_savefilename = m_savefilename;
		this.m_nick = m_nick;
		this.num = num;
		this.exp = exp;
		this.reg_date = reg_date;
	}
	public ProfileVo(int num, String m_nick){
		this.num=num;
		this.m_nick=m_nick;
		
		
	}
	
	
	public ProfileVo(String id, String u_pw, String m_nick, String m_mail) {
		this.id = id;
		this.u_pw = u_pw;
		this.m_nick = m_nick;
		this.m_mail = m_mail;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public String getM_mail() {
		return m_mail;
	}
	public void setM_mail(String m_mail) {
		this.m_mail = m_mail;
	}
	public String getM_orgfilename() {
		return m_orgfilename;
	}
	public void setM_orgfilename(String m_orgfilename) {
		this.m_orgfilename = m_orgfilename;
	}
	public String getM_savefilename() {
		return m_savefilename;
	}
	public void setM_savefilename(String m_savefilename) {
		this.m_savefilename = m_savefilename;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getStop() {
		return stop;
	}
	public void setStop(int stop) {
		this.stop = stop;
	}
	public Date getLimit_date() {
		return limit_date;
	}
	public void setLimit_date(Date limit_date) {
		this.limit_date = limit_date;
	}
	
}
